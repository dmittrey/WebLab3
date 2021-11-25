package com.example.WebLab3.exceptions;

import java.io.IOException;

    public class CoordinatesNotExist extends IOException {

        public CoordinatesNotExist(String coordinateName) {
            super("Coordinate " + coordinateName + " is not exist!");
        }
    }
