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

    public UserDto getRegisteredUser() {
        UserDto userDto = createRandomUser();
        usersApiPage.addNewUser(userDto);
        return userDto;
    }

    public Response addNewUser(UserDto userDto) {
        System.out.println("-----addNewUser for register user in app-----");
        return usersApiPage.addNewUser(userDto);
    }

    public Response checkRegisteredUser() {
        System.out.println("-----checkRegisteredUser for check duplicated user in app-----");
        UserDto userDto = createRandomUser();
        usersApiPage.addNewUser(userDto);
        return usersApiPage.checkRegisteredUser(userDto);
    }

    public Response loginValidUser(UserDto userDto) {
        System.out.println("-----loginValidUser for login user in app-----");
        return usersApiPage.loginValidUser(userDto);
    }

    public Response loginInvalidUser(UserDto userDto) {
        System.out.println("-----loginInvalidUser for login invalid user in app-----");
        return usersApiPage.loginValidUser(userDto);
    }
}
