package com.ethicalhackingai.recon;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class BannerGrabber {
    public static String grab(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(2000); // Slightly longer timeout for slower servers

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // Try sending HEAD or generic probe depending on common port
            String probe = "HEAD / HTTP/1.1\r\nHost: " + host + "\r\n\r\n";
            out.write(probe.getBytes());
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && response.length() < 1024) {
                response.append(line).append("\n");
            }

            return response.toString().trim().isEmpty() ? "No banner" : response.toString().trim();
        } catch (Exception e) {
            return "No banner";
        }
    }
}
