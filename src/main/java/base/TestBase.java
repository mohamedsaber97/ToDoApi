package base;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class TestBase {
    public static RequestSpecification getRequestSpec() {
        return given()
                .baseUri("https://qacart-todo.herokuapp.com/api/v1/")
                .contentType(ContentType.JSON)
                .log().body();
    }
}
