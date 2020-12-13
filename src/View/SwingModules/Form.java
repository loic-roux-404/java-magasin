package View.SwingModules;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface Form {
    void submit(ActionListener actionListener);

    // Access to list from form
    void list(ActionListener actionListener);

    void reset(boolean bln);

    JPanel getPanel();

    BackButton getBackButton();
}
