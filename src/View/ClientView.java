package View;

import Controller.AbstractController;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.util.Optional;

public class ClientView {

    static String[] tableColumn = {"FIRST NAME", "LAST NAME"};

    static final String ADD = "client_add";
    static final String LIST = "client_list";

    private JTextField firstNameField;
    private JTextField lastNameField;

    // Components
    public Form createForm = this.CREATE();
    public List list = this.LIST();

    public ClientView(Layout ly, AbstractController controller) {
        createForm.getBackButton().onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        ly.mainFrame.add(createForm.getPanel(), ADD);
        ly.mainFrame.add(list, LIST);
        // Home access
        ly.home.usersPage(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        createForm.list(e -> ly.card.show(ly.mainFrame.getContentPane(), LIST));
        list.backButton.onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
    }

    public View.SwingModules.List LIST() {
        return new List(tableColumn);
    }

    public Form CREATE() {
        firstNameField = new JTextField(25);
        lastNameField = new JTextField(25);
        return (new FormBuilder(true))
                .addField("nom", firstNameField)
                .addField("prenom", lastNameField)
                .create(Optional.empty());
    }

    // getters
    public String getFirstname() {
        return firstNameField.getText();
    }

    public String getLastname() {
        return lastNameField.getText();
    }
}