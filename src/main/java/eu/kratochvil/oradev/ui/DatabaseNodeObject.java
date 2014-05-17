package eu.kratochvil.oradev.ui;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseNodeObject {

    String value;

    Icons icon;

    String query;

    public DatabaseNodeObject(String value, Icons icon, String query) {
        this.value = value;
        this.icon = icon;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public Icons getIcon() {
        return icon;
    }



    @Override
    public String toString() {
        return value;
    }
}
