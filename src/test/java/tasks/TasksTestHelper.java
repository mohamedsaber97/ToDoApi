package tasks;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import tasks.model.TasksDto;
import users.UsersTestHelper;
import users.model.UserDto;

public class TasksTestHelper {
    TasksApiPage tasksApiPage = new TasksApiPage();
    UsersTestHelper usersTestHelper = new UsersTestHelper();

    public TasksDto createRandomTask() {
        Faker faker = new Faker();
        boolean isCompleted = false;
        String item = faker.name().name();
        return new TasksDto(isCompleted, item);
    }

    public Response addTask(TasksDto tasksDto) {
        System.out.println("-----addTask for add new task-----");
        String token = usersTestHelper.getToken();
        return tasksApiPage.addTask(tasksDto, token);
    }
}
