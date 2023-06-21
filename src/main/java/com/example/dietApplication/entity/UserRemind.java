package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class UserRemind {
    private int id;
    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private String time;
}
