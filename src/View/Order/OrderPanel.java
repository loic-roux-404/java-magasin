package View.Order;

import Controller.OrderController;
import View.Home;
import View.MainFrame;

import java.awt.*;

public class OrderPanel {

    static final String ADD = "order_add";
    static final String LIST = "order_list";

    public OrderPanel(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        OrderForm orderForm = new OrderForm();
        OrderDetails orderDetails = new OrderDetails();
        // initialize user controller
        new OrderController(orderForm, orderDetails);

        orderForm.backButton.onBackButton(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        mainFrame.add(orderForm, ADD);
        mainFrame.add(orderDetails, LIST);
        // Home access
        home.ordersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        orderForm.viewOrders(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
        orderDetails.backButton.onBackButton(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
    }
}
