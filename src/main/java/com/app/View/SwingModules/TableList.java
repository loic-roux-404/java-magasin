package com.app.View.SwingModules;

import com.app.Services.IEntity;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * List data in table
 */
public class TableList extends JPanel implements BuilderInterface {
    List<IEntity> entities;
    // Table for user data
    private final JTable table;
    // table column
    private String[] tableColumn = {};

    // buttons
    public BackButton backButton = new BackButton();
    JButton delete = new JButton("Supprimer");

    public TableList(String[] tableColumn) {
        add(backButton.getToolBar());
        this.tableColumn = tableColumn;
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        table = new JTable();
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(userTableScroll);
    }

    @Override
    public TableList create(JPanel jpanel) {
        if (delete != null) {
            addButton(delete);
            this.delete(e -> this.handleDelete());
        }

        return this;
    }

    // gets data from database and loads to table
    public void getDetails(List<IEntity> entities) {
        this.entities = entities;
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        // Prevenir l'ajout de colonne alors qu'on a déjà les data
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(tableColumn);
        for (IEntity o : this.entities) {
            String row = o.toString(true).trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
        }
    }

    protected void handleDelete() {
        if (!this.confirm()) return;
        if (table.getSelectedRow() > -1) {
            // remove selected row from the model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Store selected row
            int selectedRow = table.getSelectedRow();
            model.removeRow(selectedRow);
            this.entities.remove(selectedRow);
        }
    }

    public IEntity getEntity() {
        if (table.getSelectedRow() <= -1) return null;
        int selectedRow = table.getSelectedRow();

        return this.entities.get(selectedRow);
    }

    public void update(TableModelListener listener) {
        table.getModel().addTableModelListener(listener);
    }

    public void delete(ActionListener actionListener) {
        delete.addActionListener(actionListener);
    }

    public TableList disableDeleteButton()
    {
        delete = null;

        return this;
    }

    public TableList addButton(JButton button) {
        button.setSize(400, 50);
        add(button, BoxLayout.LINE_AXIS);

        return this;
    }

    public JTable getTable() {
        return table;
    }

    /**
     * Show confirm dialog.
     *
     * @return boolean true user answer Yes.
     */
    public boolean confirm() {
        return JOptionPane.showConfirmDialog(
            this,
            "Supprimer ?",
            "Confirmer la suppression",
            JOptionPane.YES_NO_OPTION
        ) == 0;
    }
}
