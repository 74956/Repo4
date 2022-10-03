package org.example.model;

import lombok.Data;

@Data
public class RegisterModel {

    private String email;
    private String password;

    public static RegisterModel generateModel(String email, String password) {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setEmail(email);
        registerModel.setPassword(password);
        return registerModel;
    }
}
