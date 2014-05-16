package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.database.DatabaseConnection;
import org.fife.rsta.ui.SizeGripIcon;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MainAppWindow extends JFrame {

    private JTabbedPane theDesktop;
    private StatusBar statusBar;

    public MainAppWindow() throws HeadlessException {
        super("OraDev");

        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //pane.add(new DatabaseTree().refreshAsScrollPane(), BorderLayout.LINE_START);
        splitPane.add(new DatabaseTree().refreshAsScrollPane(), JSplitPane.LEFT);

        theDesktop = new JTabbedPane(); // create desktop pane
        //pane.add(theDesktop, BorderLayout.CENTER);
        pane.add(splitPane, BorderLayout.CENTER);

        splitPane.add(theDesktop, JSplitPane.RIGHT);

        MainMenu mainMenu = new MainMenu(theDesktop);
        this.setJMenuBar(mainMenu.createMainMenu());

        statusBar = new StatusBar();
        pane.add(statusBar, BorderLayout.SOUTH);
    }

    private static class StatusBar extends JPanel {
        private JLabel label;

        public StatusBar() {
            label = new JLabel("Connected to: " + DatabaseConnection.getInstance().getActiveConnectionInfo().getUrl());
            setLayout(new BorderLayout());
            add(label, BorderLayout.LINE_START);
            add(new JLabel(new SizeGripIcon()), BorderLayout.LINE_END);
        }

        public void setLabel(String label) {
            this.label.setText(label);
        }

    }


}
