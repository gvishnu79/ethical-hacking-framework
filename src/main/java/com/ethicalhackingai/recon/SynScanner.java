package com.ethicalhackingai.recon;

import com.ethicalhackingai.model.ScanResult;

import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.concurrent.*;

public class SynScanner {

    // ✅ This method was missing — now added
    public static boolean isPortOpen(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 200);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ScanResult> scan(String targetIp, int startPort, int endPort, int timeout) {
        List<ScanResult> results = new CopyOnWriteArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(50);

        for (int port = startPort; port <= endPort; port++) {
            final int currentPort = port;
            executor.submit(() -> {
                boolean isOpen = false;
                String banner = null;
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(targetIp, currentPort), timeout);
                    isOpen = true;

                    // Attempt banner grabbing
                    try {
                        socket.setSoTimeout(timeout);
                        InputStream in = socket.getInputStream();
                        byte[] buffer = new byte[1024];
                        int bytesRead = in.read(buffer);
                        if (bytesRead > 0) {
                            banner = new String(buffer, 0, bytesRead).trim();
                        }
                    } catch (Exception ignored) {}

                } catch (Exception ignored) {
                    // Port is closed or filtered
                }

                ScanResult result = new ScanResult(currentPort, isOpen, banner);
                results.add(result);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return results;
    }
}
