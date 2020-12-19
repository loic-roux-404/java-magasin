package View.SwingModules;

import javax.swing.*;
import java.util.Optional;

public interface BuilderInterface {
    BuilderInterface create(Optional<JPanel> optionalJPanel);
}
