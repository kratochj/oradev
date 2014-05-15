package eu.kratochvil.oradev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class MainMenu implements ActionListener, ItemListener {
    public static final Logger logger = LogManager.getLogger(MainMenu.class);

    JDesktopPane theDesktop;

    public MainMenu(JDesktopPane theDesktop) {
        this.theDesktop = theDesktop;
    }

    public JMenuBar createMainMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.theDesktop = theDesktop;

        menuBar.add(getFileMenu());
        menuBar.add(getEditMenu());
        menuBar.add(getSessionMenu());
        menuBar.add(getToolsMenu());
        menuBar.add(getDebugMenu());
        menuBar.add(getWindowMenu());
        menuBar.add(getHelpMenu());
        return menuBar;
    }

    private JMenu getFileMenu() {
        JMenu menu;
        menu = new JMenu("File");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getEditMenu() {
        JMenu menu;
        menu = new JMenu("Edit");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getSessionMenu() {
        JMenu menu;
        menu = new JMenu("Session");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getDebugMenu() {
        JMenu menu;
        menu = new JMenu("Debug");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getToolsMenu() {
        JMenu menu;
        menu = new JMenu("Tools");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getWindowMenu() {
        JMenu menu;
        menu = new JMenu("Window");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }

    private JMenu getHelpMenu() {
        JMenu menu;
        menu = new JMenu("Help");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");

        {
            JMenuItem menuItem = new JMenuItem("A text-only menu item",
                    KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "This doesn't really do anything");
            menuItem.setActionCommand("TEST");
            menuItem.addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        logger.debug("ActionPerformed: {}", e);

        // create internal frame
        JInternalFrame frame = new JInternalFrame("Internal Frame", true, true, true, true);

        MyJPanel panel = new MyJPanel(); // create new panel
        frame.add(panel, BorderLayout.CENTER); // add panel
        frame.pack(); // set internal frame to size of contents

        theDesktop.add(frame); // attach internal frame
        frame.setVisible(true); // show internal frame
        // TODO Add implementation
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        logger.debug("ItemStateChanged: {}", e);

        // TODO Add implementation
    }
}
