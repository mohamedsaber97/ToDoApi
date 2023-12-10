package toDo.testcases;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import toDo.apis.ToDoApi;
import toDo.models.ToDoModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ToDoTest extends TestBase {

    @Test(groups = "todo")
    public void addToDoTC() {
        ToDoModel toDoModel = new ToDoModel(false, "learn appium");
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";
        Response response = ToDoApi.addToDo(toDoModel, token);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(todoData.getIsCompleted(), equalTo(false));
        assertThat(todoData.getItem(), equalTo(toDoModel.getItem()));
    }

    @Test(groups = "todo")
    public void getToDoTC() {
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";
        String taskId = "6570631ec6d90f001414aa9b";
        Response response = ToDoApi.getToDo(token, taskId);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoData.getIsCompleted(), equalTo(false));
        assertThat(todoData.getItem(), equalTo("learn appium"));
    }

    @Test(groups = "todo")
    public void deleteToDoTC() {
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJpZCI6IjY1NzA1MTExYzZkOTBmMDAxNDE0YTljOSIsImZpcnN0TmFtZSI6Im1vaGFtZWQiLCJsYXN0TmFtZSI6InNhYmVyIiwiaWF0IjoxNzAxODYyNjM1fQ." +
                "717B1Ge9WNThXSRQwmP7iayGCrZfhqtI6bAZWhQL4Ks";
        String taskId = "65705d6bc6d90f001414aa35";
        Response response = ToDoApi.deleteToDo(token, taskId);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoData.getIsCompleted(), equalTo(false));
        assertThat(todoData.getItem(), equalTo("learn appium"));
    }
}
