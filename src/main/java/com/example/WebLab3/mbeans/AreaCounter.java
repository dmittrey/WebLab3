package com.example.WebLab3.mbeans;

public class AreaCounter implements AreaCounterMBean {

    private double areaValue;

    public void calculateArea(double rValue) {
        areaValue = (Math.pow(rValue, 2) * Math.PI / 4) +
                rValue * rValue / 2 +
                1 / 2. * rValue * rValue / 2;
    }

    @Override
    public double getArea() {
        return areaValue;
    }
}
