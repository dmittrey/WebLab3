package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
@Data
public class ClickBean {
    private double x;
    private double y;

    @ManagedProperty(value="#{hitResults}")
    private HitResults hitResults;

    public void addClick() {
        String x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        System.out.println("Считали");
        Hit newClick = new Hit();
        newClick.setX(Double.valueOf(x));
        newClick.setY(Double.valueOf(y));
        newClick.setR(3.0);
        hitResults.addClick(newClick);
    }
}
