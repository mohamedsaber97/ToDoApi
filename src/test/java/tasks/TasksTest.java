package tasks;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tasks.model.TasksDto;

public class TasksTest {

    TasksTestHelper tasksTestHelper = new TasksTestHelper();

    @Story("ToDo Story")
    @Description("Add ToDo TC")
    @Test(priority = 1, groups = "todo")
    public void verifyAddTask() {
        TasksDto tasksDto = tasksTestHelper.createRandomTask();
        Response response = tasksTestHelper.addTask(tasksDto);
        Assert.assertEquals(response.statusCode(), 201, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("isCompleted"), tasksDto.getIsCompleted().toString(),
                "isCompleted is not matched");
        Assert.assertNotEquals(response.jsonPath().getString("_id"), null,
                "task id is null");
        Assert.assertEquals(response.jsonPath().getString("item"), tasksDto.getItem(),
                "item is not matched");
        Assert.assertNotEquals(response.jsonPath().getString("userID"), null,
                "userId is null");
    }
}
