package com.example.WebLab3.beans;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@Data
@ManagedBean
public class HitResults {
    private Hit newHit = new Hit();

    private List<Hit> hitList = new ArrayList<>();

    public void addHit() {
        System.out.println(newHit.toString());
        hitList.add(newHit);
        hitList.forEach(hit -> System.out.println(hit.toString()));
        newHit = new Hit();
    }
}
