package View;

import Controller.OrderController;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class OrderView {
    static String[] tableColumn = {"ID", "CAR", "CLIENT"};

    static final String ADD = "order_add";
    static final String LIST = "order_list";

    public OrderView(CardLayout cardLayout, MainFrame mainFrame, Home home) throws HeadlessException {
        Form orderForm = this.CREATE();
        View.SwingModules.List orderList = this.LIST();
        // initialize user controller
        new OrderController(orderForm, orderList);

        orderForm.getBackButton().onClick(e -> cardLayout.show(mainFrame.getContentPane(), "Home"));
        // adds view to card layout with unique constraints
        mainFrame.add(orderForm.getPanel(), ADD);
        mainFrame.add(orderList, LIST);
        // Home access
        home.ordersPage(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
        // switch view according to its constraints on click
        orderForm.list(e -> cardLayout.show(mainFrame.getContentPane(), LIST));
        orderList.backButton.onClick(e -> cardLayout.show(mainFrame.getContentPane(), ADD));
    }

    public View.SwingModules.List LIST() {
        return new List(tableColumn);
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
                .addField("id", new JTextField(25))
                .addField("voiture", new JTextField(25))
                .addField("client", new JTextField(25));

        return builder.create(Optional.empty());
    }
}
