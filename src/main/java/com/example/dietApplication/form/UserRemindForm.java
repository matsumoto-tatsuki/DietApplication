package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRemindForm {
    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    @NotBlank
    private String time;
}
