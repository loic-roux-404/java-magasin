package View.SwingModules;

import Exceptions.FormException;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface Form extends BuilderInterface {
    void submit(ActionListener actionListener);

    // Access to list from form
    void list(ActionListener actionListener);

    void reset(boolean bln);

    JPanel getPanel();

    BackButton getBackButton();

    void validate() throws FormException;
}
