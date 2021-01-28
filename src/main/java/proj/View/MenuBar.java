package main.java.proj.View;

import main.java.proj.Services.Fixtures;
import main.java.proj.View.SwingModules.FormBuilder;
import main.java.proj.View.SwingModules.LabelBuilder;
import main.java.proj.View.SwingModules.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * MenuBar class.
 */
public class MenuBar extends JMenuBar {
    // file :
    JMenu jMenuFile = new JMenu("Menu");

    JMenuItem jMenuItemQuit = new JMenuItem("Quitter");
    JMenuItem jMenuItemHome = new JMenuItem("Accueil");
    JMenuItem jMenuItemDemo = new JMenuItem(Fixtures.DEMO_TEXT_ON);
    JMenuItem jMenuItemShortcuts = new JMenuItem("Raccourcis");

    // help :
    JMenu jMenuHelp = new JMenu("Aide");
    JMenuItem jMenuItemAbout = new JMenuItem("À propos");

    /**
     * Constructor.
     */
    public MenuBar() {
        // Design
        this.setForeground(Color.lightGray);

        this.addMenuItem(jMenuFile, jMenuItemHome, "control H");
        jMenuFile.addSeparator();

        //this.addMenuItem(jMenuFile, jMenuItemDemo, null);
        // this.addMenuItem(jMenuFile, jMenuItemQuit, "control Q");

        // help :
        this.addMenuItem(jMenuHelp, jMenuItemShortcuts, "control S");
        this.addMenuItem(jMenuHelp, jMenuItemAbout, "control A");
    }

    protected void addMenuItem(JMenu jMenu, JMenuItem item, String keyStroke) {
        if (jMenu.getParent() == null) {
            add(jMenu);
            this.designMenuItem(jMenu);
            jMenu.setMnemonic(KeyEvent.VK_F);
        }
        if (keyStroke != null) item.setAccelerator(KeyStroke.getKeyStroke(keyStroke));
        this.designMenuItem(item);
        jMenu.add(item);
    }

    protected void designMenuItem(JMenuItem menuItem) {
        menuItem.setBackground(Color.gray);
        menuItem.setForeground(Color.black);
    }

    public void demoOpen(ActionListener listener) {
        jMenuItemDemo.addActionListener(listener);
    }

    public void aboutOpen(ActionListener listener) {
        jMenuItemAbout.addActionListener(listener);
    }

    protected void handleAbout(JFrame frame) {
        FormBuilder about = (new FormBuilder(false))
            .disableAllBtn()
            /* .addField(
                "img",
                (new LabelBuilder("")).buildImage(Theme.ICON).getjLabel()
            ) */
            .addField("aboutTitle", (new LabelBuilder("À propos")).buildTitle())
            .addField("about1", new JLabel("Test java"))
            .addField("about2", new JLabel("Gestion Magasins"))
            .addField("teamTitle", (new LabelBuilder("L'équipe")).buildTitle())
            .addField("team1", new JLabel("Loic Roux\n"))
            .addField("team2", new JLabel("Julien Guillaud\n"));
        JOptionPane.showMessageDialog(
            frame,
            about.create(null).getPanel(),
            "À propos du projet",
            JOptionPane.PLAIN_MESSAGE,
            null
        );
    }

    public void helpOpen(ActionListener listener) {
        jMenuItemShortcuts.addActionListener(listener);
    }

    protected void handleHelp(JFrame frame) {
        FormBuilder about = (new FormBuilder(false))
            .disableAllBtn()
            .addField("shortcuts", (new LabelBuilder("Raccourcis claviers")).buildTitle())
            .addField("shortcuts1", new JLabel("Un raccourci clavier fonctionne en maintenant CTRL + LETTRE"))
            .addField("shortcuts2", new JLabel("Vous pouvez consulter les lettres associés dans les menus Aide et Menu"));

        JOptionPane.showMessageDialog(
            frame,
            about.create(null).getPanel(),
            "Tutoriel - raccourcis",
            JOptionPane.PLAIN_MESSAGE,
            null
        );
    }
}