package eu.kratochvil.oradev.ui.window;

import eu.kratochvil.oradev.database.runner.EnhancedTableModel;
import eu.kratochvil.oradev.database.runner.QueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryTableModel;
import eu.kratochvil.oradev.ui.UiComponents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class NewSQLWindow implements RegisteredWindow {
    public static final Logger logger = LogManager.getLogger(NewSQLWindow.class);

    EnhancedTableModel tableModel = new SelectQueryTableModel();
    JTable table;

    @Override
    public String getCode() {
        return "NEW_SQL_WINDOW";
    }

    @Override
    public String getCaption() {
        return "SQL Window";
    }

    @Override
    public JComponent getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(createTable(tableModel), JSplitPane.BOTTOM);


        JPanel sqlEditor = new JPanel(new BorderLayout());

        final JTextArea jTextArea = new JTextArea(10, 0);
        sqlEditor.add(jTextArea, BorderLayout.CENTER);

        JButton executeSql = new JButton("Run");
        executeSql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Executing query: {}", jTextArea.getText());
                QueryRunner runner = new SelectQueryRunner();
                runner.execute(tableModel, jTextArea.getText());
                tableModel.fireTableStructureChanged();
                splitPane.add(createTable(tableModel), JSplitPane.BOTTOM);
                if (tableModel.size()<0) {
                    UiComponents.alert("There is more than 100 records!");
                }
            }
        });

        sqlEditor.add(executeSql, BorderLayout.EAST);

        splitPane.add(sqlEditor, JSplitPane.TOP);

        panel.add(splitPane);
        return panel;
    }


    public JComponent createTable(TableModel tableModel) {
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

/*
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
*/

        //Create the scroll pane and add the table to it.

        return new JScrollPane(table);
    }

}
