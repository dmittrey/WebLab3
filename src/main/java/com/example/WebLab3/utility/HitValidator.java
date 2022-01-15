package com.example.WebLab3.utility;

import com.example.WebLab3.dto.ValidationResult;
import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.Validator;

public class HitValidator implements Validator<Hit> {

    @Override
    public ValidationResult validate(Hit aHit) {
        ValidationResult xValidation = validateValue(aHit.getX(), -5, 3, "X");
        if (!xValidation.isValidationStatus()) return xValidation;

        ValidationResult yValidation = validateValue(aHit.getY(), -5, 3, "Y");
        if (!yValidation.isValidationStatus()) return yValidation;

        return validateValue(aHit.getR(), 2, 5, "R");
    }

    private ValidationResult validateValue(Double aValue, int minValue, int maxValue, String valueName) {
        String message;

        if (aValue != null) {
            if (aValue > minValue && aValue < maxValue) return new ValidationResult(true);
            else message = "Coordinate " + valueName + " is out of bounds(" + aValue + ")!";
        } else message = "Coordinate " + valueName + " is not exist!";

        ValidationResult validationResult = new ValidationResult(false);
        validationResult.setValidationMessage(message);
        return validationResult;
    }
}