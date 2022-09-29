package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    SITE_URL("https://reqres.in/api/"),
    USER_PATH("users"),
    REGISTER_PATH("register"),
    LOGIN_PATH("login");

    private final String path;
}
