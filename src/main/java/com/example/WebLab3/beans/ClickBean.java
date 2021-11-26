package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Data
@ManagedBean
public class ClickBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private double x;
    private double y;

    @ManagedProperty(value = "#{hitResults}")
    private HitResults hitResults;

    public void addClick() {
        String x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        logger.info("Click detected!");
        Hit newClick = new Hit();
        newClick.setX(Double.valueOf(x));
        newClick.setY(Double.valueOf(y));
        newClick.setR(hitResults.getNewHit().getR());
        hitResults.serviceClick(newClick);
        PrimeFaces.current().ajax().addCallbackParam("hitResult", newClick.getResult());
    }
}