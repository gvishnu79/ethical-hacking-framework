package com.ethicalhackingai.cli;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "fgsm",
        description = "Run Fast Gradient Sign Method (FGSM) attack.",
        mixinStandardHelpOptions = true
)
public class CommandParser implements Callable<Integer> {

    @CommandLine.Option(names = {"-i", "--input"}, required = true, description = "Original input values (comma-separated)")
    private String input;

    @CommandLine.Option(names = {"-g", "--gradient"}, required = true, description = "Gradient values (comma-separated)")
    private String gradient;

    @CommandLine.Option(names = {"-e", "--epsilon"}, required = true, description = "Perturbation epsilon")
    private double epsilon;

    @Override
    public Integer call() {
        String[] inputArr = input.split(",");
        String[] gradArr = gradient.split(",");

        if (inputArr.length != gradArr.length) {
            System.out.println("Input and gradient must be of same length");
            return 1;
        }

        System.out.println("⚔️  Performing FGSM attack...");
        for (int i = 0; i < inputArr.length; i++) {
            double x = Double.parseDouble(inputArr[i]);
            double grad = Double.parseDouble(gradArr[i]);
            double adversarial = x + epsilon * Math.signum(grad);
            System.out.printf("Original: %.4f, Gradient: %.4f => Adversarial: %.4f\n", x, grad, adversarial);
        }

        return 0;
    }
}
