package API;

import io.restassured.http.ContentType;
import models.dto.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    public void getSocialNetworksContainItemsOK() {
        Social[] socials = given()
                .spec(Configuration.getRequestSpec())
                .when()
                .get(Configuration.getDomain() + "/api/options/socials")
                .as(Social[].class);
        assertTrue(socials.length != 0);
    }

    public void postSocialOK(String token, String socialTestNetwork) {
        Social social = new Social(socialTestNetwork, "https://github.com", null, "fab fa-github");
        given()
                .spec(Configuration.getRequestSpec())
                .header("Authentication", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(social)
                .when()
                .post(Configuration.getDomain() + "/api/options/social")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    public void getSocialOK( String socialTestNetworkID) {
        if (socialTestNetworkID == null) {
            fail("Social Test Network ID is null!");
        }
        given()
                .spec(Configuration.getRequestSpec())
                .when()
                .get(Configuration.getDomain() + "/api/options/social/{name}", socialTestNetworkID)
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    public void patchSocialOK(String token, String patchSocial) {
        given()
                .spec(Configuration.getRequestSpec())
                .header("Authentication", "Bearer " + token)
                .body(patchSocial).when()
                .patch(Configuration.getDomain() + "/api/options/social")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    public void deleteSocialOK(String token, String socialID) {
        given()
                .spec(Configuration.getRequestSpec())
                .header("Authentication", "Bearer " + token)
                .body(socialID).when()
                .delete(Configuration.getDomain() + "/api/options/social")
                .then()
                .statusCode(200)
                .body("code", equalTo(200));
    }
}
