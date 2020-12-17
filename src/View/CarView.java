package View;

import Controller.AbstractController;
import Controller.CarController;
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

        // adds view to card layout with unique constraints
        ly.mainFrame.add(carList, LIST);
        // Home access
        ly.home.carsPage(e -> ly.card.show(ly.mainFrame.getContentPane(), LIST));

        carList.backButton.onClick(e -> ly.card.show(ly.mainFrame.getContentPane(), "Home"));
    }

    // TODO form getters
}
