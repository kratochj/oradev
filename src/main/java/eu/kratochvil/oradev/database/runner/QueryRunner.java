package eu.kratochvil.oradev.database.runner;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface QueryRunner {

    public void execute(EnhancedTableModel tableModel, String sql);
}
