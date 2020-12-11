package View.Client;

import Controller.ClientController;
import View.MainFrame;

import java.awt.*;

public class ClientPanel {


    public ClientPanel(CardLayout cardLayout, MainFrame mainFrame) throws HeadlessException {
        ClientForm clientForm = new ClientForm();
        ClientDetails clientDetails = new ClientDetails();
        // initialize user controller
        new ClientController(clientForm, clientDetails);

        // adds view to card layout with unique constraints
        mainFrame.add(clientForm, "form");
        mainFrame.add(clientDetails, "User details");
        // switch view according to its constraints on click
        clientForm.viewUsers(e -> cardLayout.show(mainFrame.getContentPane(), "User details"));
        clientDetails.backButton(e -> cardLayout.show(mainFrame.getContentPane(), "form"));
    }
}
