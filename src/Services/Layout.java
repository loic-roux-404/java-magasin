package Services;

import Framework.Service;
import View.Home;
import View.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Layout implements Service {
    public static String NAME = "layout";
    private boolean loaded;
    // Main components
    public CardLayout card = new CardLayout();
    public Home home = new Home();
    public View.MenuBar menuBar = new MenuBar();
    public View.MainFrame mainFrame;

    // views map
    public HashMap<String, JPanel> views = new HashMap<>();

    public Layout(View.MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.load();
    }

    public void openPage(JPanel jPanel, String name) {
        if (jPanel.getParent() == null) {
            mainFrame.add(jPanel, name);
        }
        this.card.show(mainFrame.getContentPane(), name);
    }

    public void openHome() {
        this.card.show(this.mainFrame.getContentPane(), "Home");
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
