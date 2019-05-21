package API;


import io.restassured.http.ContentType;
import models.dto.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AccountTests {

    public String authenticateCredentialsOK(AccountAuthenticationDTO dto) {
        String token = given().spec(Configuration.getRequestSpec()).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/authenticate").then().statusCode(200).body("code", equalTo(200))
                .extract().path("object");

        System.out.println("Token: " + token);
        return token;
    }

    public void authenticateWithMissingLogin() {
        AccountAuthenticationDTO dto = new AccountAuthenticationDTO(null, "password");
        given().spec(Configuration.getRequestSpec()).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/authenticate").then().statusCode(400);
    }

    public void authenticateWithMissingPassword() {
        AccountAuthenticationDTO dto = new AccountAuthenticationDTO("login", null);
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/authenticate").then().statusCode(400);
    }

    public void authenticateWithNullCredentials() {
        AccountAuthenticationDTO dto = new AccountAuthenticationDTO(null, null);
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/authenticate").then().statusCode(400);
    }

    public void authenticateWithWrongCredentials() {
        AccountAuthenticationDTO dto = new AccountAuthenticationDTO("oiasfjbkwvekfjw", "alsjbhfbkawhekwe");
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/authenticate").then().statusCode(200).body("code", equalTo(404));
    }

    public void registrationValid(AccountRegistrationDTO dto) {
        given().spec(Configuration.getRequestSpec()).body(dto).when().post(Configuration.getDomain() + "/api/account/register")
                .then().body("code", equalTo(200));
    }

    public void registrationWithMissingLogin() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO(null, "qwerty", "qwerty@.mail.ru");
        given().spec(Configuration.getRequestSpec()).body(dto).when().post(Configuration.getDomain() + "/api/account/register")
                .then().statusCode(400);
    }

    public void registrationWithMissingPassword() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO("qwerty", null, "qwerty@.mail.ru");
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/register").then().statusCode(400);
    }

    public void registrationWithMissingEmail() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO("qwerty", "qwerty", null);
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/register").then().statusCode(400);
    }

    public void registrationWithEmptyCredentials() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO(null, null, null);
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/register").then().statusCode(400);
    }

    public void registrationLoginIsAlreadyTaken() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO("Altairka", "password", "qwerty@mail.com");
        given().spec(Configuration.getRequestSpec()).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/register").then().statusCode(200).body("code", equalTo(409));
    }

    public void registrationEmailIsAlreadyTaken() {
        AccountRegistrationDTO dto = new AccountRegistrationDTO("newuser", "password", "altair999z@gmail.com");
        given().spec(Configuration.getRequestSpec()).body(dto).when().post(Configuration.getDomain() + "/api/account/register")
                .then().statusCode(200).body("code", equalTo(409));
    }

    public void updateEmail(AccountEmailDTO dto, String token) {
        given().spec(Configuration.getRequestSpec(token)).body(dto).when().post(Configuration.getDomain() + "/api/account/email")
                .then().statusCode(200).body("code", equalTo(200));
    }

    public void ban(AccountBanDTO dto, String token) {
        given().spec(Configuration.getRequestSpec(token)).contentType(ContentType.JSON).body(dto).when()
                .post(Configuration.getDomain() + "/api/account/email").then().statusCode(200).body("code", equalTo(200));
    }

    public void getAccountInfo(String token) {
        given().spec(Configuration.getRequestSpec(token)).when()
                .get(Configuration.getDomain() + "/api/account/getAccountInfo").then().statusCode(200).body("code", equalTo(200));
    }

    public void getProfileInfo(String token) {
        given().spec(Configuration.getRequestSpec(token)).when()
                .get(Configuration.getDomain() + "/api/account/getProfileInfo").then().statusCode(200).body("code", equalTo(200));
    }
}
