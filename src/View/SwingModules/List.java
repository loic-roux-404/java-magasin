package View.SwingModules;

import Services.Entity.Entity;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * List data in table
 */
public class List extends JPanel {
    ArrayList<Entity> entities;
    // Table for user data
    private final JTable table;
    // table column
    private String[] tableColumn = {};

    // buttons
    public BackButton backButton = new BackButton();
    JButton delete = new JButton("Supprimer la sélection");

    public List(String[] tableColumn) {
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

        delete.setSize(400, 50);
        add(delete, BoxLayout.LINE_AXIS);

        this.delete(e -> this.handleDelete());
    }

    // gets data from database and loads to table
    public void getDetails(ArrayList<Entity> entities) {
        this.entities = entities;
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        // Prevenir l'ajout de colonne alors qu'on a déjà les data
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(tableColumn);
        for (Entity o : this.entities) {
            String row = o.toString(true).trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
        }
    }

    protected void handleDelete() {
        if (table.getSelectedRow() > -1) {
            // remove selected row from the model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Store selected row
            int selectedRow = table.getSelectedRow();
            model.removeRow(selectedRow);
            this.entities.remove(selectedRow);
            JOptionPane.showMessageDialog(null, "Suppresion effectuée");
        }
    }

    public void update(TableModelListener listener) {
        table.getModel().addTableModelListener(listener);
    }

    public void delete(ActionListener actionListener) {
        delete.addActionListener(actionListener);
    }

    public JTable getTable() {
        return table;
    }
}
