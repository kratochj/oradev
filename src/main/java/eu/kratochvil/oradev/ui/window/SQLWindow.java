package eu.kratochvil.oradev.ui.window;

import eu.kratochvil.oradev.ui.AutocompletionProvider;
import eu.kratochvil.oradev.database.runner.EnhancedTableModel;
import eu.kratochvil.oradev.database.runner.QueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryTableModel;
import eu.kratochvil.oradev.ui.UiComponents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class SQLWindow implements RegisteredWindow {
    public static final Logger logger = LogManager.getLogger(SQLWindow.class);

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

        final RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        //textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        //cp.add(sp);

        //final JTextArea jTextArea = new JTextArea(10, 0);
        sqlEditor.add(sp, BorderLayout.CENTER);

        // Autocompletion
        CompletionProvider provider = new AutocompletionProvider().createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(textArea);

        JButton executeSql = new JButton("Run");
        executeSql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Executing query: {}", textArea.getText());
                QueryRunner runner = new SelectQueryRunner();
                runner.execute(tableModel, textArea.getText());
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
