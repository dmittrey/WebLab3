package com.example.WebLab3.utility;

import com.example.WebLab3.dto.ValidationResult;
import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.EventChecker;
import com.example.WebLab3.interfaces.MessageCreator;
import com.example.WebLab3.interfaces.Validator;
import lombok.extern.slf4j.Slf4j;

import com.example.WebLab3.interfaces.ServiceManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class HitServiceManager implements ServiceManager<Hit> {
    private final Validator<Hit> hitValidator = new HitValidator();
    private final EventChecker<Hit> areaHitChecker = new AreaHitChecker();
    private final MessageCreator messageCreator = new WarningMessageCreator();

    @Override
    public boolean serviceWithValidation(Hit aForm) {
        long serviceStartTime = System.nanoTime();

        ValidationResult formValidation = hitValidator.validate(aForm);
        if (formValidation.isValidationStatus()) {
            log.info("Validation passed!");
            service(aForm, serviceStartTime);
            return true;
        } else {
            log.info("Validation not passed!");
            messageCreator.createMessage(formValidation.getValidationMessage());
            return false;
        }
    }

    @Override
    public void serviceWithoutValidation(Hit aClick) {
        long serviceStartTime = System.nanoTime();
        service(aClick, serviceStartTime);
    }

    private void service(Hit hit, long serviceStartTime) {
        log.info("Hit service started with {}!", hit.toString());
        hit.setResult(areaHitChecker.checkEvent(hit));
        log.info("Hit result is {}", hit.getResult());
        hit.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        hit.setExecutionTime((double) (System.nanoTime() - serviceStartTime) / 100000);
        log.info("Hit service ended: {}", hit.toString());
    }
}