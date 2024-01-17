package users;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import users.model.UserDto;

public class UsersTest {

    UsersTestHelper usersTestHelper = new UsersTestHelper();

    @Story("Register Story")
    @Description("Register New User TC")
    @Test(priority = 1, groups = "auth")
    public void verifyNewUser() {
        UserDto userDto = usersTestHelper.createRandomUser();
        Response response = usersTestHelper.addNewUser(userDto);
        Assert.assertEquals(response.getStatusCode(), 201, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("firstName"), userDto.getFirstName(),
                "firstName not matched");
    }

    @Story("Register Story")
    @Description("Register Existed User TC")
    @Test(priority = 2, groups = "auth")
    public void verifyRegisteredUser() {
        Response response = usersTestHelper.checkRegisteredUser();
        Assert.assertEquals(response.getStatusCode(), 400, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("message"), UsersLocale.DUPLICATED_EMAIL_MSG,
                "duplicated account message not matched");
    }

    @Story("Login Story")
    @Description("Login Valid User TC")
    @Test(priority = 3, groups = "auth")
    public void verifyValidLogin() {
        UserDto userDto = usersTestHelper.getRegisteredUser();
        UserDto loginData = new UserDto(userDto.getEmail(), userDto.getPassword());
        Response response = usersTestHelper.loginValidUser(loginData);
        Assert.assertEquals(response.getStatusCode(), 200, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("firstName"), userDto.getFirstName(),
                "user login with invalid data");
    }

    @Story("Login Story")
    @Description("Login Valid User TC")
    @Test(priority = 3, groups = "auth")
    public void verifyInvalidLogin() {
        UserDto userDto = usersTestHelper.getRegisteredUser();
        UserDto loginData = new UserDto(userDto.getEmail(), "wrong password");
        Response response = usersTestHelper.loginValidUser(loginData);
        Assert.assertEquals(response.getStatusCode(), 401, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("message"), UsersLocale.INCORRECT_USER_DATA_MSG,
                "user login with invalid data");
    }
}
