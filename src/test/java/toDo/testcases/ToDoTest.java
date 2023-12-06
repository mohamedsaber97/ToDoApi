package toDo.testcases;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import toDo.models.ToDoModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ToDoTest extends TestBase {

    @Test(groups = "todo")
    public void addToDoTC() {
        ToDoModel toDoModel = new ToDoModel(false, "learn appium");

        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(toDoModel)
                .auth().oauth2(token)
                .when().post("tasks")
                .then().log().status()
                .log().body()
                .assertThat().statusCode(201)
                .assertThat().body("isCompleted", equalTo(false))
                .assertThat().body("item", equalTo("learn appium"));
    }

    @Test(groups = "todo")
    public void getToDoTC() {
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";

        String taskId = "6570631ec6d90f001414aa9b";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when().get("tasks/" + taskId)
                .then().log().status()
                .log().body()
                .assertThat().statusCode(200)
                .assertThat().body("isCompleted", equalTo(false))
                .assertThat().body("item", equalTo("learn appium"));
    }

    @Test(groups = "todo")
    public void deleteToDoTC() {
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";

        String taskId = "65705d6bc6d90f001414aa35";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when().delete("tasks/" + taskId)
                .then().log().status()
                .log().body()
                .assertThat().statusCode(200)
                .assertThat().body("isCompleted", equalTo(false))
                .assertThat().body("item", equalTo("learn appium"));
    }
}
