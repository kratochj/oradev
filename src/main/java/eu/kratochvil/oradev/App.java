package eu.kratochvil.oradev;

import ch.randelshofer.quaqua.QuaquaManager;
import eu.kratochvil.oradev.database.DatabaseConnection;
import eu.kratochvil.oradev.ui.MainAppWindow;
import eu.kratochvil.oradev.ui.window.WindowsRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class App {

    public static final Logger logger = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        try {
            logger.info("Starting application");

            DatabaseConnection.getInstance().connect();
            new WindowsRegister();

            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
            System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
            System.setProperty("Quaqua.design","auto");

            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainAppWindow mainAppWindow = new MainAppWindow();
                    mainAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    //mainAppWindow.setSize(300, 200);
                    mainAppWindow.setLocationRelativeTo(null);
                    mainAppWindow.pack();
                    mainAppWindow.setVisible(true);
                }
            });

        } catch (Exception e) {
            logger.fatal("Uncaught exception: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

}
