package View;

import Controller.*;
import Exceptions.FormException;
import Exceptions.InternalException;
import Exceptions.ServiceNotLoadedException;
import Framework.Registery;
import Services.Layout;
import View.SwingModules.FormBuilder;
import View.SwingModules.LabelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            new OrderController(registery);
            new BuilderController(registery);
        } catch (InternalException e) {
            System.err.println(e.getMessage());
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

        layout.menuBar.jMenuItemHome.addActionListener((ActionEvent ev) ->
        {
            layout.openHome();
        });

        layout.menuBar.jMenuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                FormBuilder about = (new FormBuilder(false))
                    .disableAllBtn()
                    .addField(
                        "img",
                        (new LabelBuilder("")).buildImage("appicon.png").getjLabel()
                    )
                    .addField("aboutTitle", (new LabelBuilder("À propos")).buildTitle())
                    .addField("about1", new JLabel("Cet outil de gestion permet de facilité le traitement d'imports et d'achats de voitures à notre magasin"))
                    .addField("about2", new JLabel("Ainsi en temps qu'employé vous pouvez consulter et proposer un catalogue de voiture actualisé en temps réel"))
                    .addField("about3", new JLabel("Et qui s'adapte à la disponibilité des constructeurs automobiles"))
                    .addField("teamTitle", (new LabelBuilder("L'équipe")).buildTitle())
                    .addField("team1", new JLabel("Lead developer : Loic Roux\n"))
                    .addField("team2", new JLabel("Dev expérimenté : Julien Guillaud\n"))
                    .addField("team3", new JLabel("Dev expérimenté : Achref Walcott\n"));

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
