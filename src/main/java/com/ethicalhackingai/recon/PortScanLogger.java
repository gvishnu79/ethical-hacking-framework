package com.ethicalhackingai.recon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class PortScanLogger {

    private static final String LOG_FILE = "portscan_results.txt";

    public static synchronized void log(String host, int port, String banner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("[" + LocalDateTime.now() + "] " +
                    "Host: " + host +
                    " | Port: " + port +
                    " | Banner: " + banner.replaceAll("\n", " ") + "\n");
        } catch (IOException e) {
            System.err.println("❌ Failed to log result: " + e.getMessage());
        }
    }
}
