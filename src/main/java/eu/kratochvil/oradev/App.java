package eu.kratochvil.oradev;

import ch.randelshofer.quaqua.QuaquaManager;
import eu.kratochvil.oradev.database.DatabaseConnection;
import eu.kratochvil.oradev.ui.MainAppWindow;
import eu.kratochvil.oradev.ui.window.WindowsRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.prefs.Preferences;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class App {

    public static final Logger logger = LogManager.getLogger(App.class);
    public static final String PREFERENCES_WINDOW_POSITION_X = "window.position.x";
    public static final String PREFERENCES_WINDOW_POSITION_Y = "window.position.y";
    public static final String PREFERENCES_WINDOW_SIZE_X = "window.size.x";
    public static final String PREFERENCES_WINDOW_SIZE_Y = "window.size.y";

    public static void main(String[] args) {
        try {
            logger.info("Starting application");

            DatabaseConnection.getInstance().connect();
            new WindowsRegister();

            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "OraDeveloper-");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "OraDeveloper");
            System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
            System.setProperty("Quaqua.design", "auto");

            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    logger.info("Application EXIT");
                }
            });

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainAppWindow mainAppWindow = new MainAppWindow();
                    mainAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    Preferences prefs = Preferences.userRoot().node(App.class.getName());
                    mainAppWindow.setBounds(
                            loadPreferenceInt(PREFERENCES_WINDOW_POSITION_X, 100),
                            loadPreferenceInt(PREFERENCES_WINDOW_POSITION_Y, 100),
                            loadPreferenceInt(PREFERENCES_WINDOW_SIZE_X, 300),
                            loadPreferenceInt(PREFERENCES_WINDOW_SIZE_Y, 300)
                    );


                    mainAppWindow.setSize(loadPreferenceInt(PREFERENCES_WINDOW_SIZE_X, 300), loadPreferenceInt(PREFERENCES_WINDOW_SIZE_Y, 300));
                    mainAppWindow.setLocationRelativeTo(null);
                    //mainAppWindow.pack();
                    mainAppWindow.setVisible(true);
                    mainAppWindow.addComponentListener(new ComponentListener() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                            savePrefs((MainAppWindow)e.getComponent());
                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {
                            savePrefs((MainAppWindow)e.getComponent());
                        }

                        @Override
                        public void componentShown(ComponentEvent e) {
                            savePrefs((MainAppWindow)e.getComponent());
                        }

                        @Override
                        public void componentHidden(ComponentEvent e) {
                            savePrefs((MainAppWindow)e.getComponent());
                        }
                    });
                }
            });

        } catch (Exception e) {
            logger.fatal("Uncaught exception: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    static void savePrefs(MainAppWindow mainAppWindow) {
        logger.debug("Saving windows state");
        savePreferenceInt(PREFERENCES_WINDOW_POSITION_X, mainAppWindow.getX());
        savePreferenceInt(PREFERENCES_WINDOW_POSITION_Y, mainAppWindow.getY());
        savePreferenceInt(PREFERENCES_WINDOW_SIZE_X, mainAppWindow.getWidth());
        savePreferenceInt(PREFERENCES_WINDOW_SIZE_Y, mainAppWindow.getHeight());
    }

    private static void savePreferenceInt(String key, int value) {
             logger.trace("Saving {}={}", key, value);
             Preferences prefs = Preferences.userRoot().node(App.class.getName());
             prefs.putInt(key, value);

         }
    private static int loadPreferenceInt(String key, int defaultValue) {
        Preferences prefs = Preferences.userRoot().node(App.class.getName());
        int value = prefs.getInt(key, defaultValue);
        logger.trace("Loading {}={} (default={})", key, value, defaultValue);
        return value;
    }
}
