package org.example.tests;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.example.enums.StatusCodeAndMsg;
import org.example.model.LoginModel;
import org.example.model.RegisterModel;
import org.example.model.User;
import org.example.response.ResponseService;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static org.example.enums.Path.LOGIN_PATH;
import static org.example.enums.Path.REGISTER_PATH;
import static org.example.enums.Path.USER_PATH;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetUsersTest {

    @Test(description = "Check GET request to find all users from the 2nd page. API 1.1")
    public void CheckUsersListStatusCode() {
        Response response = ResponseService.sendModelWithQueryParam(Method.GET, USER_PATH, Map.of("page", 2));
        assertThat("Status code is not 200 OK", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
        assertThat("Content-Type is not 'application/json'", response.contentType(), Matchers.equalTo("application/json; charset=utf-8"));
    }

    @Test(description = "Check GET request to find user with valid id. API 1.2")
    public void CheckSingleUserStatusCode() {
        Response response = ResponseService.sendModelWithQueryParam(Method.GET, USER_PATH, Map.of("id", 2));
        assertThat("Status code is not 200 OK", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Check GET request to find user with invalid id. API 1.2")
    public void CheckSingleUserWithInvalidIdStatusCode() {
        Response response = ResponseService.sendModelWithQueryParam(Method.GET, USER_PATH, Map.of("id", "1abc"));
        assertThat("Status code is not 404 NOT_FOUND", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NOT_FOUND.getCode()));
    }

    @Test(description = "Check GET request to find user with empty id. API 1.2")
    public void CheckSingleUserWithEmptyIdStatusCode() {
        Response response = ResponseService.sendModelWithQueryParam(Method.GET, USER_PATH, Map.of("id", " "));
        assertThat("Status code is not 404 NOT_FOUND", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NOT_FOUND.getCode()));
    }

    @Test(description = "Check GET request to find user with not existing id. API 1.3")
    public void CheckSingleUserNotFoundStatusCode() {
        Response response = ResponseService.sendModelWithQueryParam(Method.GET, USER_PATH, Map.of("id", 23));
        assertThat("Status code is not 404 not found", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NOT_FOUND.getCode()));
    }

    @Test(description = "Check POST request to create user. API 1.7")
    public void createUserAndCheckStatusCode() {
        User user = User.generateModel("morpheus", "leader");
        Response postRequest = ResponseService.sendModel(Method.POST, USER_PATH, user);
        assertThat("Status code is not 201 created", postRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.CREATED.getCode()));
    }

    @Test(description = "Check PUT request and change user`s data. API 1.8")
    public void updateUserAndCheckStatusCode() {
        User user = User.generateModel("morpheus", "zion resident");
        Response putRequest = ResponseService.sendModel(Method.PUT, USER_PATH + "/2", user);
        assertThat("Status code is not 200 OK", putRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Check PATCH request and change user`s data. API 1.9")
    public void updateUserPartlyAndCheckStatusCode() {
        User user = User.generateModel("morpheus", "zion resident");
        Response patchRequest = ResponseService.sendModel(Method.PATCH, USER_PATH + "/2", user);
        assertThat("Status code is not 200 OK", patchRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Check DELETE request with valid id. API 1.10")
    public void createDeleteUserAndCheckStatusCode() {
        Response deleteResponse = ResponseService.sendModelWithQueryParam(Method.DELETE, USER_PATH, Map.of("id", 2));
        assertThat("Status code is not 204 no content", deleteResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NO_CONTENT.getCode()));
    }

    @Test(description = "Check POST request to register a user. API 1.11")
    public void createRegisterAndCheckStatusCode() {
        RegisterModel registerModel = RegisterModel.generateModel("eve.holt@reqres.in", "pistol");
        Response postResponse = ResponseService.sendModel(Method.POST, REGISTER_PATH, registerModel);
        assertThat("Status code is not 200 OK", postResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Check POST request of user`s log in. API 1.13")
    public void createLoginAndCheckStatusCode() {
        LoginModel loginModel = LoginModel.generateModel("eve.holt@reqres.in", "cityslicka");
        Response postResponse = ResponseService.sendModel(Method.POST, LOGIN_PATH, loginModel);
        assertThat("Status code is not 200 OK", postResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }
}
