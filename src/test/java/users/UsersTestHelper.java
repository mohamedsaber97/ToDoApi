package users;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import users.model.UserDto;

public class UsersTestHelper {
    UsersApiPage usersApiPage = new UsersApiPage();

    public UserDto createRandomUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserDto(firstName, lastName, email, password);
    }

    public Response addNewUser(UserDto userDto) {
        System.out.println("-----addNewUser for register user in app-----");
        return usersApiPage.addNewUser(userDto);
    }
}
