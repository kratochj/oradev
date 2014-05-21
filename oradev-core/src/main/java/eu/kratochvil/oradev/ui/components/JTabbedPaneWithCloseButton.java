package eu.kratochvil.oradev.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Create tab with close button
 * <p/>
 * Usage:
 * <p/>
 * <code>
 * tabbedPane.add(panel);<br />
 * tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel), getTitlePanel(tabbedPane, panel, "Tab3"));
 * </code>
 *
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class JTabbedPaneWithCloseButton {
    public static JPanel getTitlePanel(final JTabbedPane tabbedPane, final JPanel panel, String title) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setOpaque(false);
        JLabel titleLbl = new JLabel(title);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        titlePanel.add(titleLbl);
        JButton closeButton = new JButton("x");

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.remove(panel);
            }
        });
        titlePanel.add(closeButton);

        return titlePanel;
    }

}
