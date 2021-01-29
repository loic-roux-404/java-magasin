package com.app.View.SwingModules;

import com.app.Exceptions.FormException;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Interface type to identify a builded and ready form
 */
public interface Form extends BuilderInterface {
    void submit(ActionListener actionListener);

    // Access to list from form
    void list(ActionListener actionListener);

    void reset(boolean bln);

    JPanel getPanel();

    BackButton getBackButton();

    void validate() throws FormException;
}
