package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeAndMsg {

    OK(200),
    NOT_FOUND(404),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400);

    private final int code;

}
