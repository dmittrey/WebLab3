package com.example.WebLab3.entity;

import lombok.Data;

//todo Сделать конвертер для таблицы
@Data
public class Hit {
    private double x;
    private double y;
    private double r;
    private String currentTime;
    private long executionTime;
    private boolean result;
}