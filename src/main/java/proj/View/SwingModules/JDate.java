package main.java.proj.View.SwingModules;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDate {
    final static String FORMAT = "MM/dd/yyyy";
    DateFormat df = new SimpleDateFormat(FORMAT);
    JFormattedTextField txtDate = new JFormattedTextField(df);

    public JDate() {
        txtDate.setSize(100, 20);
        txtDate.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                    (c == KeyEvent.VK_BACK_SPACE) ||
                    (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });
    }

    public JFormattedTextField getField() {
        return txtDate;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat frt = new SimpleDateFormat(FORMAT);
        return frt.parse(txtDate.getText());
    }
}
