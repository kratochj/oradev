package eu.kratochvil.oradev.database;

import eu.kratochvil.oradev.ActiveConnectionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseConnection {
    public static final Logger logger = LogManager.getLogger(DatabaseConnection.class);

    ActiveConnectionInfo activeConnectionInfo = new ActiveConnectionInfo();

    public static DatabaseConnection instance = null;

    Connection connection = null;

    public static DatabaseConnection getInstance() {
        if (null == instance) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public ActiveConnectionInfo getActiveConnectionInfo() {
        return activeConnectionInfo;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        try {
            logger.debug("Registering driver");
            Class.forName(activeConnectionInfo.getDriver());

            logger.debug("Connecting to database...");
            connection = DriverManager.getConnection(activeConnectionInfo.getUrl(), activeConnectionInfo.getUsername(), activeConnectionInfo.getPassword());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }




}
