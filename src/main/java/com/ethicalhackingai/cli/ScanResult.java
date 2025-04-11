package com.ethicalhackingai.cli;

public class ScanResult {
    private final int port;
    private final boolean open;
    private final String banner;
    private final String service;

    public ScanResult(int port, boolean open, String banner, String service) {
        this.port = port;
        this.open = open;
        this.banner = banner;
        this.service = service;
    }

    public int getPort() {
        return port;
    }

    public boolean isOpen() {
        return open;
    }

    public String getBanner() {
        return banner;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return String.format("Port %d: %s - Service: %s - Banner: %s",
                port,
                open ? "OPEN" : "CLOSED",
                service != null ? service : "Unknown",
                banner != null ? banner : "N/A");
    }
}
