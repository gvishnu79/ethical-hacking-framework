package com.ethicalhackingai.service;

import com.ethicalhackingai.model.ScanResult;
import com.ethicalhackingai.recon.BannerGrabber;
import com.ethicalhackingai.recon.SynScanner;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PortScannerService {
    private final int timeout;
    private final int threads;
    private final boolean synScan;
    private final String format;

    public PortScannerService(int timeout, int threads, boolean synScan, String format) {
        this.timeout = timeout;
        this.threads = threads;
        this.synScan = synScan;
        this.format = format.toLowerCase();
    }

    public void scan(String host, int startPort, int endPort) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<ScanResult>> futures = new ArrayList<>();

        for (int port = startPort; port <= endPort; port++) {
            final int p = port;
            futures.add(executor.submit(() -> {
                if (synScan) {
                    boolean isOpen = SynScanner.isPortOpen(host, p);
                    return new ScanResult(p, isOpen, isOpen ? BannerGrabber.grab(host, p) : null);
                } else {
                    try (Socket socket = new Socket()) {
                        socket.connect(new InetSocketAddress(host, p), timeout);
                        String banner = BannerGrabber.grab(host, p);
                        return new ScanResult(p, true, banner);
                    } catch (IOException e) {
                        return new ScanResult(p, false, null);
                    }
                }
            }));
        }

        executor.shutdown();
        List<ScanResult> results = new ArrayList<>();
        for (Future<ScanResult> f : futures) {
            try {
                results.add(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        outputResults(results);
    }

    private void outputResults(List<ScanResult> results) throws IOException {
        switch (format) {
            case "json":
                ObjectMapper mapper = new ObjectMapper();
                String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
                System.out.println(jsonOutput);
                break;

            case "csv":
                try (FileWriter writer = new FileWriter("scan_results.csv")) {
                    writer.write("Port,Open,Banner\n");
                    for (ScanResult r : results) {
                        writer.write(String.format("%d,%b,%s%n", r.getPort(), r.isOpen(), r.getBanner() != null ? r.getBanner().replace(",", " ") : ""));
                    }
                }
                System.out.println("[*] Results saved to scan_results.csv");
                break;

            default:
                for (ScanResult r : results) {
                    if (r.isOpen()) {
                        System.out.printf("Port %d OPEN\tBanner: %s%n", r.getPort(), r.getBanner());
                    }
                }
        }
    }
}
