package eu.kratochvil.oradev.database.model;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class TableInfo implements EntityInfo{

    String tablename;

    public TableInfo(String tablename) {
        this.tablename = tablename;
    }

    @Override
    public String toString() {
       return tablename;
    }

    @Override
    public String getQuery() {
        return "select * from " + tablename;
    }
}
