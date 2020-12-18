package View;

import Controller.AbstractController;
import Exceptions.ServiceRegisteryException;
import Services.Layout;
import View.SwingModules.List;

import java.awt.*;

public class CarView {
    static String[] tableColumn = {"Modele", "Marque"};
    static final String LIST = "car_list";

    // Components
    public List carList;

    public CarView(Layout ly, AbstractController controller) throws HeadlessException, ServiceRegisteryException {
        // initialize user controller
        carList = new List(tableColumn);
        // Home access
        ly.home.page(Home.CARS).onOpen(e -> ly.openPage(carList, LIST));

        carList.backButton.onClick(e -> ly.openHome());
    }

    // TODO form getters
}
