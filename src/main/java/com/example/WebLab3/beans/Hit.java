package com.example.WebLab3.beans;

import lombok.Data;

@Data
public class Hit {
    private double x;
    private double y;
    private double r;
    private String currentTime;
    private long executionTime;
    private boolean result;
}