package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class DietResult {
    private int dietResultId;
    private String dietName;
    private String action;
    private boolean result;
}
