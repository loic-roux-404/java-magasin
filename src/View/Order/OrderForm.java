package View.Order;

import View.SwingModules.BackButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderForm extends JPanel {
    public BackButton backButton;
    private JButton viewButton;

    OrderForm() {
        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        viewButton = new JButton("Liste");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = buttonInset;

        add(viewButton, gridBagConstraints);

        backButton = new BackButton(this);
    }

    public void viewOrders(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }
}
