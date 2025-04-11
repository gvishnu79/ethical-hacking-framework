package com.ethicalhackingai.cli;

import com.ethicalhackingai.service.FgsmService;
import com.ethicalhackingai.utils.ImageLoader;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "fgsm", description = "Run Fast Gradient Sign Method (FGSM) attack.")
public class FgsmCommand implements Callable<Integer> {

    @Option(names = {"-f", "--file"}, description = "Path to input image (grayscale PNG)")
    private String imagePath;

    @Option(names = {"-e", "--epsilon"}, required = true, description = "Perturbation epsilon")
    private double epsilon;

    @Option(names = {"-o", "--output"}, description = "Path to save adversarial image (PNG)")
    private String outputPath;

    private final FgsmService fgsmService = new FgsmService();

    @Override
    public Integer call() {
        try {
            if (imagePath == null) {
                System.err.println("Please provide input image using -f option.");
                return 1;
            }

            // Load image as normalized vector
            List<Double> input = ImageLoader.loadImage(imagePath);

            // Auto-generate dummy gradient (same size as input)
            List<Double> gradient = input.stream().map(v -> 1.0).toList(); // sign(1.0) == 1

            // Perform FGSM attack
            List<Double> result = fgsmService.performAttack(input, gradient, epsilon);

            // Save image if outputPath provided
            if (outputPath != null) {
                int width = ImageLoader.getWidth(imagePath);
                int height = ImageLoader.getHeight(imagePath);
                fgsmService.saveAsImage(result, width, height, outputPath);
                System.out.println("Adversarial image saved to: " + outputPath);
            } else {
                System.out.println("Adversarial Output Vector: " + result);
            }

            return 0;
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            return 1;
        }
    }
}
