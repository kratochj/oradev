package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.ui.window.RegisteredWindow;
import eu.kratochvil.oradev.ui.window.WindowsRegister;
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

    JTabbedPane theDesktop;

    public MainMenu(JTabbedPane theDesktop) {
        this.theDesktop = theDesktop;
    }

    public JMenuBar createMainMenu() {
        JMenuBar menuBar = new JMenuBar();
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
            JMenuItem menuItem = new JMenuItem("New SQL RegisteredWindow",
                    KeyEvent.CTRL_MASK + KeyEvent.VK_N);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Open new SQL window");
            menuItem.setActionCommand("NEW_SQL_WINDOW");
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
        menu = new JMenu("RegisteredWindow");
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
        RegisteredWindow window = WindowsRegister.getWindows().get(e.getActionCommand());
        if (window == null) {
            logger.warn("No registered window for command: {}", e.getActionCommand());
            return ;
        }


        theDesktop.addTab(window.getCaption(), null, window.getPanel(), "Does nothing");

        // TODO Add implementation
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        logger.debug("ItemStateChanged: {}", e);

        // TODO Add implementation
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}