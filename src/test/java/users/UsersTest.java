package users;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import users.model.UserDto;

public class UsersTest {

    UsersTestHelper usersTestHelper = new UsersTestHelper();

    @Test
    public void verifyNewUser() {
        UserDto userDto = usersTestHelper.createRandomUser();
        Response response = usersTestHelper.addNewUser(userDto);
        Assert.assertEquals(response.getStatusCode(), 201, "error status code is " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("firstName"), userDto.getFirstName(), "firstName not matched");
    }
}
