package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class DietInfo {
    private boolean favorite;
    private boolean select;
    private String dietName;
    private String period;
    private String communityName;
    private String categoryName;
    private String difficultName;
}
