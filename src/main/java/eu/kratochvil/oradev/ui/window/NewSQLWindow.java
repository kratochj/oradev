package eu.kratochvil.oradev.ui.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class NewSQLWindow implements RegisteredWindow {
    @Override
    public String getCode() {
        return "NEW_SQL_WINDOW";
    }

    @Override
    public String getCaption() {
        return "SQL Window";
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
