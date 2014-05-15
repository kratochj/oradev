package eu.kratochvil.oradev.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MainAppWindow extends JFrame {

    private JTabbedPane theDesktop;

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
    }

}
