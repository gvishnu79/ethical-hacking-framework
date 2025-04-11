package com.ethicalhackingai.cli;

import com.ethicalhackingai.recon.OSFingerprintService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "osfingerprint", description = "Basic OS detection using TTL-based heuristics.")
public class OSFingerprintCommand implements Callable<Integer> {

    @Option(names = {"-t", "--target"}, required = true, description = "Target IP or host")
    private String target;

    @Override
    public Integer call() {
        OSFingerprintService service = new OSFingerprintService();
        String result = service.fingerprint(target);
        System.out.println(result);
        return 0;
    }
}
