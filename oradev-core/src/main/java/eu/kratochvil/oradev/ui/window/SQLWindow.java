package eu.kratochvil.oradev.ui.window;

import eu.kratochvil.oradev.database.runner.EnhancedTableModel;
import eu.kratochvil.oradev.database.runner.QueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryRunner;
import eu.kratochvil.oradev.database.runner.SelectQueryTableModel;
import eu.kratochvil.oradev.ui.AutocompletionProvider;
import eu.kratochvil.oradev.ui.Icons;
import eu.kratochvil.oradev.ui.components.UiComponents;
import org.apache.commons.lang.StringUtils;
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

    String windowCaption = "SQL Window";

    @Override
    public String getCode() {
        return "NEW_SQL_WINDOW";
    }

    @Override
    public String getCaption() {
        return windowCaption;
    }

    @Override
    public JComponent getPanel() {
        return getPanel("");
    }

    public JComponent getPanel(String sql) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(createTable(tableModel), JSplitPane.BOTTOM);

        JPanel sqlEditor = new JPanel(new BorderLayout());

        final RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60){
            @Override
            public void addNotify() {
                super.addNotify();
                requestFocus();
            }
        };
        textArea.setText(sql);

        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
        //textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        //cp.add(sp);

        String keyStrokeAndKey = "control ENTER";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
        textArea.getInputMap().put(keyStroke, keyStrokeAndKey);
        textArea.getActionMap().put(keyStrokeAndKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runQuery(textArea, splitPane);
            }
        });

        //final JTextArea jTextArea = new JTextArea(10, 0);
        sqlEditor.add(sp, BorderLayout.CENTER);

        // Autocompletion
        CompletionProvider provider = new AutocompletionProvider().createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(textArea);

        JButton executeSql = new JButton();
        executeSql.setIcon(Icons.EXECUTE.getIcon());
        executeSql.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runQuery(textArea, splitPane);
            }
        });

        sqlEditor.add(executeSql, BorderLayout.EAST);

        splitPane.add(sqlEditor, JSplitPane.TOP);

        panel.add(splitPane);

        textArea.grabFocus();

        if (StringUtils.isNotBlank(sql)) {
            runQuery(textArea, splitPane);
        }

        return panel;
    }

    private void runQuery(RSyntaxTextArea textArea, JSplitPane splitPane) {
        String query = textArea.getText();
        if (StringUtils.isBlank(query)) {
            logger.debug("Query is empty");
            UiComponents.alert("Empty query");
            return;
        }

        if (StringUtils.isNotEmpty(textArea.getSelectedText())) {
            query = textArea.getSelectedText();
        } else {
            if (query.contains(";")) {
                selectSingleCommand(textArea);
                query = textArea.getSelectedText();
            }
        }

        logger.debug("Executing query: {}", query);
        QueryRunner runner = new SelectQueryRunner();
        runner.execute(tableModel, query);
        tableModel.fireTableStructureChanged();
        splitPane.add(createTable(tableModel), JSplitPane.BOTTOM);
        if (tableModel.size()<0) {
            UiComponents.alert("There is more than 100 records!");
        }
    }

    private void selectSingleCommand(JTextArea textArea) {
        String text = textArea.getText();
        int pos = textArea.getCaretPosition();
        int nextSemicol = text.indexOf(";", pos);
        int prevSemicol = text.substring(0, pos).lastIndexOf(";");

        if (nextSemicol < 0) {
            nextSemicol = text.length();
        }
        if (prevSemicol < 0) {
            prevSemicol = 0;
        } else {
            prevSemicol++;
        }

        logger.debug("Text after cursor: {}", text.substring(prevSemicol, nextSemicol));
        textArea.setSelectionStart(prevSemicol);
        textArea.setSelectionEnd(nextSemicol);


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
