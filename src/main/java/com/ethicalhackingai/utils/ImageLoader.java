package com.ethicalhackingai.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {

    public static List<Double> loadImage(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        List<Double> pixelValues = new ArrayList<>();

        // Convert the image pixels to a list of double values (normalized to 0-1 range)
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                double normalizedPixelValue = (red + green + blue) / 3.0 / 255.0;  // Normalize to [0,1]
                pixelValues.add(normalizedPixelValue);
            }
        }

        return pixelValues;
    }

    public static int getWidth(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        return image.getWidth();
    }

    public static int getHeight(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        return image.getHeight();
    }
}
