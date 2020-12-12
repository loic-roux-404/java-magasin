package View.SwingModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BackButton {

    protected JToolBar toolBar;
    // back button
    protected JButton btn;

    public BackButton(JPanel panel) {
        toolBar = new JToolBar("toolBar", JToolBar.VERTICAL);
        toolBar.setLayout(new GridBagLayout());
        toolBar.setFloatable(false);
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        toolBar.setBounds(300, 300, 1200, 100);
        // Back
        btn = new JButton("Go Back");
        panel.add(toolBar);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        toolBar.add(btn);
    }

    // event listener for back button
    public void onBackButton(ActionListener actionListener) {
        btn.addActionListener(actionListener);
    }
}
