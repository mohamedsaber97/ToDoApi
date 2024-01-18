package tasks;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import tasks.model.TasksDto;
public class TasksTestHelper {
    TasksApiPage tasksApiPage = new TasksApiPage();
    public TasksDto createRandomTask() {
        Faker faker = new Faker();
        boolean isCompleted = false;
        String item = faker.name().name();
        return new TasksDto(isCompleted, item);
    }

    public Response addTask(TasksDto tasksDto, String token) {
        System.out.println("-----addTask for add new task-----");
        return tasksApiPage.addTask(tasksDto, token);
    }

    public Response getTaskDetails(String taskId, String token) {
        System.out.println("-----getTaskDetails for get task details by taskId-----");
        return tasksApiPage.getTaskDetails(taskId, token);
    }

    public Response deleteTask(String taskId, String token) {
        System.out.println("-----deleteTask for delete task by taskId-----");
        return tasksApiPage.deleteTask(taskId, token);
    }
}
