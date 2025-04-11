package com.ethicalhackingai.cli;

import com.ethicalhackingai.recon.SubdomainScannerService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "subdomain", description = "Brute-force subdomain scanning.")
public class SubdomainCommand implements Callable<Integer> {

    @Option(names = {"-d", "--domain"}, required = true, description = "Target domain")
    private String domain;

    @Option(names = {"-w", "--wordlist"}, required = true, description = "Path to wordlist file")
    private String wordlistPath;

    private final SubdomainScannerService subdomainScannerService = new SubdomainScannerService();

    @Override
    public Integer call() {
        List<String> results = subdomainScannerService.findSubdomains(domain, wordlistPath);
        System.out.println("Discovered Subdomains:");
        results.forEach(System.out::println);
        return 0;
    }
}
