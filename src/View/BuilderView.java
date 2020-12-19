package View;

import Controller.BuilderController;
import Controller.CarController;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;
import View.SwingModules.PageBtn;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class BuilderView {
    static final String ADD = "builder_add";
    static final String LIST = "builder_list";
    // Entity fields to show
    static String[] tableColumn = {"NOM"};

    private JTextField nameField;
    public PageBtn carPageBtn = new PageBtn("Ajouter une voiture");

    // TODO select from cars
    // private JTextField lastNameField;

    // Components
    public Form builderCreateForm = this.CREATE();
    public List builderList = this.LIST();

    public BuilderView(Layout ly, BuilderController controller) throws HeadlessException {
        builderCreateForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.BUILDERS).onOpen(e -> {
            ly.openPage(builderCreateForm.getPanel(), ADD);
            ly.setPageTitle(BuilderController.TITLE);
        });
        // switch view according to its constraints on click
        carPageBtn.onOpen(e -> {
            ly.openPage(ly.getPage(CarView.ADD), CarView.ADD);
            ly.setPageTitle(CarController.TITLE_ADD);
        });
        builderCreateForm.list(e -> {
            ly.openPage(builderList, LIST);
            ly.setPageTitle(BuilderController.TITLE);
        });
        builderList.backButton.onClick(e -> {
            ly.openPage(builderCreateForm.getPanel(), ADD);
            ly.setPageTitle(BuilderController.TITLE);
        });
    }

    public Form CREATE() {
        nameField = new JTextField(25);
        FormBuilder builder = (new FormBuilder(true))
            .addField("nom", nameField)
            .addButton("cars", carPageBtn.getBtn());

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