package View;

import Controller.AbstractController;
import Controller.BuilderController;
import Services.Layout;
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

    // Components
    public Form builderCreateForm = this.CREATE();
    public List builderList = this.LIST();

    public BuilderView(Layout ly, BuilderController controller) throws HeadlessException {
        builderCreateForm.getBackButton().onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        ly.mainFrame.add(builderCreateForm.getPanel(), ADD);
        ly.mainFrame.add(builderList, LIST);
        // Home access
        ly.home.buildersPage(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        builderCreateForm.list(e -> ly.card.show(ly.mainFrame.getContentPane(), LIST));
        builderList.backButton.onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), ADD));
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