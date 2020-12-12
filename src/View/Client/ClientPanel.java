package View.Client;

import Controller.ClientController;
import View.Home;
import View.MainFrame;

import java.awt.*;

public class ClientPanel {

    static final String ADD = "client_add";
    static final String LIST = "client_list";

    public ClientPanel(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        ClientForm clientForm = new ClientForm();
        ClientDetails clientDetails = new ClientDetails();
        // initialize user controller
        new ClientController(clientForm, clientDetails);

        clientForm.backButton.onBackButton(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        mainFrame.add(clientForm, ADD);
        mainFrame.add(clientDetails, LIST);
        // Home access
        home.usersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        clientForm.viewUsers(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
        clientDetails.backButton.onBackButton(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
    }
}
