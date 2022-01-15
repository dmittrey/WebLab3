package com.example.WebLab3.interfaces;

public interface ValuesFormatter<T, S, U> {

    String getTableValue(T srcValue);

    String getUpperCaseResult(S result);

    String getJSONForm(U srcValue);
}