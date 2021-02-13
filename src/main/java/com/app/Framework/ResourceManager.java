package com.app.Framework;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * @author Ganesh Tiwari @@ gtiwari333@gmail.com <br/>
 * Created on : Mar 19, 2012<br/>
 * Copyright : <a
 * href="http://ganeshtiwaridotcomdotnp.blogspot.com">Ganesh Tiwari </a>
 */
public class ResourceManager {

    public static final String resourceMapFile = "string-resource.ini";
    public static final String configMapFile = "config.ini";
    private static final String a = "gt?Pass,e#. ";
    private static ResourceManager reader;
    private static Map<String, String> stringConstantsMap;
    private static Map<String, String> configParamMap;

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
