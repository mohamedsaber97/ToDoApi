package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.http.ContentType;
import users.model.ApiMethodTypes;

import static io.restassured.RestAssured.*;

public class TestBase {

    public static final String TODO_APP_HOST = "https://qacart-todo.herokuapp.com";

    public static String getEnvironment() {
        String env = System.getProperty("env", "production");
        String baseUrl;
        switch (env) {
            case "production":
                baseUrl = "https://qacart-todo.herokuapp.com/api/v1/";
                break;
            case "local":
                baseUrl = "https://localhost:8888";
                break;
            default:
                throw new RuntimeException("-----Environment is not supported-----");
        }
        return baseUrl;
    }

    public static RequestSpecification getRequestSpec(String baseUrl) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public static Response sendApiRequest(String baseUrl, Object object, ApiMethodTypes apiMethodTypes, String endPoint) {
        Response response;
        switch (apiMethodTypes) {
            case POST:
                response = given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .body(object)
                        .when().post(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case GET:
                response = given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .when().get(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case PUT:
                response = given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .when().put(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case DELETE:
                response = given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .when().delete(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case PATCH:
                response = given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .when().patch(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + apiMethodTypes);
        }
        return response;
    }
}
