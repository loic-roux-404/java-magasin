package com.app.View.SwingModules;

import com.app.Framework.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class LabelBuilder implements BuilderInterface {
    private JLabel jLabel;
    private JPanel jPanel;

    public LabelBuilder(String text) {
        this.jLabel = new JLabel(text);
    }

    public LabelBuilder setFont(String family, int size) {
        return setFont(family, Font.PLAIN, size);
    }

    private LabelBuilder setBoldFont(String family, int size) {
        return setFont(family, Font.BOLD, size);
    }

    private LabelBuilder setItalicFont(String family, int size) {
        return setFont(family, Font.BOLD, size);
    }

    public JLabel buildTitle() {
        return this
                .setBoldFont("Arial", 24)
                .getjLabel();
    }

    public LabelBuilder buildImage(String path) {
        jLabel = new JLabel(ResourceManager.getImageIcon(path));

        return this;
    }

    private LabelBuilder setFont(String family, int type, int size) {
        Font font = new Font(family, type >= 0 ? type : Font.PLAIN, size);
        jLabel.setFont(font);

        return this;
    }

    public LabelBuilder create(JPanel jPanel) {
        jPanel = jPanel != null
            ? jPanel
            : new JPanel();

        jPanel.add(jLabel);

        return this;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
