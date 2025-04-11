package com.ethicalhackingai.recon;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubdomainScannerService {

    public List<String> findSubdomains(String domain, String wordlistPath) {
        List<String> discovered = new ArrayList<>();
        File file = new File(wordlistPath);

        if (!file.exists() || !file.isFile()) {
            System.err.println("❌ Wordlist file not found: " + wordlistPath);
            return discovered;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String word;
            while ((word = br.readLine()) != null) {
                String subdomain = word.trim() + "." + domain;
                if (isAlive(subdomain)) {
                    discovered.add(subdomain);
                    System.out.println("✅ Found: " + subdomain);
                } else {
                    System.out.println("❌ Not found: " + subdomain);
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ Error reading wordlist: " + e.getMessage());
        }

        return discovered;
    }

    private boolean isAlive(String subdomain) {
        try {
            URL url = new URL("http://" + subdomain);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            return code >= 200 && code < 400;
        } catch (IOException e) {
            return false;
        }
    }
}
