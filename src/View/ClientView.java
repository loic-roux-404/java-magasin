package View;

import Controller.ClientController;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ClientView {
    static String[] tableColumn = {"FIRST NAME", "LAST NAME"};

    static final String ADD = "client_add";
    static final String LIST = "client_list";

    private JTextField firstNameField;
    private JTextField lastNameField;

    public FormBuilder builder;

    public ClientView(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        Form clientCreateForm = this.CREATE();
        View.SwingModules.List clientDetails = this.LIST();
        // initialize user controller
        new ClientController(clientCreateForm, clientDetails, this);

        clientCreateForm.getBackButton().onClick(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        mainFrame.add(clientCreateForm.getPanel(), ADD);
        mainFrame.add(clientDetails, LIST);
        // Home access
        home.usersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        clientCreateForm.list(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
        clientDetails.backButton.onClick(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
    }

    public View.SwingModules.List LIST() {
        return new List(tableColumn);
    }

    public Form CREATE() {
        firstNameField = new JTextField(25);
        lastNameField = new JTextField(25);
        builder = (new FormBuilder(true))
                .addField("nom", firstNameField)
                .addField("prenom", lastNameField);

        return builder.create(Optional.empty());
    }

    // getters
    public String getFirstname() {
        return firstNameField.getText();
    }

    public String getLastname() {
        return lastNameField.getText();
    }
}
