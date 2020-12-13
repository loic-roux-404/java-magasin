package View;

import Controller.CarController;
import View.SwingModules.List;

import java.awt.*;

public class CarView {
    static String[] tableColumn = {"Modele", "Marque"};
    static final String LIST = "car_list";

    public CarView(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        // initialize user controller
        View.SwingModules.List carList = new List(tableColumn);
        new CarController(carList);

        // adds view to card layout with unique constraints
        mainFrame.add(carList, LIST);
        // Home access
        home.carsPage(e -> cardLayout.show(mainFrame.getContentPane(), LIST));

        carList.backButton.onClick(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
    }

    // TODO form getters
}
