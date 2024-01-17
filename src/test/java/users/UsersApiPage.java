package users;

import base.TestBase;
import io.restassured.response.Response;
import users.model.ApiMethodTypes;
import users.model.UserDto;

public class UsersApiPage {
    public Response addNewUser(UserDto userDto) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                userDto,
                ApiMethodTypes.POST,
                UsersLocale.REGISTER_ROUTE);
    }

    public Response checkRegisteredUser(UserDto userDto) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                userDto,
                ApiMethodTypes.POST,
                UsersLocale.REGISTER_ROUTE);
    }

    public Response loginValidUser(UserDto userDto) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                userDto,
                ApiMethodTypes.POST,
                UsersLocale.LOGIN_ROUTE);
    }
    public Response loginInvalidUser(UserDto userDto) {
        return TestBase.sendApiRequest(
                TestBase.TODO_APP_HOST,
                userDto,
                ApiMethodTypes.POST,
                UsersLocale.LOGIN_ROUTE);
    }
}
