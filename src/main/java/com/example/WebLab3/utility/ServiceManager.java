package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.AreaHitCheckerInterface;
import com.example.WebLab3.interfaces.HitValidatorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.WebLab3.interfaces.ServiceManagerInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceManager implements ServiceManagerInterface {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HitValidatorInterface hitValidator = new HitValidator();
    private final AreaHitCheckerInterface areaChecker = new AreaHitChecker();

    @Override
    public boolean serviceClick(Hit aClick) {
        long serviceStartTime = System.nanoTime();
        service(aClick, serviceStartTime);
        return true;
    }

    @Override
    public boolean serviceForm(Hit aForm) {
        long serviceStartTime = System.nanoTime();
        if (hitValidator.validate(aForm)) {
            logger.info("Validation passed!");
            service(aForm, serviceStartTime);
            return true;
        } else {
            logger.info("Validation not passed!");
            return false;
        }
    }

    private void service(Hit hit, long serviceStartTime) {
        logger.info("Hit service started with {}!", hit.toString());
        hit.setResult(areaChecker.checkHitResult(hit));
        logger.info("Hit result is {}", hit.getResult());
        hit.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        hit.setExecutionTime((double) (System.nanoTime() - serviceStartTime) / 100000);
        logger.info("Hit service ended: {}", hit.toString());
    }
}