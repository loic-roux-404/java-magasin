package View;

import Controller.BuilderController;
import Controller.CarController;
import Exceptions.InternalException;
import Services.Layout;
import View.SwingModules.*;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;

public class BuilderView {
    static final String ADD = "builder_add";
    static final String LIST = "builder_list";
    // Entity fields to show
    static String[] tableColumn = {"NOM", "Modèles de voiture pris en charge"};

    // Form fields
    private JTextField nameField;
    private JSpinner capacity = (new NumberField()).getField();

    // TODO select from cars
    // private JTextField lastNameField;
    // Paging
    private PageBtn carPageBtn;
    // Components
    public Form builderCreateForm;
    public List builderList;

    public BuilderView(Layout ly, BuilderController controller) throws HeadlessException, InternalException {
        // Recuperate the page object
        carPageBtn = controller.getLayout().home.page(CarView.ADD);

        builderCreateForm = this.CREATE();
        builderList = this.LIST();

        builderCreateForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.BUILDERS).onOpen(e -> {
            ly.openPage(builderCreateForm.getPanel(), ADD);
            ly.setPageTitle(BuilderController.TITLE);
        });
        // switch view according to its constraints on click
        controller.getLayout().home.page(CarView.ADD).onOpen(e -> {
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
            .addField("nom_constructeur", nameField)
            .addField("capacité", capacity)
            .addButton("cars", carPageBtn.getBtn());

        return builder.create(null);
    }

    public List LIST() {
        return new List(tableColumn);
    }

    // getters
    public String getName() {
        return nameField.getText();
    }

    public int getCapacity() {
        return (int) capacity.getValue();
    }
}