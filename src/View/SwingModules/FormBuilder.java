package View.SwingModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Optional;

public class FormBuilder implements Form {

    private JPanel panel;

    public HashMap<String, JTextField> fields = new HashMap<>();
    private JButton listButton;
    private final JButton submitButton;
    private static final Dimension buttonSize = new Dimension(278, 40);

    private BackButton backButton = new BackButton();

    public FormBuilder(boolean enableListBtn) {
        if (enableListBtn) {
            this.listButton = new JButton("Liste");
            listButton.setPreferredSize(buttonSize);
        }
        submitButton = new JButton("Ajouter");
        submitButton.setPreferredSize(buttonSize);
    }

    public FormBuilder addField(String name, JTextField jTextField) {
        this.fields.put(name, jTextField);

        return this;
    }

    public Form create(Optional<JPanel> optionalJPanel) {
        panel = optionalJPanel.isPresent()
                ? optionalJPanel.get()
                : new JPanel();
        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        // First constraints
        this.addGridBagConstraint(gridBagConstraints);

        for (HashMap.Entry<String, JTextField> entry : fields.entrySet()) {
            String name = entry.getKey();
            JTextField jTextField = entry.getValue();
            panel.add(
                    new JLabel(name.substring(0, 1).toUpperCase() + name.substring(1) + " :"),
                    gridBagConstraints
            );
            this.addGridBagConstraint(gridBagConstraints);
            panel.add(jTextField, gridBagConstraints);
            this.addGridBagConstraint(gridBagConstraints);
        }

        if (listButton.isEnabled()) {
            gridBagConstraints.insets = buttonInset;
            panel.add(listButton, gridBagConstraints);
        }

        this.addGridBagConstraint(gridBagConstraints);
        gridBagConstraints.insets = buttonInset;
        panel.add(submitButton, gridBagConstraints);

        this.addGridBagConstraint(gridBagConstraints);
        panel.add(backButton.getToolBar(), gridBagConstraints);

        return this;
    }

    public void submit(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }

    public void list(ActionListener actionListener) {
        listButton.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if (!bln) {
            return;
        }
        for (HashMap.Entry<String, JTextField> entry : fields.entrySet()) {
            JTextField jTextField = entry.getValue();
            jTextField.setText("");
        }
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public BackButton getBackButton() {
        return backButton;
    }

    /**
     * Move view to a new form row
     * @param gridBagConstraints Grid bag constraints
     */
    protected void addGridBagConstraint(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy += 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
    }
}
