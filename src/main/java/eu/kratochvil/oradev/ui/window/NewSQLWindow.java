package eu.kratochvil.oradev.ui.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class NewSQLWindow implements RegisteredWindow {
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

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(createTable(), JSplitPane.BOTTOM);


        JPanel sqlEditor = new JPanel(new BorderLayout());

        JTextArea jTextArea = new JTextArea(10, 0);
        sqlEditor.add(jTextArea, BorderLayout.CENTER);

        JButton executeSql = new JButton("Run");
        sqlEditor.add(executeSql, BorderLayout.EAST);

        splitPane.add(sqlEditor, JSplitPane.TOP);

        panel.add(splitPane);
        return panel;
    }


    public JComponent createTable() {
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        final JTable table = new JTable(data, columnNames);
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
        JScrollPane scrollPane = new JScrollPane(table);

        return scrollPane;
    }

}
