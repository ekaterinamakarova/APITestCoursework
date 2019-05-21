package API;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class OptionsTests {

    public void getTitleOK() {
        given()
                .spec(Configuration.getRequestSpec())
                .when()
                .get(Configuration.getDomain() + "/api/options/title")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    public void getSocialNetworksOK() {
        given()
                .spec(Configuration.getRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .get(Configuration.getDomain() + "/api/options/socials")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

}
