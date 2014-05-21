package eu.kratochvil.oradev.database;

import eu.kratochvil.oradev.database.model.TableInfo;
import eu.kratochvil.oradev.database.model.ViewInfo;

import java.util.List;

/**
  * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
  */
 public class Structure {

    List<TableInfo> tables;

    List<ViewInfo> views;

    static Structure instance;

    public static Structure getInstance() {
        if (instance == null) {
            instance = new Structure();
        }
        return instance;
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }

    public List<ViewInfo> getViews() {
        return views;
    }

    public void setViews(List<ViewInfo> views) {
        this.views = views;
    }
}
