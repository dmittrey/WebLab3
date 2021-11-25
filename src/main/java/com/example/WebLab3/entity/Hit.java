package com.example.WebLab3.entity;

import lombok.Data;

import java.util.Locale;

@Data
public class Hit {
    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private Double executionTime;
    private Boolean result;

    public String getTableX() {
        return String.format("%.3f", getX());
    }

    public String getTableY() {
        return String.format("%.3f", getY());
    }

    public String getTableR() {
        return String.format("%.3f", getR());
    }

    public String getTableResult() {
        return getResult().toString().toUpperCase(Locale.ROOT);
    }
}