import View.MainFrame;

import javax.swing.*;

public class Auto {
    public static void main(String[] args) {
        // Async Swing initialization
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
