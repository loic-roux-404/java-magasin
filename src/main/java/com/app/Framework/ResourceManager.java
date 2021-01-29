package com.app.Framework;

import com.app.Utils.CryptoUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
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

    public static synchronized ResourceManager getReader() {
        if (reader == null) {
            reader = new ResourceManager();
        }
        return reader;
    }

    public static Map<String, String> readMap(String file, boolean isEncry) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Map<String, String> map = new HashMap<>();
        String str;
        while ((str = in.readLine()) != null) {
            String[] spl = str.split(":");
            if (isEncry) {
                map.put(CryptoUtils.decryptText(spl[0].trim(), a), CryptoUtils.decryptText(spl[1].trim(), a));
            } else {
                map.put(spl[0].trim(), spl[1].trim());
            }
        }
        in.close();
        return map;
    }

    public static boolean saveMap(String file, Map<String, String> map, boolean isEncry) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (isEncry) {
                    out.write(CryptoUtils.encryptText(entry.getKey(), a) + ":" + CryptoUtils.encryptText(entry.getValue(), a) + "\n");
                } else {
                    out.write(entry.getKey() + ":" + entry.getValue());
                }

            }
            out.flush();
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    public static String getConfigParam(String key) {
        if (configParamMap == null) {
            try {
                /* config params are encrypted */
                configParamMap = readMap(configMapFile, true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cannot Read Config File" + e.getMessage(), "Severe Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return configParamMap.get(key);
    }

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

    public static void main(String[] args) {
        /* ImageIcon ic = new ImageIcon(com.gt.common.ResourceManager.class.getResource("/images/logout-on.png"));
        ImageIcon ic2 = getImageIcon("logout-on.png");
        System.out.println(ic.getIconHeight());
        System.out.println(ic2.getIconHeight()); */
    }
}
