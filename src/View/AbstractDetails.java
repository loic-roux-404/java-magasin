package View;

import View.SwingModules.BackButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @TODO Transform this class in CRUD generator
 */
abstract public class AbstractDetails extends JPanel {

    // Table for user data
    private JTable table;
    // table column
    private String[] tableColumn = {};

    // back button
    public BackButton backButton = new BackButton(this);

    public AbstractDetails(String[] tableColumn) {
        this.tableColumn = tableColumn;
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        table = new JTable();
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(userTableScroll);
    }

    // gets data from database and loads to table
    public void getDetails(Object[] objects) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        // Prevenir l'ajout de colonne alors qu'on a déjà les data
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(tableColumn);
        for (Object o: objects) {
            String row = o.toString().trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
        }
    }
}
