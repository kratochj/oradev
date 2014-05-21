package eu.kratochvil.oradev.ui.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class TestWindow implements RegisteredWindow {
    @Override
    public String getCode() {
        return "TEST";
    }

    @Override
    public String getCaption() {
        return "Test";
    }

    @Override
    public JComponent getPanel() {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(getCode());
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;

    }
}
