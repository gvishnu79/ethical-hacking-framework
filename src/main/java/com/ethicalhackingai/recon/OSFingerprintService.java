package com.ethicalhackingai.recon;

import java.net.InetAddress;

public class OSFingerprintService {

    public String fingerprint(String target) {
        try {
            long start = System.currentTimeMillis();
            InetAddress inet = InetAddress.getByName(target);
            boolean reachable = inet.isReachable(3000);
            long end = System.currentTimeMillis();

            long latency = end - start;
            int ttl = getTTL(target);

            String osGuess;
            if (ttl >= 128) {
                osGuess = "Windows (TTL ≥ 128)";
            } else if (ttl >= 64) {
                osGuess = "Linux/Unix (TTL ≥ 64)";
            } else {
                osGuess = "Possibly older Unix/Linux or network filtering involved";
            }

            return String.format("Target: %s\nReachable: %s\nLatency: %dms\nTTL: %d\nLikely OS: %s",
                    target, reachable, latency, ttl, osGuess);

        } catch (Exception e) {
            return "Error fingerprinting target: " + e.getMessage();
        }
    }

    private int getTTL(String target) {
        // Dummy TTL for simulation - real method would use raw sockets or external libs
        return 64; // You can change this for demo/test purposes
    }
}
