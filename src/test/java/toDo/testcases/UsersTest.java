package toDo.testcases;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import toDo.models.UsersModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UsersTest extends TestBase {


    @Test(groups = "auth")
    public void registerNewUserTC() {
        UsersModel usersModel = new UsersModel("mohamed", "saber", "msaber9765@gmail.com", "12345678");
        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/register")
                .then().log().status()
                .log().body()
                .assertThat().statusCode(201)
                .assertThat().body("firstName", equalTo("mohamed"));
    }

    @Test(groups = "auth")
    public void registerExistedUserTC() {
        UsersModel usersModel = new UsersModel("mohamed", "saber", "msaber9765@gmail.com", "12345678");
        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/register")
                .then().log().status()
                .log().body()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Email is already exists in the Database"));
    }

    @Test(groups = "auth")
    public void loginValidUserTC() {
        UsersModel usersModel = new UsersModel();
        usersModel.setEmail("msaber9765@gmail.com");
        usersModel.setPassword("12345678");

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/login")
                .then().log().status()
                .log().body()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", equalTo("mohamed"))
                .assertThat().body("access_token", not(equalTo(null)));
    }

    @Test(groups = "auth")
    public void loginInValidUserTC() {
        UsersModel usersModel = new UsersModel();
        usersModel.setEmail("msaber9765@gmail.commm");
        usersModel.setPassword("12345678");

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(usersModel)
                .when().post("users/login")
                .then().log().status()
                .log().body()
                .assertThat().statusCode(400)
                .assertThat().body("message", equalTo("Please Fill a correct Password"));
    }
}
