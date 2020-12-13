package View;

import Controller.BuilderController;
import View.SwingModules.List;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class BuilderView {
    static final String ADD = "builder_add";
    static final String LIST = "builder_list";
    // Entity fields to show
    static String[] tableColumn = {"NOM"};

    private JTextField nameField;
    // TODO select from cars
    // private JTextField lastNameField;

    public BuilderView(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        Form builderCreateForm = this.CREATE();
        List builderList = this.LIST();
        // initialize user controller
        new BuilderController(builderCreateForm, builderList);

        builderCreateForm.getBackButton().onClick(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        mainFrame.add(builderCreateForm.getPanel(), ADD);
        mainFrame.add(builderList, LIST);
        // Home access
        home.buildersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        builderCreateForm.list(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
        builderList.backButton.onClick(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
    }

    public Form CREATE() {
        nameField = new JTextField(25);
        FormBuilder builder = (new FormBuilder(true))
                .addField("nom", nameField);

        return builder.create(Optional.empty());
    }

    public List LIST() {
        return new List(tableColumn);
    }

    // getters
    public String getName() {
        return nameField.getText();
    }
}