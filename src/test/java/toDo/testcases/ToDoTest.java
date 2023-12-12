package toDo.testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import toDo.apis.ToDoApi;
import toDo.models.ToDoModel;
import toDo.steps.ToDoSteps;
import toDo.steps.UsersSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ToDoTest{

    @Test(priority = 1, groups = "todo")
    public void addToDoTC() {
        String token = UsersSteps.getToken();
        ToDoModel toDoModel = ToDoSteps.generateRandomToDo();
        Response response = ToDoApi.addToDo(toDoModel, token);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(todoData.getIsCompleted(), equalTo(false));
        assertThat(todoData.getItem(), equalTo(toDoModel.getItem()));
    }

    @Test(priority = 2, groups = "todo")
    public void getToDoTC() {
        String token = UsersSteps.getToken();
        ToDoModel toDoModel = ToDoSteps.generateRandomToDo();
        String taskId = ToDoSteps.getToDoId(toDoModel,token);
        Response response = ToDoApi.getToDo(token, taskId);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoData.getIsCompleted(), equalTo(toDoModel.getIsCompleted()));
        assertThat(todoData.getItem(), equalTo(toDoModel.getItem()));
    }

    @Test(priority = 3, groups = "todo")
    public void deleteToDoTC() {
        String token = UsersSteps.getToken();
        ToDoModel toDoModel = ToDoSteps.generateRandomToDo();
        String taskId = ToDoSteps.getToDoId(toDoModel,token);
        Response response = ToDoApi.deleteToDo(token, taskId);
        ToDoModel todoData = response.body().as(ToDoModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(todoData.getIsCompleted(), equalTo(toDoModel.getIsCompleted()));
        assertThat(todoData.getItem(), equalTo(toDoModel.getItem()));
    }
}
