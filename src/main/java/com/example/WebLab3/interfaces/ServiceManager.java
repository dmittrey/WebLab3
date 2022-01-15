package com.example.WebLab3.interfaces;

public interface ServiceManager<T> {

    boolean serviceWithValidation(T obj);

    void serviceWithoutValidation(T obj);
}
