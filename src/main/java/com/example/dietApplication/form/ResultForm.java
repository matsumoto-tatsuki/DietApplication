package com.example.dietApplication.form;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultForm {
    private Map<String, String> fields = new HashMap<>();
    private String memo;
    private int weight;
}
