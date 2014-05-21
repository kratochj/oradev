package eu.kratochvil.oradev.database.runner;

import eu.kratochvil.oradev.database.DatabaseConnection;
import eu.kratochvil.oradev.ui.components.UiComponents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class SelectQueryRunner implements QueryRunner {
    public static final Logger logger = LogManager.getLogger(SelectQueryRunner.class);
    private static final int FETCH_SIZE = 100;

    @Override
    public void execute(EnhancedTableModel tableModel, String sql) {
        String sqlLimited = "select * from (" + sql + ") where rownum <= " + (FETCH_SIZE + 1);
        logger.debug("SQL: {}", sqlLimited);

        try {
            ((SelectQueryTableModel)tableModel).getTableData().clear();
            ((SelectQueryTableModel)tableModel).getColums().clear();
            ((SelectQueryTableModel)tableModel).getColums().add(new DatabaseCell("#", "#", ""));

            ResultSet rs = DatabaseConnection.getInstance().getConnection().createStatement().executeQuery(sqlLimited);

            // Load data
            int count = 0;
            boolean structureNotLoaded = true;
            while (rs.next()) {
                count++;

                if (structureNotLoaded) {
                    structureNotLoaded = false;
                    // Load structure
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        DatabaseCell dbc = new DatabaseCell(rs.getMetaData().getColumnName(i),
                                null, rs.getMetaData().getColumnTypeName(i));
                        ((SelectQueryTableModel)tableModel).getColums().add(dbc);
                    }
                }

                if (count <= FETCH_SIZE) {
                    List<DatabaseCell> row = new ArrayList<DatabaseCell>();
                    row.add(new DatabaseCell("#", String.valueOf(count), ""));
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        DatabaseCell dbc = new DatabaseCell(rs.getMetaData().getColumnName(i),
                                rs.getString(i), rs.getMetaData().getColumnTypeName(i));
                        row.add(dbc);
                    }
                    ((SelectQueryTableModel) tableModel).getTableData().add(row);
                }
            }
            if (count > FETCH_SIZE) {
                ((SelectQueryTableModel) tableModel).setSize(-1);
            } else {
                ((SelectQueryTableModel) tableModel).setSize(count);
            }
            logger.debug("Fetched {} records", count);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            UiComponents.alert(e.getLocalizedMessage());
        }
    }

}
