package toDo.apis;

import base.TestBase;
import io.restassured.response.Response;
import toDo.data.Routes;
import toDo.models.ToDoModel;

import static io.restassured.RestAssured.given;

public class ToDoApi {

    public static Response addToDo(ToDoModel toDoModel, String token) {
        return given().spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .body(toDoModel)
                .auth().oauth2(token)
                .when().post(Routes.TODO_ROUTE)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response getToDo(String token, String taskId) {
        return given().spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .auth().oauth2(token)
                .when().get(Routes.TODO_ROUTE + "/" + taskId)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response deleteToDo(String token, String taskId) {
        return given().spec(TestBase.getRequestSpec(TestBase.TODO_APP_HOST))
                .auth().oauth2(token)
                .when().delete(Routes.TODO_ROUTE + "/" + taskId)
                .then().log().status()
                .log().body()
                .extract().response();
    }
}
