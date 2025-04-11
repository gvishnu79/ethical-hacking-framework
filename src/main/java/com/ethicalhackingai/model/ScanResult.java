package com.ethicalhackingai.model;

public class ScanResult {
    private int port;
    private boolean open;
    private String banner;

    public ScanResult(int port, boolean open, String banner) {
        this.port = port;
        this.open = open;
        this.banner = banner;
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

    public void setPort(int port) {
        this.port = port;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public String toString() {
        return "Port " + port + " is " + (open ? "OPEN" : "CLOSED") +
                (banner != null && !banner.isEmpty() ? " | Banner: " + banner : "");
    }
}
