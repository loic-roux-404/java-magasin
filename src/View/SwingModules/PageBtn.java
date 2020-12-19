package View.SwingModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PageBtn {
    JButton btn;
    public static Dimension SIZE = new Dimension(310, 40);

    public PageBtn(String txt) {
        this.btn = new JButton(txt);
        this.btn.setPreferredSize(SIZE);
    }

    public void onOpen(ActionListener actionListener) {
        btn.addActionListener(actionListener);
    }

    public JButton getBtn() {
        return btn;
    }
}
