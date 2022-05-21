package com.example.WebLab3.io;

import java.io.PrintStream;
import java.io.PrintWriter;

import static com.example.WebLab3.io.TextColors.*;

public class PrintAnimator implements AutoCloseable{

    private final PrintWriter printWriter;

    public PrintAnimator(PrintStream printStream) {
        printWriter = new PrintWriter(printStream);
    }

    public void print(String message) {
        printWriter.print(message);
    }

    public void printSuccess(String message) {
        printWriter.print(ANSI_GREEN + message + ANSI_RESET);
    }

    public void printWarning(String message) {
        printWriter.print(ANSI_YELLOW + message + ANSI_RESET);
    }

    public void printError(String message) {
        printWriter.print(ANSI_RED + message + ANSI_RESET);
    }

    @Override
    public void close() {
        printWriter.close();
    }
}
