package com.app.Framework;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * @author Ganesh-Tiwari (github.com/gtiwari333)
 */
public class ResourceManager {

    public static Image getImage(String fileName) {
        // src/image/
        return readImage(ResourceManager.class.getResource("/images/" + fileName).toString());
    }

    public static ImageIcon getImageIcon(String resourcePath) {
        return new ImageIcon(ResourceManager.class.getResource("/images/" + resourcePath));
    }

    public static URL getResource(String fileName) {
        return ResourceManager.class.getResource("/" + fileName);
    }

    public static BufferedImage readImage(String imageName) {
        try {
            File input = new File(imageName);
            BufferedImage image = ImageIO.read(input);
            return image;
        } catch (IOException ie) {
            System.out.println("Error:" + ie.getMessage());
        }
        return null;
    }
}
