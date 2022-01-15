package com.example.WebLab3.dto;

import lombok.Data;

@Data
public class ValidationResult {

    private final boolean validationStatus;
    private String validationMessage;
}
