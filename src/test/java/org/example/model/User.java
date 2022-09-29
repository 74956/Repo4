package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class User {

    private String name;
    private String job;
    @EqualsAndHashCode.Exclude
    private Integer id;
    @EqualsAndHashCode.Exclude
    private String createdAt;

    public static User generate(String name, String job) {
        User model = new User();
        model.setJob(name);
        model.setJob(job);
        return model;
    }
}
