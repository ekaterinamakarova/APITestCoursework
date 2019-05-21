import API.*;
import models.dto.*;
import com.sun.xml.xsom.impl.scd.Token;

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
        account.registrationValid(new AccountRegistrationDTO("qwwqeewqewq", "qewewqewqewqweq", "qweqwe@we.reweu"));
    }

    @Test
    public void accountRegisterTest() {
        RestAssured.defaultParser = Parser.JSON;
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
    }

    @Test
    public void ban() {
        RestAssured.defaultParser = Parser.JSON;
        account.ban(new AccountBanDTO(" ", "Altairka", true), token);
        account.ban(new AccountBanDTO(" ", "Altairka", false), token);
    }

    @Test
    public void getInfo() {
        RestAssured.defaultParser = Parser.JSON;
        account.getAccountInfo(token);
        account.getProfileInfo(token);
    }

    @Test
    public void checkOptions() {
        RestAssured.defaultParser = Parser.JSON;
        options.getTitleOK();
        options.getSocialNetworksOK();
        // options.getSocialNetworksContainItemsOK();
        // options.postSocialOK();
        // options.getSocialOK();
        // options.patchSocialOK();
        // options.deleteSocialOK();
    }

}
