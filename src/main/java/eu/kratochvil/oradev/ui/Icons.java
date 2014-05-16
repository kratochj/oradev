package eu.kratochvil.oradev.ui;

import javax.swing.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public enum Icons {

    TABLE("table.png", "Database table"),
    VIEW("view.png", "Database View"),
    DATABASE("database.png", "Database connection");

    private ImageIcon icon;

    Icons(String filename, String description) {
        this.icon = createImageIcon("/icons/" + filename, description);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(((new ImageIcon(imgURL)).getImage()).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH), description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
