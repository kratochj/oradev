package eu.kratochvil.oradev.database.runner;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseCell {

    String name;
    String value;
    String type;

    public DatabaseCell(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("name", name).append("value", value).append("type", type);
        return builder.toString();
    }
}
