package eu.kratochvil.oradev;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class ActiveConnectionInfo {

    String username = "vcelin";
    String password = "vcelin123";
    String url = "jdbc:oracle:thin:@db01.cairo.topmonks.com:1521:TOPMONKS";
    String driver = "oracle.jdbc.OracleDriver";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("username", username).append("password", password).append("url", url).append("driver", driver);
        return builder.toString();
    }
}
