package com.example.WebLab3.exceptions;

import java.io.IOException;

public class OutOfBoundCoordinates extends IOException {

    public OutOfBoundCoordinates(String coordinateName, double coordinateValue) {
        super("Coordinate " + coordinateName + " is out of bounds(" + coordinateValue + ")!");
    }
}
