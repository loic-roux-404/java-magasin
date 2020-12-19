package View;

import Controller.AbstractController;
import Controller.BuilderController;
import Controller.CarController;
import Exceptions.InternalException;
import Exceptions.ServiceRegisteryException;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.util.Optional;

public class CarView {
    static String[] tableColumn = {"Modèle", "Marque"};
    static final String LIST = "car_list";
    static final String ADD = "car_add";

    public JTextField modelName = new JTextField(25);;
    public JTextField brandName = new JTextField(25);;
    public JTextField length = new JTextField(25);;
    public JTextField weight = new JTextField(25);;
    // Components
    public List carList = new List(tableColumn);
    public Form carAdd = this.CREATE();

    public CarView(Layout ly, AbstractController controller) throws InternalException {
        controller.getLayout().addPage(carAdd.getPanel(), ADD);

        // Home access
        ly.home.page(Home.CARS).onOpen(e -> {
            ly.openPage(carList, LIST);
            ly.setPageTitle(CarController.TITLE);
        });
        // Warning : we open car form from builder
        carList.backButton.onClick(e -> ly.openHome());
        carAdd.getBackButton().onClick(e -> {
            ly.openPage(ly.getPage(BuilderView.ADD), BuilderView.ADD);
            ly.setPageTitle(BuilderController.TITLE);
        });
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Modèle", modelName)
            .addField("Marque", brandName)
            .addField("Longueur", length)
            .addField("Poid", weight)
            .disableListBtn();

        return builder.create(Optional.empty());
    }
}
