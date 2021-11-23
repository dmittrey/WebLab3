package com.example.WebLab3.beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@Data
@ManagedBean
@SessionScoped
public class HitResults {
    //todo Сделать очистку листа на кнопку
    //todo Сделать валидаторы
    //todo Сделать архитектуру нормальную с перекладыванием ответственности
    //todo Сделать orm
    private Hit newHit = new Hit();

    private List<Hit> hitList = new ArrayList<>();

    public void addHit() {
        hitList.add(newHit);
        System.out.println(hitList.size());
        newHit = new Hit();
    }
}
