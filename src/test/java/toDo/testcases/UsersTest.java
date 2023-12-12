package toDo.testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import toDo.apis.UsersApi;
import toDo.data.ErrorMessages;
import toDo.models.ErrorMessageModel;
import toDo.models.UsersModel;
import toDo.steps.UsersSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UsersTest {


    @Test(priority = 1, groups = "auth")
    public void registerNewUserTC() {
        UsersModel usersModel = UsersSteps.generateRandomUser();
        Response response = UsersApi.registerNewUser(usersModel);
        UsersModel resultData = response.body().as(UsersModel.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(resultData.getFirstName(), equalTo(usersModel.getFirstName()));
    }

    @Test(priority = 2, groups = "auth")
    public void registerExistedUserTC() {
        UsersModel usersModel = UsersSteps.getRegisteredUser();
        Response response = UsersApi.registerExistedUser(usersModel);
        ErrorMessageModel errorMessageModel = response.body().as(ErrorMessageModel.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(errorMessageModel.getMessage(), equalTo(ErrorMessages.DUPLICATED_EMAIL));
    }

    @Test(priority = 3, groups = "auth")
    public void loginValidUserTC() {
        UsersModel usersModel = UsersSteps.getRegisteredUser();
        UsersModel loginUser = new UsersModel(usersModel.getEmail(), usersModel.getPassword());
        Response response = UsersApi.loginValidUser(loginUser);
        UsersModel resultData = response.body().as(UsersModel.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(resultData.getFirstName(), equalTo(usersModel.getFirstName()));
        assertThat(resultData.getAccessToken(), not(equalTo(null)));
    }

    @Test(priority = 4, groups = "auth")
    public void loginInValidUserTC() {
        UsersModel usersModel = UsersSteps.getRegisteredUser();
        UsersModel loginUser = new UsersModel(usersModel.getEmail(), "wrong pass");
        Response response = UsersApi.loginInValidUser(loginUser);
        ErrorMessageModel errorMessageModel = response.body().as(ErrorMessageModel.class);
        assertThat(response.statusCode(), equalTo(401));
        assertThat(errorMessageModel.getMessage(), equalTo(ErrorMessages.INCORRECT_USER_DATA));
    }
}
