package View.SwingModules;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        jLabel = new JLabel(new ImageIcon(image));

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
