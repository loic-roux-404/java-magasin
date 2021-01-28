package main.java.proj;

import javax.swing.*;
import main.java.proj.View.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Async Swing initialization
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
