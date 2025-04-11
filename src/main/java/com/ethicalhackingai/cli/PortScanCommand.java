package com.ethicalhackingai.cli;

import com.ethicalhackingai.service.PortScannerService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "portscan",
        description = "Scan ports on a target host",
        mixinStandardHelpOptions = true
)
public class PortScanCommand implements Callable<Integer> {

    @Option(
            names = {"-h", "--host"},
            description = "Target hostname or IP address",
            required = true
    )
    private String host;

    @Option(
            names = {"-s", "--start"},
            description = "Start port (default: ${DEFAULT-VALUE})",
            defaultValue = "1"
    )
    private int startPort;

    @Option(
            names = {"-e", "--end"},
            description = "End port (default: ${DEFAULT-VALUE})",
            defaultValue = "1024"
    )
    private int endPort;

    @Option(
            names = {"-t", "--threads"},
            description = "Number of threads (default: ${DEFAULT-VALUE})",
            defaultValue = "50"
    )
    private int threads;

    @Option(
            names = {"-T", "--timeout"},
            description = "Timeout in milliseconds (default: ${DEFAULT-VALUE})",
            defaultValue = "500"
    )
    private int timeout;

    @Option(
            names = {"--syn"},
            description = "Use SYN scan mode (requires elevated privileges)"
    )
    private boolean synScan;

    @Option(
            names = {"-f", "--format"},
            description = "Output format: json, csv, or text (default: text)",
            defaultValue = "text"
    )
    private String format;

    @Override
    public Integer call() {
        try {
            System.out.printf("[*] Starting scan on host %s from port %d to %d%n", host, startPort, endPort);
            PortScannerService service = new PortScannerService(timeout, threads, synScan, format);
            service.scan(host, startPort, endPort);
            return 0;
        } catch (SecurityException se) {
            System.err.println("[!] Error: " + se.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("[!] Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return 1;
        }
    }
}
