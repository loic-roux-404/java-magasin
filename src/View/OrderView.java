package View;

import Controller.OrderController;
import Exceptions.InternalException;
import Services.Entity.Entity;
import Services.Layout;
import View.SwingModules.Form;
import View.SwingModules.FormBuilder;
import View.SwingModules.List;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class OrderView {
    // Table config
    static String[] tableColumn = {"ID", "STATUS", "NOM", "PRENOM", "BRAND", "MODEL", "BUILDER"};
    public static String NO_SELECT = "Sélectionner ...";

    OrderController controller;
    static final String ADD = "order_add";
    static final String LIST = "order_list";

    private final JTextField id = new JTextField(25);
    private final JComboBox clientSelect = new JComboBox();
    private final JComboBox carSelect = new JComboBox();

    // Components
    public Form orderForm;
    public List orderList;

    public OrderView(Layout ly, OrderController controller) throws HeadlessException, InternalException {
        this.controller = controller;
        // Components
        orderForm = this.CREATE();
        orderList = this.LIST();

        orderForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.ORDERS).onOpen(e -> {
            this.fillForm();
            ly.openPage(orderForm.getPanel(), ADD);
        });
        // switch view according to its constraints on click
        orderForm.list(e -> ly.openPage(orderList, LIST));
        orderList.backButton.onClick(e -> ly.openPage(orderForm.getPanel(), ADD));
    }

    public View.SwingModules.List LIST() {
        return new List(tableColumn);
    }

    public void fillForm() {
        clientSelect.removeAllItems();
        carSelect.removeAllItems();
        clientSelect.addItem(NO_SELECT);
        carSelect.addItem(NO_SELECT);
        // The solution
        for (Entity client : controller.clients) {
            clientSelect.addItem(client);
        }

        for (Entity car : controller.cars) {
            carSelect.addItem(car);
        }
    }

    public Form CREATE() {
        FormBuilder builder = (new FormBuilder(true))
            .addField("client", clientSelect)
            .addField("voiture", carSelect)
            .addField("id", id);

        return builder.create(Optional.empty());
    }

    public String getId() {
        return id.getText();
    }

    public JComboBox getClientSelect() {
        return clientSelect;
    }

    public JComboBox getCarSelect() {
        return carSelect;
    }
}
