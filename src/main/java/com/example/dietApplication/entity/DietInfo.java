package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class DietInfo {
    private  Integer id;
    private boolean favorite;
    private Integer select;
    private String dietName;
    private String period;
    private String communityName;
    private String categoryName;
    private String difficultName;
}
