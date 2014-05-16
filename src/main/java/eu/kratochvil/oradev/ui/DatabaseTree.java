package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.database.Tables;
import eu.kratochvil.oradev.database.Views;
import eu.kratochvil.oradev.database.model.TableInfo;
import eu.kratochvil.oradev.database.model.ViewInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseTree {
    public static final Logger logger = LogManager.getLogger(DatabaseTree.class);

    public JScrollPane refreshAsScrollPane() {
        return new JScrollPane(refresh());
    }

    private JTree refresh() {
        DefaultMutableTreeNode database = new DefaultMutableTreeNode(new DatabaseNodeObject("Database", Icons.DATABASE));
        createTables(database);
        createViews(database);
        createPlSQL(database);

        JTree tree = new JTree(database);
        setTreeRenderer(tree);
        return tree;
    }

    private void setTreeRenderer(JTree tree) {
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean isLeaf, int row, boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value,
                        selected, expanded, isLeaf, row, focused);


                if ((value instanceof DefaultMutableTreeNode) && (((DefaultMutableTreeNode)value).getUserObject() instanceof DatabaseNodeObject)) {
                    setIcon(((DatabaseNodeObject)(((DefaultMutableTreeNode)value).getUserObject())).getIcon().getIcon());
                }
                return c;
            }
        });
    }

    private void createPlSQL(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode plsql = new DefaultMutableTreeNode("PL/SQL");
        database.add(plsql);
    }

    private void createViews(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode views = new DefaultMutableTreeNode(new DatabaseNodeObject("Views", Icons.VIEW));

        for (ViewInfo view : new Views().load()) {
            logger.trace("Adding table: {}", view);
            views.add(new DefaultMutableTreeNode(view));
        }

        database.add(views);
    }

    private void createTables(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode(new DatabaseNodeObject("Tables", Icons.TABLE));

        for (TableInfo table : new Tables().load()) {
            logger.trace("Adding table: {}", table);
            tables.add(new DefaultMutableTreeNode(table));
        }

        database.add(tables);
    }

}
