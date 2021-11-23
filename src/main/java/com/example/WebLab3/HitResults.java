package com.example.WebLab3;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@Data
@ManagedBean
public class HitResults {
    private Hit newHit = new Hit();

    private List<Hit> hitList = new ArrayList<Hit>();

    public void addHit() {
        hitList.add(newHit);
        newHit = new Hit();
    }
}
