package com.ethicalhackingai.ai;

import java.util.Arrays;

public class FgsmExecutor {

    /**
     * Generates adversarial examples using the FGSM method.
     *
     * @param originalInput The original input data.
     * @param gradient The gradient of the loss with respect to the input.
     * @param epsilon The perturbation magnitude.
     * @return Adversarial example.
     */
    public static double[] generateAdversarialExample(double[] originalInput, double[] gradient, double epsilon) {
        double[] adversarial = new double[originalInput.length];

        for (int i = 0; i < originalInput.length; i++) {
            adversarial[i] = originalInput[i] + epsilon * Math.signum(gradient[i]);
        }

        return adversarial;
    }

    // Example run
    public static void main(String[] args) {
        double[] input = {0.2, 0.4, 0.1};
        double[] gradient = {0.5, -0.3, 0.7};
        double epsilon = 0.1;

        double[] adversarial = generateAdversarialExample(input, gradient, epsilon);

        System.out.println("Original:   " + Arrays.toString(input));
        System.out.println("Gradient:   " + Arrays.toString(gradient));
        System.out.println("Adversarial:" + Arrays.toString(adversarial));
    }
}
