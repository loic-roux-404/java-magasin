package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @TODO Transform this class in CRUD generator
 */
abstract public class AbstractDetails extends JPanel {

    // Table for user data
    private JTable table;
    // table column
    private String[] tableColumn = {};

    // back button
    private JButton backButton;

    public AbstractDetails(String[] tableColumn) {
        this.tableColumn = tableColumn;
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        table = new JTable();
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    // gets users from database and loads to table
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

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
