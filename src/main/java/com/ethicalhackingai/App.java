package com.ethicalhackingai;

import com.ethicalhackingai.cli.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "ethicalhackingai",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Ethical Hacking AI CLI Toolkit",
        subcommands = {
                FgsmCommand.class,
                PortScanCommand.class,
                SubdomainCommand.class,
                OSFingerprintCommand.class,
                SynScanCommand.class
        }
)
public class App implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("\n=== Welcome to Ethical Hacking AI CLI Toolkit ===");
        System.out.println("Available subcommands:");
        System.out.println("  fgsm           Run Fast Gradient Sign Method (FGSM) attack");
        System.out.println("  portscan       Scan ports on a target host");
        System.out.println("  synscan        Perform a SYN (half-open) scan");
        System.out.println("  subdomain      Brute-force subdomain scanning");
        System.out.println("  osfingerprint  Basic OS detection using TTL-based heuristics");
        System.out.println("\nUse '--help' with any subcommand for detailed options.\n");
    }
}
