package org.example.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.enums.Path;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ResponseService {

    public static final Gson GSON = new GsonBuilder().create();

    public static Response getResponse(Method method, RequestSpecification requestSpecification) {
        requestSpecification
                .baseUri(Path.SITE_URL.getPath())
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract()
                .response()
                .prettyPrint();
        return requestSpecification.request(method);
    }

    public static Response createModel(String path, Object model) {
        RequestSpecification requestSpecification = given()
                .basePath(path)
                .body(model instanceof JSONObject ? model.toString()
                        : GSON.toJson(model));
        return getResponse(Method.POST, requestSpecification);
    }

    public static Response getModelWithQueryParam(String path, Map<String, ? extends Serializable> map) {
        RequestSpecification requestSpecification = given()
                .basePath(path);
        map.forEach(requestSpecification::queryParam);
        return getResponse(Method.GET, requestSpecification);
    }

    public static Response deleteModelWithQueryParam(String path, Map<String, ? extends Serializable> map) {
        RequestSpecification requestSpecification = given()
                .basePath(path);
        map.forEach(requestSpecification::queryParam);
        return getResponse(Method.DELETE, requestSpecification);
    }

    public static Response updateModel(String path, Object model) {
        RequestSpecification requestSpecification = given()
                .basePath(path)
                .body(GSON.toJson(model));
        return getResponse(Method.PUT, requestSpecification);
    }

    public static Response updateModelPartly(String path, Object model) {
        RequestSpecification requestSpecification = given()
                .basePath(path)
                .body(GSON.toJson(model));
        return getResponse(Method.PATCH, requestSpecification);
    }
}
