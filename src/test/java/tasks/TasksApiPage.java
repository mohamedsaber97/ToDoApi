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
}
