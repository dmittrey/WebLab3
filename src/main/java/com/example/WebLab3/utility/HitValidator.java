package com.example.WebLab3.utility;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.HitValidatorInterface;
import lombok.NoArgsConstructor;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@NoArgsConstructor
public class HitValidator implements HitValidatorInterface {

    @Override
    public boolean validate(Hit hit) {
        return validateX(hit.getX())
                & validateY(hit.getY())
                & validateR(hit.getR());
    }

    private boolean validateX(Double xCoordinate) {
        String warning;
        if (xCoordinate != null) {
            if (xCoordinate > -5 && xCoordinate < 3) return true;
            else warning = "Coordinate x is out of bounds(" + xCoordinate + ")!";
        } else warning = "Coordinate x is not exist!";
        createMessage(warning);
        return false;
    }

    private boolean validateY(Double yCoordinate) {
        String warning;
        if (yCoordinate != null) {
            if (yCoordinate > -5 && yCoordinate < 3) return true;
            else warning = "Coordinate y is out of bounds(" + yCoordinate + ")!";
        } else warning = "Coordinate y is not exist!";
        createMessage(warning);
        return false;
    }

    private boolean validateR(Double rCoordinate) {
        String warning;
        if (rCoordinate != null) {
            if (rCoordinate >= 2 && rCoordinate <= 5) return true;
            else warning = "Coordinate r is out of bounds(" + rCoordinate + ")!";
        } else warning = "Coordinate r is not exist!";
        createMessage(warning);
        return false;
    }

    private void createMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Server validation", message));
    }
}