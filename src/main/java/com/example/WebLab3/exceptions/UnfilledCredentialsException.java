package com.example.WebLab3.exceptions;

import java.io.IOException;

public class UnfilledCredentialsException extends IOException {

    public UnfilledCredentialsException(String loginEnvName, String passwordEnvName) {
        super("Credentials aren't filled! Check env variables " + loginEnvName + " and " + passwordEnvName + "!");
    }

    public UnfilledCredentialsException(String message) {
        super(message);
    }

    public UnfilledCredentialsException() {
        super("Credentials aren't filled!");
    }
}
