import API.*;
import models.dto.*;

import org.junit.FixMethodOrder;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestClass {

    AccountTests account = new AccountTests();
    OptionsTests options = new OptionsTests();

    private static String token = null;

    @Test
    public void accountAuthentication() {
        RestAssured.defaultParser = Parser.JSON;
        token = account.authenticateCredentialsOK(new AccountAuthenticationDTO("Altairka", "rainbow"));
    }

    @Test
    public void accountAuthenticationTest() {
        RestAssured.defaultParser = Parser.JSON;
        account.authenticateWithMissingLogin();
        account.authenticateWithMissingPassword();
        account.authenticateWithNullCredentials();
        account.authenticateWithWrongCredentials();
    }

    @Test
    public void accountRegister() {
        RestAssured.defaultParser = Parser.JSON;
        account.registrationValid(new AccountRegistrationDTO("qww23а3q", "qewewqewqfаewqweq", "qfweqwаe@we.reweu"));
    }

    @Test
    public void accountRegisterTest() {
        RestAssured.defaultParser = Parser.JSON;
        account.registrationWithMissingLogin();
        account.registrationWithMissingPassword();
        account.registrationWithMissingEmail();
        account.registrationWithEmptyCredentials();
        account.registrationLoginIsAlreadyTaken();
        account.registrationEmailIsAlreadyTaken();
    }

    @Test
    public void updateEmail() {
        RestAssured.defaultParser = Parser.JSON;
        account.updateEmail(new AccountEmailDTO("acciosky@mail.ru"), token);
        account.updateEmailEmptyValue(token);
        account.updateEmailInvalidValue_1(token);
        account.updateEmailInvalidValue_2(token);
    }

    @Test
    public void getInfo() {
        RestAssured.defaultParser = Parser.JSON;
        account.getAccountInfo(token);
        account.getAccountInfoWrongToken();
        account.getAccountInfoEmptyToken();
        account.getProfileInfo(token);
        account.getProfileInfoWrongToken();
        account.getProfileInfoEmptyToken();
    }

    @Test
    public void checkOptions() {
        RestAssured.defaultParser = Parser.JSON;
        options.getTitleOK();
        options.getSocialNetworksOK();
    }
}
