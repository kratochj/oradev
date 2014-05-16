package eu.kratochvil.oradev.ui;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseNodeObject {

    String value;

    Icons icon;

    public DatabaseNodeObject(String value, Icons icon) {
        this.value = value;
        this.icon = icon;
    }

    public Icons getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return value;
    }
}
