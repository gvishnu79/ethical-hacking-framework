package com.ethicalhackingai.service;

public class LoggingService {
    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }

    public static void logError(String errorMessage) {
        System.err.println("[ERROR] " + errorMessage);
    }

    public static void logError(String errorMessage, Throwable throwable) {
        System.err.println("[ERROR] " + errorMessage);
        throwable.printStackTrace();
    }
}