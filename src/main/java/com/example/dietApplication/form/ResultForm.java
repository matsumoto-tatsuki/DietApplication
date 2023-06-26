package com.example.dietApplication.form;

import com.example.dietApplication.entity.DietResult;
import lombok.Data;

import java.util.List;

@Data
public class ResultForm {
    private List<DietResult> dietResults;
    private String memo;
    private int weight;
}
