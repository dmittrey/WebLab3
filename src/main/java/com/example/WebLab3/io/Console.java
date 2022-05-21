package com.example.WebLab3.io;

import java.util.Scanner;

public class Console implements AutoCloseable {

    private final Scanner scanner;

    public Console(Scanner aScanner) {
        scanner = aScanner;
    }

    public double readDouble(String message) {
        System.out.println(message);

        return Double.parseDouble(scanner.next("\\d+(\\.\\d+)?"));
    }

    @Override
    public void close() {
        scanner.close();
    }
}
