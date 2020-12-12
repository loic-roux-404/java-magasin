package View.Car;

import Controller.CarController;
import View.Home;
import View.MainFrame;

import java.awt.*;

public class CarPanel {

    static final String LIST = "car_list";

    public CarPanel(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        // initialize user controller
        CarDetails carDetails = new CarDetails();
        new CarController(new CarDetails());

        // adds view to card layout with unique constraints
        // mainFrame.add(clientForm, "form");
        mainFrame.add(carDetails, LIST);
        // Home access
        home.carsPage(e -> cardLayout.show(mainFrame.getContentPane(), LIST));

        carDetails.backButton.onBackButton(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // Create from home access
        // homeForm.carsPage(e -> cardLayout.show(mainFrame.getContentPane(), "Car details"));
        // switch view according to its constraints on click
        // clientForm.viewUsers(e -> cardLayout.show(mainFrame.getContentPane(), "Car details"));
    }
}
