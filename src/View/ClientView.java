package View;

import Controller.AbstractController;
import Controller.ClientController;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;

public class ClientView {

    static String[] tableColumn = {"FIRST NAME", "LAST NAME"};

    static final String ADD = "client_add";
    static final String LIST = "client_list";

    private JTextField firstNameField;
    private JTextField lastNameField;

    // Components
    public Form createForm = this.CREATE();
    public List list = (this.LIST()).create(null);

    public ClientView(Layout ly, AbstractController controller) {
        createForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.CLIENTS).onOpen(e -> {
            ly.openPage(createForm.getPanel(), ADD);
            ly.setPageTitle(ClientController.TITLE);
        });
        // switch view according to its constraints on click
        createForm.list(e -> {
            ly.openPage(list, LIST);
            ly.setPageTitle(ClientController.TITLE);
        });
        // Button
        list.backButton.onClick(e -> {
            ly.openPage(createForm.getPanel(), ADD);
            ly.setPageTitle(ClientController.TITLE);
        });
    }

    public List LIST() {
        return new List(tableColumn);
    }

    public Form CREATE() {
        firstNameField = new JTextField(25);
        lastNameField = new JTextField(25);
        return (new FormBuilder(true))
            .addField("nom", firstNameField)
            .addField("prenom", lastNameField)
            .create(null);
    }

    // getters
    public String getFirstname() {
        return firstNameField.getText();
    }

    public String getLastname() {
        return lastNameField.getText();
    }
}
