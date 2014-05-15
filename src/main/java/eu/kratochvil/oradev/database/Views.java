package eu.kratochvil.oradev.database;

import eu.kratochvil.oradev.database.model.ViewInfo;
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
public class Views {
    public static final Logger logger = LogManager.getLogger(Views.class);

    public List<ViewInfo> load() {
        List<ViewInfo> views = new ArrayList<ViewInfo>();
        try {
            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement("select view_name from user_views order by view_name");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                logger.trace("Loadinf table from database: {}", rs.getString(1));
                views.add(new ViewInfo(rs.getString(1)));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return views;
    }

}
