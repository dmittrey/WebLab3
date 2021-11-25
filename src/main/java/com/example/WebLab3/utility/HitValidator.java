package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.exceptions.OutOfBoundCoordinates;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HitValidator {

    public void validate(Hit hit) throws OutOfBoundCoordinates {
        validateX(hit.getX());
        validateY(hit.getY());
        validateR(hit.getR());
    }

    private void validateX(double xCoordinate) throws OutOfBoundCoordinates {
        if (xCoordinate < -3 || xCoordinate > 3)
            throw new OutOfBoundCoordinates("x", xCoordinate);
    }

    private void validateY(double yCoordinate) throws OutOfBoundCoordinates {
        if (yCoordinate < -5 || yCoordinate > 5)
            throw new OutOfBoundCoordinates("y", yCoordinate);
    }

    private void validateR(double rCoordinate) throws OutOfBoundCoordinates {
        if (rCoordinate < 2 || rCoordinate > 5)
            throw new OutOfBoundCoordinates("r", rCoordinate);
    }
}
