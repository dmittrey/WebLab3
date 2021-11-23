package com.example.WebLab3.beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;

@Data
@ManagedBean
public class timeBean {

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;
    private int second;
}
