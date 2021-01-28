package main.java.proj.View.SwingModules;

import main.java.proj.Exceptions.FormException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FormBuilder implements Form {

    private JPanel panel;

    public LinkedHashMap<String, JComponent> fields = new LinkedHashMap<>();
    public LinkedHashMap<String, JComponent> buttons = new LinkedHashMap<>();

    // List of fields id with no auto label
    public ArrayList<String> noLabelFields = new ArrayList<>();
    // Basic form buttons
    private JButton listButton = new JButton("Liste");
    private JButton submitButton = new JButton("Ajouter");
    private BackButton backButton = new BackButton();
    // space between fields
    Insets fieldsInset = new Insets(0, 0, 10, 0);
    // space between buttons
    Insets buttonInset = new Insets(20, 0, 0, 0);
    // Config
    private final boolean autoJLabel;
   static private int position = GridBagConstraints.WEST;
    // combobox
    public static String NO_SELECT = "Sélectionner ...";

    public FormBuilder(boolean autoJLabels) {
        listButton.setPreferredSize(PageBtn.SIZE);
        this.autoJLabel = autoJLabels;
        submitButton.setPreferredSize(PageBtn.SIZE);
    }

    public FormBuilder addField(String name, JComponent jComponent) {
        this.fields.put(name, jComponent);

        return this;
    }

    /**
     * Display on end
     *
     * @param name
     * @param jComponent
     * @return
     */
    public FormBuilder addButton(String name, JComponent jComponent) {
        buttons.put(name, jComponent);

        return this;
    }

    public FormBuilder addNoLabel(String name, JComponent jComponent) {
        noLabelFields.add(name);
        return this.addField(name, jComponent);
    }

    @Override
    public Form create(JPanel jPanel) {
        panel = jPanel != null ? jPanel : new JPanel();

        // uses Grid Bag Layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        // First constraints
        this.addGridBagConstraint(gridBagConstraints);

        for (HashMap.Entry<String, JComponent> entry : fields.entrySet()) {
            String name = entry.getKey();
            if (autoJLabel && !noLabelFields.contains(name)) {
                panel.add(
                    new JLabel(name.substring(0, 1).toUpperCase() + (name.substring(1)).replace("_", " ") + " :"),
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
        LinkedHashMap<String, JComponent> tmpBtns = new LinkedHashMap<>();

        if (submitButton != null) tmpBtns.put("submit", submitButton);
        if (listButton != null) tmpBtns.put("list", listButton);
        if (backButton != null) buttons.put("return", backButton.getToolBar());

        for (HashMap.Entry<String, JComponent> entry : tmpBtns.entrySet()) {
            this.panelAddBtn(entry.getValue(), gridBagConstraints);
        }

        for (HashMap.Entry<String, JComponent> entry : buttons.entrySet()) {
            this.panelAddBtn(entry.getValue(), gridBagConstraints);
        }
    }

    protected void panelAddBtn(JComponent jComponent, GridBagConstraints gridBagConstraints) {
        this.addGridBagConstraint(gridBagConstraints);
        gridBagConstraints.insets = buttonInset;
        panel.add(jComponent, gridBagConstraints);
    }

    public void submit(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }

    public void list(ActionListener actionListener) {
        listButton.addActionListener(actionListener);
    }

    public void validate() throws FormException {
        for (HashMap.Entry<String, JComponent> entry : fields.entrySet()) {
            JComponent jField = entry.getValue();
            String name = entry.getKey();

            if (jField instanceof JTextField) {
                if (((JTextField) jField).getText().isEmpty()) {
                    throw new FormException("Le champ texte "+ name +" doit être remplit");
                }
            } else if (jField instanceof JComboBox) {
                Object selected = ((JComboBox) jField).getSelectedItem();

                if (((JComboBox) jField).getSelectedItem() == NO_SELECT ||
                    selected.toString().isEmpty()
                ) {
                    throw new FormException("Le champ de sélection " + name + " doit être remplit");
                }
            } else if (jField instanceof JSpinner) {
                Object selected = ((JSpinner) jField).getValue();
                if ((Integer) selected <= 0) {
                    throw new FormException("Le champ nombre " + name + " doit être remplit");
                }
            }
        }
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
            } else if (jField instanceof JComboBox) {
                JComboBox jBox = (JComboBox) jField;
                jBox.setSelectedIndex(0);
            } else if (jField instanceof JSpinner) {
                JSpinner jSpinner = (JSpinner) jField;
                jSpinner.setValue(1);
            }

        }
    }

    public static void setPosition(int position) {
        FormBuilder.position = position;
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
     *
     * @param gridBagConstraints Grid bag constraints
     */
    protected void addGridBagConstraint(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy += 1;
        gridBagConstraints.anchor = position;
    }
}
