package com.ethicalhackingai.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FgsmService {

    public List<Double> performAttack(List<Double> input, List<Double> gradient, double epsilon) {
        for (int i = 0; i < input.size(); i++) {
            double perturbed = input.get(i) + epsilon * Math.signum(gradient.get(i));
            input.set(i, Math.min(1.0, Math.max(0.0, perturbed))); // clip between 0 and 1
        }
        return input;
    }

    public void saveAsImage(List<Double> pixelValues, int width, int height, String outputPath) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < pixelValues.size(); i++) {
            int x = i % width;
            int y = i / width;
            int gray = (int) (pixelValues.get(i) * 255);
            int rgb = (gray << 16) | (gray << 8) | gray;
            image.setRGB(x, y, rgb);
        }

        ImageIO.write(image, "png", new File(outputPath));
    }
}
