package eu.kratochvil.oradev.ui.window;

import javax.swing.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface RegisteredWindow {

    String getCode();

    String getCaption();

    JComponent getPanel();

}
