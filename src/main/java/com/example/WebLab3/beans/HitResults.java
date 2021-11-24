package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HitResults {
    //todo Сделать orm
    private Hit newHit = new Hit();

    private List<Hit> hitList = new ArrayList<>();

    public void addHit() {
        hitList.add(newHit);
        System.out.println(hitList.size());
        newHit = new Hit();
    }

    public void clear() {
        hitList.clear();
        newHit = new Hit();
    }
}
