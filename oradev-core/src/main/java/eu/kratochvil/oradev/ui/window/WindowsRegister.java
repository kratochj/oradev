package eu.kratochvil.oradev.ui.window;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class WindowsRegister {
    public static final Logger logger = LogManager.getLogger(WindowsRegister.class);

    public WindowsRegister() {
        logger.debug("Initializing application windows");
        register(new SQLWindow());
        register(new TestWindow());
    }

    private static Map<String, RegisteredWindow> windows = new HashMap<String, RegisteredWindow>();

    public static Map<String, RegisteredWindow> getWindows() {
        return windows;
    }

    private static void register(RegisteredWindow window) {
        logger.debug("Registering window: {} [{}]", window.getCode(), window.getCaption());
        windows.put(window.getCode(), window);
    }


}
