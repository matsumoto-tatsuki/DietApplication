package com.example.dietApplication.entity;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        DietResult that = (DietResult) o;
        return Objects.equals(dietName, that.dietName) &&
                Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dietResultId, dietName, action, result);
    }
}
