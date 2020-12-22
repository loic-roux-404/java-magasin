package View;

import View.SwingModules.*;
import Controller.BuilderController;
import Controller.CarController;
import Exceptions.InternalException;
import Services.Entity.Entity;
import Services.Layout;

import javax.swing.*;
import java.util.ArrayList;

public class CarView {
    static String[] tableColumn = {"Modèle", "Marque", "Longueur", "Taille", "Prix"};
    static final String LIST = "car_list";
    static final String ADD = "car_add";

    // Core
    public PageBtn carPageBtn = new PageBtn("Ajouter une voiture");

    public JTextField modelName = new JTextField(25);;
    public JTextField brandName = new JTextField(25);;
    public JTextField price = new JTextField(25);;
    public JSpinner length = (new NumberField()).getField();
    public JSpinner weight = (new NumberField()).getField();
    public JComboBox builderSelect = new JComboBox();

    // Components
    public List carList = new List(tableColumn);
    public Form carAdd = this.CREATE();

    public CarView(Layout ly, CarController controller) throws InternalException {
        controller.getLayout().addPage(carAdd.getPanel(), ADD, carPageBtn);

        // Home access
        ly.home.page(Home.CARS).onOpen(e -> {
            ly.openPage(carList, LIST);
            ly.setPageTitle(CarController.TITLE);
        });
        // Functional
        carPageBtn.onOpen(e -> {
            try {
                this.fillForm(controller.getBuilders());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
        // Warning : we open car form from builder
        carList.backButton.onClick(e -> ly.openHome());
        carAdd.getBackButton().onClick(e -> {
            ly.openPage(ly.getPage(BuilderView.ADD), BuilderView.ADD);
            ly.setPageTitle(BuilderController.TITLE);
        });
    }

    public void fillForm(ArrayList<Entity> builders) {
        builderSelect.removeAllItems();
        builderSelect.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (Entity builder : builders) {
            builderSelect.addItem(builder);
        }
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Constructeur", builderSelect)
            .addField("Modèle", modelName)
            .addField("Marque", brandName)
            .addField("Longueur", length)
            .addField("Poid", weight)
            .addField("Prix", price)
            .disableListBtn();

        return builder.create(null);
    }
}
