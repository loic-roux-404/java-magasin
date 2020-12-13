package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Home extends JPanel {
    public HashMap<String, JButton> pages = new HashMap<String, JButton>();

    final static String CLIENTS = "clients";
    final static String CARS = "cars";
    final static String ORDERS = "orders";
    final static String BUILDERS = "builders";

    public Home() {
        this.addPage("Gestion des clients", CLIENTS);
        this.addPage("Catalogue de voitures", CARS);
        this.addPage("Gestion des commandes", ORDERS);
        this.addPage("Fabricants", BUILDERS);
    }

    protected void addPage(String name, String id) {
        JButton btn = new JButton(name);
        pages.put(id, btn);
        btn.setPreferredSize(new Dimension(278, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        add(btn, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = buttonInset;
    }

    public void usersPage(ActionListener actionListener) {
        pages.get(CLIENTS).addActionListener(actionListener);
    }

    public void carsPage(ActionListener actionListener) {
        pages.get(CARS).addActionListener(actionListener);
    }

    public void ordersPage(ActionListener actionListener) {
        pages.get(ORDERS).addActionListener(actionListener);
    }

    public void buildersPage(ActionListener actionListener) {
        pages.get(BUILDERS).addActionListener(actionListener);
    }
}
