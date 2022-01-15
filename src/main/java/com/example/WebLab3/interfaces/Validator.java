package com.example.WebLab3.interfaces;

import com.example.WebLab3.dto.ValidationResult;

public interface Validator<T> {

    ValidationResult validate(T obj);
}
