package tasks;

import base.ApiMethodTypes;
import base.TestBase;
import io.restassured.response.Response;
import tasks.model.TasksDto;

public class TasksApiPage {

    public Response addTask(TasksDto tasksDto, String token) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                tasksDto,
                ApiMethodTypes.POST,
                TasksLocale.TODO_ROUTE,
                token
        );
    }

    public Response getTaskDetails(String taskId, String token) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                "",
                ApiMethodTypes.GET,
                TasksLocale.TODO_ROUTE + taskId,
                token
        );
    }

    public Response deleteTask(String taskId, String token) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                "",
                ApiMethodTypes.DELETE,
                TasksLocale.TODO_ROUTE + taskId,
                token
        );
    }
}
