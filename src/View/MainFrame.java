package View;

import Controller.*;
import Exceptions.InternalException;
import Exceptions.ServiceNotLoadedException;
import Framework.Registery;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainFrame extends JFrame {
    public final static String TITLE = "Automobile Market";

    // Card layout for switching view
    public Layout layout = new Layout(this);

    public Registery registery;
    // viewRegisery
    public ArrayList<JPanel> views = new ArrayList<>();

    public MainFrame() {
        super(TITLE);
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
        layout.menuBar.jMenuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File("appicon.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FormBuilder about = (new FormBuilder(false))
                    .disableAllBtn()
                    .addField("img", new JLabel(new ImageIcon(image)))
                    .addField("aboutTitle", new JLabel("À propos\n"))
                    .addField("about1", new JLabel("Cet outil de gestion permet de facilité le traitement d'imports et d'achats de voitures à notre magasin"))
                    .addField("about2", new JLabel("Ainsi en temps qu'employé vous pouvez consulter et proposer un catalogue de voiture actualisé en temps réel"))
                    .addField("about3", new JLabel("Et qui s'adapte à la disponibilité des constructeurs automobiles"))
                    .addField("teamTitle",new JLabel("L'équipe\n"))
                    .addField("team1", new JLabel("Lead developer : Loic Roux\n"))
                    .addField("team2",new JLabel("Dev expérimenté : Julien Guillaud\n"))
                    .addField("team3",new JLabel("Dev expérimenté : Achref Walcott\n"));

                JOptionPane.showMessageDialog(
                    layout.mainFrame,
                    about.create(Optional.empty()).getPanel(),
                    "À propos du projet",
                    JOptionPane.PLAIN_MESSAGE,
                    null
                );
            }
        });
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
