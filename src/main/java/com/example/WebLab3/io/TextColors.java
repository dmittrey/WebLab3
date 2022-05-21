package com.example.WebLab3.io;

import lombok.Getter;

public enum TextColors {

    ANSI_RESET("\u001B[0m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m");

    @Getter
    private final String colorCode;

    TextColors(String aColorCode) {
        colorCode = aColorCode;
    }
}
