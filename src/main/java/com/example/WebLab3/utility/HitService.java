package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.exceptions.OutOfBoundCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HitValidator hitValidator = new HitValidator();
    private final AreaChecker areaChecker = new AreaChecker();

    public void service(Hit hit, long serviceStart) throws OutOfBoundCoordinates {
        hitValidator.validate(hit);
        logger.info("Validation passed!");
        hit.setResult(areaChecker.checkShot(hit));
        logger.info("Hit result is {}", hit.getResult());
        hit.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        hit.setExecutionTime((System.nanoTime() - serviceStart) / 1000000);
        logger.info("Hit service ended!");
    }
}
