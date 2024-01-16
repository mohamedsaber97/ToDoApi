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
}
