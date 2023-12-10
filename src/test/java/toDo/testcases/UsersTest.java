package toDo.testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import toDo.apis.UsersApi;
import toDo.models.ErrorMessage;
import toDo.models.UsersModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UsersTest {


    @Test(groups = "auth")
    public void registerNewUserTC() {
        UsersModel usersModel = new UsersModel("mohamed", "saber", "msaber97659@gmail.com", "12345678");
        Response response = UsersApi.registerNewUser(usersModel);
        UsersModel resultData = response.body().as(UsersModel.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(resultData.getFirstName(), equalTo(usersModel.getFirstName()));
    }

    @Test(groups = "auth")
    public void registerExistedUserTC() {
        UsersModel usersModel = new UsersModel("mohamed", "saber", "msaber9765@gmail.com", "12345678");
        Response response = UsersApi.registerExistedUser(usersModel);
        ErrorMessage errorMessage = response.body().as(ErrorMessage.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(errorMessage.getMessage(), equalTo("Email is already exists in the Database"));
    }

    @Test(groups = "auth")
    public void loginValidUserTC() {
        UsersModel usersModel = new UsersModel();
        usersModel.setEmail("msaber9765@gmail.com");
        usersModel.setPassword("12345678");
        Response response = UsersApi.loginValidUser(usersModel);
        UsersModel resultData = response.body().as(UsersModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(resultData.getFirstName(), equalTo("mohamed"));
        assertThat(resultData.getAccessToken(), not(equalTo(null)));
    }

    @Test(groups = "auth")
    public void loginInValidUserTC() {
        UsersModel usersModel = new UsersModel();
        usersModel.setEmail("msaber9765@gmail.commm");
        usersModel.setPassword("12345678");
        Response response = UsersApi.loginInValidUser(usersModel);
        ErrorMessage errorMessage = response.body().as(ErrorMessage.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(errorMessage.getMessage(), equalTo("Please Fill a correct Password"));
    }
}
