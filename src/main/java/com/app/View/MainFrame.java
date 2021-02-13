package com.app.View;

import com.app.Controller.MagasinController;
import com.app.Exceptions.InternalException;
import com.app.Exceptions.NotLoadedException;
import com.app.Services.Fixtures;
import com.app.Services.Layout;
import com.app.View.SwingModules.Theme;
import com.sun.tools.javac.Main;
import com.app.Controller.ArticleController;
import com.app.Framework.Registery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    // Card layout for switching view
    public Layout layout = new Layout(this);

    public Registery registery;
    // viewRegisery
    public ArrayList<JPanel> views = new ArrayList<>();

    public MainFrame() {
        super(Theme.TITLE);
        setTitle(Theme.TITLE);
        // app runtime
        this.init();
        // icon for our application
        // this.setIcon(Theme.ICON);
        // size of our application frame
        setSize(Theme.FRAME_WIDTH, Theme.FRAME_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init() {
        this.createMenu();
        // Bootstrap app
        setLayout(layout.card);
        add(layout.home, "Home");
        try {
            // Core Framework.Service creation
            this.loadServices();
            // Controllers
            new ArticleController(registery);
            new MagasinController(registery);

        } catch (InternalException e) {
            e.printStackTrace();
        }
    }

    protected void loadServices() throws NotLoadedException {
        registery = new Registery(null);
        registery.add(Layout.NAME, layout);
    }

    protected void setIcon(String iconPath) {
        //loading an image from a file
        final URL imageResource = Main.class.getClassLoader().getResource(iconPath);
        final Image image = (new ImageIcon(imageResource)).getImage();

        //this is new since JDK 9
        final Taskbar taskbar = Taskbar.getTaskbar();

        try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        setIconImage(image);
    }

    protected void createMenu() {

        setJMenuBar(layout.menuBar);

        // menu listeners :
        layout.menuBar.jMenuItemQuit.addActionListener((ActionEvent ev) -> {
            if (confirmBeforeExit()) System.exit(0);
        });

        layout.menuBar.jMenuItemHome.addActionListener((ActionEvent ev) -> {
            layout.openHome();
        });

        layout.menuBar.demoOpen(e -> {
            if (confirmLoose()) return;
            this.initFixtures((JMenuItem) e.getSource());
            layout.openHome();
        });

        layout.menuBar.aboutOpen(e -> layout.menuBar.handleAbout(this));

        layout.menuBar.helpOpen(e -> layout.menuBar.handleHelp(this));
    }

    private void initFixtures(JMenuItem item) {
         try {
             if (!registery.has("fixtures")) {
                 registery.add("fixtures", new Fixtures());
             }

             Fixtures fixtures = ((Fixtures) registery.get("fixtures"));
             fixtures.bootFixtures(registery);
             // Change text
             item.setText(fixtures.demoText());
         } catch (InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show confirm dialog before closing the window.
     *
     * @return boolean true user answer Yes.
     */
    public boolean confirmBeforeExit() {
        return JOptionPane.showConfirmDialog(this, "Partir ?", "No", JOptionPane.YES_NO_OPTION) == 0;
    }

    /**
     * Show confirm dialog before closing the window.
     *
     * @return boolean true user answer Yes.
     */
    public boolean confirmLoose() {
        return JOptionPane.showConfirmDialog(
            this,
            "Attention cette action engendre la perte de vos données",
            "Confirmer",
            JOptionPane.YES_NO_OPTION
        ) == 1;
    }
}
