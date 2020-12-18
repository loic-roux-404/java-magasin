package View;

import Controller.*;
import Exceptions.InternalException;
import Exceptions.ServiceNotLoadedException;
import Framework.Listener;
import Framework.Registery;
import Framework.Service;
import Services.Layout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFrame extends JFrame {

    // Card layout for switching view
    public Layout layout = new Layout(this);

    public Registery registery;
    // viewRegisery
    public ArrayList<JPanel> views = new ArrayList<>();

    public MainFrame() {
        super("Automobile Market");

        this.init();
        // icon for our application
        ImageIcon imageIcon = new ImageIcon("appicon.png");
        setIconImage(imageIcon.getImage());
        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
            new ClientController(registery);
            new CarController(registery);
            new CarController(registery);
            new OrderController(registery);
            new BuilderController(registery);
            new ShopController(registery);
            // Listeners
            this.callListeners();
        } catch (InternalException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void loadServices() throws ServiceNotLoadedException {
        registery = new Registery(null);
        registery.add(Layout.NAME, layout);
    }

    protected void callListeners() {
        for (HashMap.Entry<String, Service> service : registery.services.entrySet()) {
            if (service instanceof Listener) {
                ((Listener) service).call();
            }
        }
    }

    protected void createMenu() {

        setJMenuBar(layout.menuBar);

        // menu listeners :
        layout.menuBar.jMenuItemQuit.addActionListener((ActionEvent ev) ->
        {
            if (confirmBeforeExit()) {
                {
                    System.exit(0);
                }
            }
        });
        /**
         * TODO show dialog with infos about project
         */
        /* menuBar.jMenuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
               aboutdDialog.setVisible(true);
            }
        }); */
    }

    /**
     * Show confirm dialog before closing the window.
     *
     * @return boolean true user answer Yes.
     */
    public boolean confirmBeforeExit() {
        return JOptionPane.showConfirmDialog(this, "Exit ?", "No", JOptionPane.YES_NO_OPTION) == 0;
    }
}
