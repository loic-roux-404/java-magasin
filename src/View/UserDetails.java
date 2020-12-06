package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserDetails extends JPanel {

    // Table for user data
    private JTable userTable;
    // table column
    private String[] userTableColumn = {"FIRST NAME", "LAST NAME"};

    // back button
    private JButton backButton;

    public UserDetails() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        userTable = new JTable();
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    // gets users from database and loads to table
    public void getUsers(Object[] objects) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        // Prevenir l'ajout de colonne alors qu'on a déjà les data
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(userTableColumn);
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
