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
import java.awt.event.ActionListener;

public class OrderView {
    // Table config
    static String[] tableColumn = {"ID", "STATUS", "NOM", "PRENOM", "MARQUE", "MODELE", "CONSTRUCTEUR"};

    OrderController controller;
    static final String ADD = "order_add";
    static final String LIST = "order_list";

    private final JTextField id = new JTextField(25);
    private final JComboBox clientSelect = new JComboBox();
    private final JComboBox carSelect = new JComboBox();

    // Special buttons
    private JButton validOrderBtn = new JButton("Valider la commande");
    private JButton cancelOrderBtn = new JButton("Refuser la commande");

    // Components
    public Form orderForm;
    public List orderList;

    public OrderView(Layout ly, OrderController controller) throws HeadlessException, InternalException {
        this.controller = controller;
        // Components
        orderForm = this.CREATE();
        orderList = (this.LIST())
            .addButton(validOrderBtn)
            .addButton(cancelOrderBtn)
            .disableDeleteButton()
            .create(null);

        orderForm.getBackButton().onClick(e -> ly.openHome());
        // Home access
        ly.home.page(Home.ORDERS).onOpen(e -> {
            this.fillForm();
            ly.openPage(orderForm.getPanel(), ADD);
            ly.setPageTitle(OrderController.TITLE);
        });
        // switch view according to its constraints on click
        orderForm.list(e -> {
            ly.openPage(orderList, LIST);
            ly.setPageTitle(OrderController.TITLE);
        });
        orderList.backButton.onClick(e -> {
            ly.openPage(orderForm.getPanel(), ADD);
            ly.setPageTitle(OrderController.TITLE);
        });
    }

    public List LIST() {
        return new List(tableColumn);
    }

    public void fillForm() {
        clientSelect.removeAllItems();
        carSelect.removeAllItems();
        clientSelect.addItem(FormBuilder.NO_SELECT);
        carSelect.addItem(FormBuilder.NO_SELECT);
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
            .addField("voiture", carSelect);

        return builder.create(null);
    }

    public JComboBox getClientSelect() {
        return clientSelect;
    }

    public JComboBox getCarSelect() {
        return carSelect;
    }

    public void onValid(ActionListener actionListener) {
        validOrderBtn.addActionListener(actionListener);
    }

    public void onCancel(ActionListener actionListener) {
        cancelOrderBtn.addActionListener(actionListener);
    }
}
