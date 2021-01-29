package com.app;

import javax.swing.*;
import com.app.View.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Async Swing initialization
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
