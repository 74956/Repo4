package org.example.tests;

import io.restassured.response.Response;
import org.example.enums.StatusCodeAndMsg;
import org.example.model.LoginModel;
import org.example.model.RegisterModel;
import org.example.model.User;
import org.example.response.ResponseService;
import org.example.enums.Path;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetUsersTest {  //get запрос не находит по id, так как нет в сохранения

    @Test(description = "Get API 1.1")
    public void CheckUsersListStatusCode() {
        Response response = ResponseService.getModelWithQueryParam(Path.USER_PATH.getPath(), Map.of("page", 2));
        assertThat("Status code is not 200 OK", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
        assertThat("Content-Type is not 'application/json'", response.contentType(), Matchers.equalTo("application/json; charset=utf-8"));
    }

    @Test(description = "Get API 1.2")
    public void CheckSingleUserStatusCode() {
        Response response = ResponseService.getModelWithQueryParam(Path.USER_PATH.getPath(), Map.of("id", 2));
        assertThat("Status code is not 200 OK", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Get API 1.3")
    public void CheckSingleUserNotFoundStatusCode() {
        Response response = ResponseService.getModelWithQueryParam(Path.USER_PATH.getPath(), Map.of("id", 23));
        assertThat("Status code is not 404 not found", response.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NOT_FOUND.getCode()));
    }

    @Test(description = "Post API 1.7")
    public void createUserAndCheckStatusCode() {
        User user = User.generate("morpheus", "leader");
        Response postRequest = ResponseService.createModel(Path.USER_PATH.getPath(), user);
        assertThat("Status code is not 201 created", postRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.CREATED.getCode()));
    }

    @Test(description = "Put API 1.8")
    public void updateUserAndCheckStatusCode() {
        User user = User.generate("morpheus", "zion resident");
        Response putRequest = ResponseService.updateModel(Path.USER_PATH.getPath() + "/2", user);
        assertThat("Status code is not 200 OK", putRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Patch API 1.9")
    public void updateUserPartlyAndCheckStatusCode() {
        User user = User.generate("morpheus", "zion resident");
        Response patchRequest = ResponseService.updateModelPartly(Path.USER_PATH.getPath() + "/2", user);
        assertThat("Status code is not 200 OK", patchRequest.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "API 1.10")
    public void createDeleteUserAndCheckStatusCode() {
        Response deleteResponse = ResponseService.deleteModelWithQueryParam(Path.USER_PATH.getPath(), Map.of("id", 2));
        assertThat("Status code is not 204 no content", deleteResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.NO_CONTENT.getCode()));
    }

    @Test(description = "Post API 1.11")
    public void createRegisterAndCheckStatusCode() {
        RegisterModel registerModel = RegisterModel.generate("eve.holt@reqres.in", "pistol");
        Response postResponse = ResponseService.createModel(Path.REGISTER_PATH.getPath(), registerModel);
        assertThat("Status code is not 200 OK", postResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }

    @Test(description = "Post API 1.13")
    public void createLoginAndCheckStatusCode() {
        LoginModel loginModel = LoginModel.generate("eve.holt@reqres.in", "cityslicka");
        Response postResponse = ResponseService.createModel(Path.LOGIN_PATH.getPath(), loginModel);
        assertThat("Status code is not 200 OK", postResponse.getStatusCode(), Matchers.equalTo(StatusCodeAndMsg.OK.getCode()));
    }
}
