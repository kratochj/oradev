package eu.kratochvil.oradev.database;

import eu.kratochvil.oradev.database.model.TableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class Tables {
    public static final Logger logger = LogManager.getLogger(Tables.class);

    public List<TableInfo> load() {
        List<TableInfo> tables = new ArrayList<TableInfo>();
        try {
            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement("select table_name from user_tables order by table_name");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                logger.trace("Loadinf table from database: {}", rs.getString(1));
                tables.add(new TableInfo(rs.getString(1)));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return tables;
    }

}
