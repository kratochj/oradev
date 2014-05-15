package eu.kratochvil.oradev.database.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class SelectQueryTableModel  extends AbstractTableModel implements EnhancedTableModel{
    public static final Logger logger = LogManager.getLogger(SelectQueryTableModel.class);

    List<List<DatabaseCell>> tableData;

    List<DatabaseCell> colums;

    int size = -1;

    public SelectQueryTableModel() {
        tableData =  new ArrayList<List<DatabaseCell>>();
        colums = new ArrayList<DatabaseCell>();

        colums.add(new DatabaseCell("TEST", null, ""));
        List<DatabaseCell> row = new ArrayList<DatabaseCell>();
        row.add(new DatabaseCell("TEST", "data", ""));

        tableData.add(row);


    }

    public List<List<DatabaseCell>> getTableData() {
        return tableData;
    }

    public List<DatabaseCell> getColums() {
        return colums;
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return colums.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colums.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // TODO Add implementation
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        logger.trace("Reading table data at {}:{}", rowIndex, columnIndex);

        return tableData.get(rowIndex).get(columnIndex).getValue();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // TODO Add implementation
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Add implementation
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Add implementation
    }

    @Override
    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
