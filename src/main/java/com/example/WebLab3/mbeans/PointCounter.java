package com.example.WebLab3.mbeans;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.Validator;
import com.example.WebLab3.utility.HitValidator;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean {

    private long hitsAmount = 0;
    private long innerHitsAmount = 0;

    private boolean isPreviousInner = true;

    private long sequenceNumber = 0;

    private final Validator<Hit> hitValidator = new HitValidator();

    public void persistHit(Hit aHit) {
        hitsAmount++;

        if (hitValidator.validate(aHit).isValidationStatus()) {
            innerHitsAmount++;

            isPreviousInner = true;
        } else {
            if (!isPreviousInner) {

                Notification notification = new Notification("someType", this, ++sequenceNumber,
                        System.currentTimeMillis(),
                        "Two negative hits happened");

                sendNotification(notification);
            }

            isPreviousInner = false;
        }
    }

    @Override
    public Long countAllPoints() {
        return hitsAmount;
    }

    @Override
    public Long countInnerPoints() {
        return innerHitsAmount;
    }
}
