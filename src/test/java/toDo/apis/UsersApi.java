package toDo.apis;

import base.TestBase;
import io.restassured.response.Response;
import toDo.data.Routes;
import toDo.models.UsersModel;

import static io.restassured.RestAssured.given;

public class UsersApi {

    public static Response registerNewUser(UsersModel usersModel) {
        return given()
                .spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .body(usersModel)
                .when().post(Routes.REGISTER_ROUTE)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response registerExistedUser(UsersModel usersModel) {
        return given()
                .spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .body(usersModel)
                .when().post(Routes.REGISTER_ROUTE)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response loginValidUser(UsersModel usersModel) {
        return given()
                .spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .body(usersModel)
                .when().post(Routes.LOGIN_ROUTE)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response loginInValidUser(UsersModel usersModel) {
        return given()
                .spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .body(usersModel)
                .when().post(Routes.LOGIN_ROUTE)
                .then().log().status()
                .log().body()
                .extract().response();
    }
}
