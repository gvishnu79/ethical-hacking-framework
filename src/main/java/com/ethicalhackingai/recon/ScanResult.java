package com.ethicalhackingai.recon;

import java.util.HashMap;

public class ScanResult {
    private String ip;
    private HashMap<Integer, String> openPorts;  // Port -> Banner

    public void addVulnerabilityAnalysis() {
        openPorts.forEach((port, banner) -> {
            if (banner.contains("Apache 2.4.49")) {
                System.out.println("[!] CVE-2021-41773 detected on port " + port);
            }
        });
    }
}