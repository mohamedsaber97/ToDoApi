package toDo.apis;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import toDo.models.UsersModel;

import static io.restassured.RestAssured.given;

public class UsersApi extends TestBase {

    public static Response registerNewUser(UsersModel usersModel) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/register")
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response registerExistedUser(UsersModel usersModel) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/register")
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response loginValidUser(UsersModel usersModel) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/login")
                .then().log().status()
                .log().body()
                .extract().response();
    }
    public static Response loginInValidUser(UsersModel usersModel) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/login")
                .then().log().status()
                .log().body()
                .extract().response();
    }
}
