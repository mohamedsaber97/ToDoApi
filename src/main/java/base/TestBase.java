package base;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class TestBase {

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
                throw new RuntimeException("Environment is not supported");
        }
        return baseUrl;
    }

    public static RequestSpecification getRequestSpec() {
        return given()
                .baseUri(getEnvironment())
                .contentType(ContentType.JSON)
                .log().body();
    }
}
