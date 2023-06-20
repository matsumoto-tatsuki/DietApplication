package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class DietResult {
    private int dietResultId;
    private String dietName;
    private String action;
    private boolean result;

    public DietResult(int dietResultId, String dietName, String action, boolean result) {
        this.dietResultId = dietResultId;
        this.dietName = dietName;
        this.action = action;
        this.result = result;
    }

    public DietResult(){}
}
