package eu.kratochvil.oradev.database.model;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class ViewInfo {

    String viewname;

    public ViewInfo(String viewname) {
        this.viewname = viewname;
    }

    @Override
    public String toString() {
       return viewname;
    }
}
