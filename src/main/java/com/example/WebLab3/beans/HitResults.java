package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.exceptions.CoordinatesNotExist;
import com.example.WebLab3.exceptions.OutOfBoundCoordinates;
import com.example.WebLab3.utility.HitService;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
public class HitResults {

    //todo Сделать orm
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Hit newHit = new Hit();
    private List<Hit> hitList = new ArrayList<>();
    private HitService hitService = new HitService();

    public void addHit() {
        serviceHit(newHit);
        newHit = new Hit();
    }

    public void serviceClick(Hit aClick) {
        aClick.setR(newHit.getR());
        serviceHit(aClick);
    }

    public void serviceHit(Hit hit) {
        logger.info("Hit service started with {}!", hit.toString());
        long begin = System.nanoTime();
        if (hitService.service(hit, begin)) hitList.add(hit);
        logger.info("Now, size of results is: {}", hitList.size());
//            shotDAO.save(this);  Проработать с запросом к бд
    }

    public void resetHit() {
        newHit.setX(null);
        newHit.setY(null);
        newHit.setR(null);
    }

    public void clear() {
        hitList.clear();
        newHit = new Hit();
    }
}
