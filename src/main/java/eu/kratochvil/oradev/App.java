package eu.kratochvil.oradev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class App {

    public static final Logger logger = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        logger.info("Starting application");
        MainAppWindow mainAppWindow = new MainAppWindow();
        mainAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainAppWindow.setVisible(true);
        mainAppWindow.setSize(300, 200);
        mainAppWindow.setLocationRelativeTo(null);
    }

}
