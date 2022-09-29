package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class UserCollectionModel {

    @EqualsAndHashCode.Exclude
    private Integer page;
    @EqualsAndHashCode.Exclude
    private Integer per_page;
    @EqualsAndHashCode.Exclude
    private Integer total;
    @EqualsAndHashCode.Exclude
    private Integer total_pages;
    private List<UserModel> data;

}
