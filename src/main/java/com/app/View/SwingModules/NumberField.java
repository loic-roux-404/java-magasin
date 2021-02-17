package com.app.View.SwingModules;

import javax.swing.*;
import java.awt.*;

/**
 * usage :
 * - To create field(new NumberField()).getField(); In a Form builder
 * - To get value : (int) jSpinnner.getValue();
 */
public class NumberField {
    JSpinner numberChooser;

    public NumberField() {
        SpinnerNumberModel numberModel = new SpinnerNumberModel(
            Integer.valueOf(15), // value
            Integer.valueOf(1), // min
            Integer.valueOf(20000), // max
            Integer.valueOf(1) // step
        );
        numberChooser = new JSpinner(numberModel);
        numberChooser.setSize(new Dimension(310, 40));
    }

    public JSpinner getField() {
        return numberChooser;
    }
}
