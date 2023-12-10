package toDo.steps;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import toDo.apis.UsersApi;
import toDo.models.UsersModel;

public class UsersSteps {

    public static UsersModel generateRandomUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String pass = faker.internet().password();

        return new UsersModel(firstName, lastName, email, pass);
    }

    public static UsersModel getRegisteredUser(){
        UsersModel usersModel = generateRandomUser();
        UsersApi.registerNewUser(usersModel);
        return usersModel;
    }

    public static String getToken(){
        UsersModel usersModel = generateRandomUser();
        Response response=UsersApi.registerNewUser(usersModel);
        return response.body().path("access_token");
    }
}
