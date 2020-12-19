package Services;

import Framework.Service;
import View.Home;
import View.MainFrame;
import View.MenuBar;
import View.SwingModules.PageBtn;

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

    /**
     * Get page Jpanel
     *
     * @param name
     * @return
     */
    public JPanel getPage(String name) {
        return views.get(name);
    }

    /**
     * Add a page for a later use
     *
     * @param jPanel
     * @param name
     */
    public void addPage(JPanel jPanel, String name, PageBtn pageBtn) {
        if (null != pageBtn) {
            home.registerPage(name, pageBtn);
        }
        if (jPanel == null) {
            return;
        }
        if (jPanel.getParent() == null) {
            mainFrame.add(jPanel, name);
        }
        views.put(name, jPanel);
    }

    /**
     * Open and create the page
     * @param jPanel
     * @param name
     */
    public void openPage(JPanel jPanel, String name) {
        this.addPage(jPanel, name, null);
        this.card.show(mainFrame.getContentPane(), name);
    }

    public void openHome() {
        this.card.show(this.mainFrame.getContentPane(), "Home");
        setPageTitle(null);
    }

    public void setPageTitle(String title) {
        this.mainFrame.setTitle(
            MainFrame.TITLE + (title != null ? " - " + title : "")
        );
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
