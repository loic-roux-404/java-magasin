package View;

import Services.Fixtures;
import View.SwingModules.FormBuilder;
import View.SwingModules.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * MenuBar class.
 */
public class MenuBar extends JMenuBar {
    // file :
    JMenu jMenuFile = new JMenu("Menu");
    // JMenuItem jMenuItemFrame1 = new JMenuItem("Clients");
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

        this.addMenuItem(jMenuFile, jMenuItemDemo, null);
        this.addMenuItem(jMenuFile, jMenuItemQuit, "control Q");

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
            .addField(
                "img",
                (new LabelBuilder("")).buildImage("appicon.png").getjLabel()
            )
            .addField("aboutTitle", (new LabelBuilder("À propos")).buildTitle())
            .addField("about1", new JLabel("Cet outil de gestion permet de facilité le traitement d'imports et d'achats de voitures à notre magasin"))
            .addField("about2", new JLabel("Ainsi en temps qu'employé vous pouvez consulter et proposer un catalogue de voiture actualisé en temps réel"))
            .addField("about3", new JLabel("et qui s'adapte à la disponibilité des constructeurs automobiles"))
            .addField("teamTitle", (new LabelBuilder("L'équipe")).buildTitle())
            .addField("team1", new JLabel("Lead developer : Loic Roux\n"))
            .addField("team2", new JLabel("Dev expérimenté : Julien Guillaud\n"))
            .addField("team3", new JLabel("Dev : Achref Walcott\n"));

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