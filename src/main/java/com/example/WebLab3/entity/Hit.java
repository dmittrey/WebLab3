package com.example.WebLab3.entity;

import lombok.Data;

@Data
public class Hit {
    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private long executionTime;
    private Boolean result;
}