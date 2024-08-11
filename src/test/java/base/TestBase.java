package base;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TestBase {

    public static final String TODO_APP_HOST = "https://qacart-todo.herokuapp.com";

//    public static String getEnvironment() {
//        String env = System.getProperty("env", "production");
//        String baseUrl;
//        switch (env) {
//            case "production":
//                baseUrl = "https://qacart-todo.herokuapp.com/api/v1/";
//                break;
//            case "local":
//                baseUrl = "https://localhost:8888";
//                break;
//            default:
//                throw new RuntimeException("-----Environment is not supported-----");
//        }
//        return baseUrl;
//    }

    public static Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", "PostmanRuntime/7.36.1");
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Connection", "keep-alive");
        return headers;
    }

    public static Response sendApiRequest(String baseUrl, Object body, ApiMethodTypes apiMethodTypes, String endPoint, String token) {
        Response response;
        switch (apiMethodTypes) {
            case POST:
                response = given()
                        .baseUri(baseUrl)
                        .headers(getHeaders())
                        .body(body)
                        .auth().oauth2(token)
                        .when().post(endPoint)
                        .then().log().body()
                        .extract().response();
                break;
            case GET:
                response = given()
                        .baseUri(baseUrl)
                        .headers(getHeaders())
                        .body(body)
                        .auth().oauth2(token)
                        .when().get(endPoint)
                        .then().log().body()
                        .extract().response();
                break;
            case PUT:
                response = given()
                        .baseUri(baseUrl)
                        .headers(getHeaders())
                        .body(body)
                        .auth().oauth2(token)
                        .when().put(endPoint)
                        .then().log().body()
                        .extract().response();
                break;
            case DELETE:
                response = given()
                        .baseUri(baseUrl)
                        .headers(getHeaders())
                        .body(body)
                        .auth().oauth2(token)
                        .when().delete(endPoint)
                        .then().log().body()
                        .extract().response();
                break;
            case PATCH:
                response = given()
                        .baseUri(baseUrl)
                        .headers(getHeaders())
                        .body(body)
                        .auth().oauth2(token)
                        .when().patch(endPoint)
                        .then().log().body()
                        .extract().response();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + apiMethodTypes);
        }
        return response;
    }
}
