package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HitValidator hitValidator = new HitValidator();
    private final AreaChecker areaChecker = new AreaChecker();

    public boolean service(Hit hit, long serviceStart) {
        if (hitValidator.validate(hit)) {
            logger.info("Validation passed!");
            hit.setResult(areaChecker.checkShot(hit));
            logger.info("Hit result is {}", hit.getResult());
            hit.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            hit.setExecutionTime((double) (System.nanoTime() - serviceStart) / 100000);
            logger.info("Hit service ended: {}", hit.toString());
            return true;
        } else {
            logger.info("Validation not passed!");
            return false;
        }
    }
}
