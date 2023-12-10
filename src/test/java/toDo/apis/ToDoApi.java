package toDo.apis;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import toDo.models.ToDoModel;

import static io.restassured.RestAssured.given;

public class ToDoApi extends TestBase {

    public static Response addToDo(ToDoModel toDoModel, String token) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(toDoModel)
                .auth().oauth2(token)
                .when().post("tasks")
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response getToDo(String token, String taskId) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when().get("tasks/" + taskId)
                .then().log().status()
                .log().body()
                .extract().response();
    }

    public static Response deleteToDo(String token, String taskId) {
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when().delete("tasks/" + taskId)
                .then().log().status()
                .log().body()
                .extract().response();
    }
}
