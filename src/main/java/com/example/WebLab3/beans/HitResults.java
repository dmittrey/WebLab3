package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.exceptions.OutOfBoundCoordinates;
import com.example.WebLab3.utility.HitService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public class HitResults {
    //todo Разложить ответственность
    //todo Сделать orm
    // Понять как вернуть в message
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Hit newHit = new Hit();
    private List<Hit> hitList = new ArrayList<>();
    private HitService hitService = new HitService();

    public void addHit() {
        serviceHit(newHit);
        newHit = new Hit();
    }

    public void serviceHit(Hit hit) {
        try {
            logger.info("Hit service started with {}!", hit.toString());
            long begin = System.nanoTime();
            hitService.service(hit, begin);
            hitList.add(hit);
            logger.info("Now, size of results is: {}", hitList.size());
//            shotDAO.save(this);  Проработать с запросом к бд
        } catch (OutOfBoundCoordinates e) {
            // Подумать как вывести сообщение об ошибке
        }
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
