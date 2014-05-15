package eu.kratochvil.oradev;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MainAppWindow extends JFrame {

    private JDesktopPane theDesktop;

    public MainAppWindow() throws HeadlessException {
        super("OraDev");
        theDesktop = new JDesktopPane(); // create desktop pane

        MainMenu mainMenu = new MainMenu(theDesktop);
        this.setJMenuBar(mainMenu.createMainMenu());
        add(theDesktop); // add desktop pane to frame
    }

}
