package com.ethicalhackingai.ai;

public class AdversarialReport {
    private String originalClass;
    private String adversarialClass;
    private double confidenceDrop;

    // Constructor, getters, and analysis methods
    public void generateReport() {
        System.out.println("[+] Attack Result:");
        System.out.println("Original: " + originalClass);
        System.out.println("Adversarial: " + adversarialClass);
        System.out.println("Confidence Drop: " + confidenceDrop + "%");
    }
}