package View;

import View.SwingModules.FormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Optional;

public class Home extends JPanel {
    public FormBuilder form = new FormBuilder(false).disableAllBtn();

    public HashMap<String, JButton> pages = new HashMap<String, JButton>();

    final static String CLIENTS = "clients";
    final static String CARS = "cars";
    final static String ORDERS = "orders";
    final static String BUILDERS = "builders";

    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public Home() {
        this.addPage("Gestion des clients", CLIENTS);
        this.addPage("Catalogue de voitures", CARS);
        this.addPage("Gestion des commandes", ORDERS);
        this.addPage("Fabricants", BUILDERS);

        form.create(Optional.ofNullable(this));
    }

    protected void addPage(String name, String id) {
        JButton btn = new JButton(name);
        pages.put(id, btn);
        btn.setPreferredSize(new Dimension(278, 40));

        form.addField(name, btn);
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
