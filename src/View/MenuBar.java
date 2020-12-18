package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * MenuBar class.
 */
public class MenuBar extends JMenuBar {
    // file :
    JMenu jMenuFile = new JMenu("Menu");
    // JMenuItem jMenuItemFrame1 = new JMenuItem("Clients");
    JMenuItem jMenuItemQuit = new JMenuItem("Quitter");

    // help :
    JMenu jMenuHelp = new JMenu("Aide");
    JMenuItem jMenuItemAbout = new JMenuItem("à propos");

    /**
     * Constructor.
     */
    public MenuBar() {
        // Design
        this.setForeground(Color.lightGray);
        // file :
        add(jMenuFile);
        this.designMenuItem(jMenuFile);

        jMenuFile.setMnemonic(KeyEvent.VK_F);

        // jMenuItemFrame1.setAccelerator(KeyStroke.getKeyStroke('R'));
        // jMenuFile.add(jMenuItemFrame1);

        jMenuFile.addSeparator();

        jMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q'));
        jMenuFile.add(jMenuItemQuit);
        this.designMenuItem(jMenuItemQuit);

        // help :
        add(jMenuHelp);
        jMenuHelp.setMnemonic(KeyEvent.VK_H);
        this.designMenuItem(jMenuHelp);

        jMenuItemAbout.setAccelerator(KeyStroke.getKeyStroke('A'));
        jMenuHelp.add(jMenuItemAbout);
        this.designMenuItem(jMenuItemAbout);
    }

    protected void designMenuItem(JMenuItem menuItem) {
        menuItem.setBackground(Color.gray);
        menuItem.setForeground(Color.black);
    }
}