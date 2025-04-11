package com.ethicalhackingai.recon;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

public class PortScanner {
    private final int threadCount;
    private final int timeout;

    public PortScanner(int threadCount, int timeout) {
        this.threadCount = threadCount;
        this.timeout = timeout;
    }

    public void scan(String host, int startPort, int endPort) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int port = startPort; port <= endPort; port++) {
            final int currentPort = port;
            executor.submit(() -> {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, currentPort), timeout);
                    System.out.printf("Port %d is open%n", currentPort);
                } catch (Exception ignored) {
                    // Port is closed or filtered
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}