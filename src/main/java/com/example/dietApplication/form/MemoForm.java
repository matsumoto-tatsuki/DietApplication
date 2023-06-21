package com.example.dietApplication.form;

import lombok.Data;

@Data
public class MemoForm {
    private String memo;

    public MemoForm() {
    }

    public MemoForm(String memo) {
        this.memo = memo;
    }
}
