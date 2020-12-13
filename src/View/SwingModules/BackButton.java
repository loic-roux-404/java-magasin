package View.SwingModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BackButton {

    private JToolBar toolBar;
    // back button
    protected JButton btn;

    public BackButton() {
        toolBar = new JToolBar("toolBar", JToolBar.VERTICAL);
        toolBar.setFloatable(false);
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        toolBar.setBounds(300, 300, 1200, 100);
        // Back
        btn = new JButton("Go Back");
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        toolBar.add(btn);
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    // event listener for back button
    public void onClick(ActionListener actionListener) {
        btn.addActionListener(actionListener);
    }
}
