package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.EventChecker;

public class AreaHitChecker implements EventChecker<Hit> {

    @Override
    public boolean checkEvent(Hit aHit) {
        return isBlueZone(aHit) || isGreenZone(aHit) || isYellowZone(aHit);
    }

    private boolean isBlueZone(Hit aHit) {
        double x = aHit.getX();
        double y = aHit.getY();
        double r = aHit.getR();

        return (y >= 0) && (x <= 0) && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
    }

    private boolean isGreenZone(Hit aHit) {
        double x = aHit.getX();
        double y = aHit.getY();
        double r = aHit.getR();

        return (x >= 0) && (y >= 0) && (x <= r) && (y <= r / 2);
    }

    private boolean isYellowZone(Hit aHit) {
        double x = aHit.getX();
        double y = aHit.getY();
        double r = aHit.getR();

        return (x >= 0) && (y <= 0) && (y >= 2 * x - r);
    }
}