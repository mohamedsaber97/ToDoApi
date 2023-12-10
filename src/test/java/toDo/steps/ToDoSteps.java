package toDo.steps;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import toDo.apis.ToDoApi;
import toDo.models.ToDoModel;

public class ToDoSteps {

    public static ToDoModel generateRandomToDo() {
        Faker faker = new Faker();
        boolean isCompleted = false;
        String item = faker.name().name();
        return new ToDoModel(isCompleted,item);
    }

    public static String getToDoId(ToDoModel toDoModel,String token){
        Response response = ToDoApi.addToDo(toDoModel , token);
        return response.body().path("_id");
    }
}
