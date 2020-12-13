package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    // Card layout for switching view
    public CardLayout cardLayout;
    private MenuBar menuBar = new MenuBar();
    public Home home = new Home();

    public MainFrame() {
        super("Automobile Market");
        this.createMenu();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        // sets our layout as a card layout
        add(home, "Home");
        new ClientView(cardLayout, this, home);
        new CarView(cardLayout, this, home);
        new OrderView(cardLayout, this, home);
        new BuilderView(cardLayout, this, home);

        // icon for our application
        ImageIcon imageIcon = new ImageIcon("appicon.png");
        setIconImage(imageIcon.getImage());
        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void createMenu() {

        setJMenuBar(menuBar);

        // menu listeners :
        menuBar.jMenuItemQuit.addActionListener((ActionEvent ev) ->
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
    public boolean confirmBeforeExit()
    {
        if (JOptionPane.showConfirmDialog(this, "Exit ?", "No", JOptionPane.YES_NO_OPTION) == 0)
        {
            return true;
        }

        return false;
    }
}
