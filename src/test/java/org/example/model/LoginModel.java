package org.example.model;

import lombok.Data;

@Data
public class LoginModel {

    private String email;
    private String password;

    public static LoginModel generateModel(String email, String password) {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(email);
        loginModel.setPassword(password);
        return loginModel;
    }
}
