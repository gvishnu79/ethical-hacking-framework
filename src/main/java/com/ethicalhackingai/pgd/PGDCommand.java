package com.ethicalhackingai.pgd;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "pgd", description = "Run Projected Gradient Descent (PGD) attack.")
public class PGDCommand implements Callable<Integer> {

    @Option(names = {"-i", "--input"}, required = true, description = "Original input values (comma-separated)")
    private String input;

    @Option(names = {"-g", "--gradient"}, required = true, description = "Gradient values (comma-separated)")
    private String gradient;

    @Option(names = {"-e", "--epsilon"}, required = true, description = "Perturbation epsilon")
    private double epsilon;

    @Option(names = {"-a", "--alpha"}, required = true, description = "Step size alpha")
    private double alpha;

    @Option(names = {"-n", "--iterations"}, required = true, description = "Number of iterations")
    private int iterations;

    @Override
    public Integer call() {
        double[] inputArr = Arrays.stream(input.split(",")).mapToDouble(Double::parseDouble).toArray();
        double[] gradientArr = Arrays.stream(gradient.split(",")).mapToDouble(Double::parseDouble).toArray();

        if (inputArr.length != gradientArr.length) {
            System.err.println("Input and gradient lengths must match.");
            return 1;
        }

        double[] adversarial = Arrays.copyOf(inputArr, inputArr.length);

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < adversarial.length; j++) {
                adversarial[j] += alpha * Math.signum(gradientArr[j]);
                double diff = adversarial[j] - inputArr[j];
                // Project back into the epsilon-ball
                if (Math.abs(diff) > epsilon) {
                    adversarial[j] = inputArr[j] + epsilon * Math.signum(diff);
                }
            }
        }

        System.out.println("PGD adversarial output: " + Arrays.toString(adversarial));
        return 0;
    }
}
