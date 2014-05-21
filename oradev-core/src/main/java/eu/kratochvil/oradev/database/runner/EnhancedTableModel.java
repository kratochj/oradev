package eu.kratochvil.oradev.database.runner;

import javax.swing.table.TableModel;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public interface EnhancedTableModel extends TableModel {

    void fireTableDataChanged();

    void fireTableStructureChanged();

    int size();
}
