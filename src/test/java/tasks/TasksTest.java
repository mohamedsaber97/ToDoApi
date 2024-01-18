package tasks;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tasks.model.TasksDto;
import users.UsersTestHelper;

public class TasksTest {

    TasksTestHelper tasksTestHelper = new TasksTestHelper();
    UsersTestHelper usersTestHelper = new UsersTestHelper();

    @Story("ToDo Story")
    @Description("Add ToDo TC")
    @Test(priority = 1, groups = "todo")
    public void verifyAddTask() {
        TasksDto tasksDto = tasksTestHelper.createRandomTask();
        String token = usersTestHelper.getToken();
        Response response = tasksTestHelper.addTask(tasksDto, token);
        Assert.assertEquals(response.statusCode(), 201, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("isCompleted"), tasksDto.getIsCompleted().toString(),
                "isCompleted is not matched");
        Assert.assertEquals(response.jsonPath().getString("item"), tasksDto.getItem(),
                "item is not matched");
    }

    @Story("ToDo Story")
    @Description("Get ToDo TC")
    @Test(priority = 2, groups = "todo")
    public void VerifyTaskDetails() {
        TasksDto tasksDto = tasksTestHelper.createRandomTask();
        String token = usersTestHelper.getToken();
        Response res = tasksTestHelper.addTask(tasksDto, token);
        String taskId = res.jsonPath().getString("_id");
        Response response = tasksTestHelper.getTaskDetails(taskId, token);
        Assert.assertEquals(response.statusCode(), 200, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("_id"), taskId,
                "taskId is not matched");
    }

    @Story("ToDo Story")
    @Description("Delete ToDo TC")
    @Test(priority = 3, groups = "todo")
    public void VerifyDeleteTask() {
        TasksDto tasksDto = tasksTestHelper.createRandomTask();
        String token = usersTestHelper.getToken();
        Response res = tasksTestHelper.addTask(tasksDto, token);
        String taskId = res.jsonPath().getString("_id");
        Response response = tasksTestHelper.deleteTask(taskId, token);
        Assert.assertEquals(response.statusCode(), 200, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("_id"), taskId,
                "taskId is not matched");
    }
}
