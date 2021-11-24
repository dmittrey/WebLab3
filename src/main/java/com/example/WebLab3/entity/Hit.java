package com.example.WebLab3.entity;

import lombok.Data;

//todo Сделать конвертер для таблицы
@Data
public class Hit {
    private Double x;
    private Double y;
    private Double r;
    private String currentTime;
    private long executionTime;
    private boolean result;
}