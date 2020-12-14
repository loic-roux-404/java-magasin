package View.SwingModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Optional;

public class FormBuilder implements Form {

    private JPanel panel;

    public HashMap<String, JComponent> fields = new HashMap<>();
    // Basic form buttons
    private JButton listButton    = new JButton("Liste");
    private JButton submitButton  = new JButton("Ajouter");
    private BackButton backButton = new BackButton();
    // Sizing
    private static final Dimension buttonSize = new Dimension(278, 40);
    // space between fields
    Insets fieldsInset = new Insets(0, 0, 10, 0);
    // space between buttons
    Insets buttonInset = new Insets(20,0,0,0);
    // Config
    private boolean autoJLabel;

    public FormBuilder(boolean autoJLabels) {
        listButton.setPreferredSize(buttonSize);
        this.autoJLabel = autoJLabels;
        submitButton.setPreferredSize(buttonSize);
    }

    public FormBuilder addField(String name, JComponent jComponent) {
        this.fields.put(name, jComponent);

        return this;
    }

    public Form create(Optional<JPanel> optionalJPanel) {
        panel = optionalJPanel.isPresent()
                ? optionalJPanel.get()
                : new JPanel();

        // uses Grid Bag Layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        // First constraints
        this.addGridBagConstraint(gridBagConstraints);

        for (HashMap.Entry<String, JComponent> entry : fields.entrySet()) {
            if (autoJLabel) {
                String name = entry.getKey();
                panel.add(
                        new JLabel(name.substring(0, 1).toUpperCase() + name.substring(1) + " :"),
                        gridBagConstraints
                );
                this.addGridBagConstraint(gridBagConstraints);
            }
            JComponent jTextField = entry.getValue();
            panel.add(jTextField, gridBagConstraints);
            this.addGridBagConstraint(gridBagConstraints);
        }

        this.initButtons(gridBagConstraints);

        return this;
    }

    protected void initButtons(GridBagConstraints gridBagConstraints) {
        if (listButton != null) {
            gridBagConstraints.insets = buttonInset;
            panel.add(listButton, gridBagConstraints);
        }

        if (submitButton != null) {
            this.addGridBagConstraint(gridBagConstraints);
            gridBagConstraints.insets = buttonInset;
            panel.add(submitButton, gridBagConstraints);
        }

        if (backButton != null) {
            this.addGridBagConstraint(gridBagConstraints);
            panel.add(backButton.getToolBar(), gridBagConstraints);
        }
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
        for (HashMap.Entry<String, JComponent> entry : fields.entrySet()) {
            JComponent jField = entry.getValue();
            if (jField instanceof JTextField) {
                JTextField jTextField = (JTextField) jField;
                jTextField.setText("");
            }
        }
    }

    public FormBuilder disableSubmitBtn() {
        listButton = null;

        return this;
    }

    public FormBuilder disableListBtn() {
        listButton = null;

        return this;
    }

    public FormBuilder disableBackBtn() {
        backButton = null;

        return this;
    }

    public FormBuilder disableAllBtn() {
        backButton = null;
        listButton = null;
        submitButton = null;

        return this;
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
