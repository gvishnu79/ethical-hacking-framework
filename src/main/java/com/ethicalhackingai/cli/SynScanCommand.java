package com.ethicalhackingai.cli;

import com.ethicalhackingai.model.ScanResult;
import com.ethicalhackingai.recon.SynScanner;
import picocli.CommandLine.*;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "synscan", description = "Perform a SYN (half-open) scan on target host.")
public class SynScanCommand implements Callable<Integer> {

    @Option(names = {"-h", "--host"}, required = true, description = "Target host IP address.")
    private String host;

    @Option(names = {"-s", "--start"}, defaultValue = "1", description = "Start port.")
    private int startPort;

    @Option(names = {"-e", "--end"}, defaultValue = "1024", description = "End port.")
    private int endPort;

    @Option(names = {"-t", "--timeout"}, defaultValue = "200", description = "Timeout in milliseconds.")
    private int timeout;

    @Override
    public Integer call() {
        try {
            SynScanner scanner = new SynScanner();
            List<ScanResult> results = scanner.scan(host, startPort, endPort, timeout);

            for (ScanResult result : results) {
                System.out.println(result);
            }

            return 0;
        } catch (Exception e) {
            System.err.println("Error during SYN scan: " + e.getMessage());
            return 1;
        }
    }
}
