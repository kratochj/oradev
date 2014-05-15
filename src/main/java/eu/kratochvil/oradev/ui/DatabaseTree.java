package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.database.Tables;
import eu.kratochvil.oradev.database.Views;
import eu.kratochvil.oradev.database.model.TableInfo;
import eu.kratochvil.oradev.database.model.ViewInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseTree {
    public static final Logger logger = LogManager.getLogger(DatabaseTree.class);

    public JScrollPane refreshAsScrollPane() {
        return new JScrollPane(refresh());
    }

    private JTree refresh() {
        DefaultMutableTreeNode database = new DefaultMutableTreeNode("Database");
        createTables(database);
        createViews(database);
        createPlSQL(database);
        return new JTree(database);
    }

    private void createPlSQL(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode plsql = new DefaultMutableTreeNode("PL/SQL");
        database.add(plsql);
    }

    private void createViews(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode views = new DefaultMutableTreeNode("Views");

        for (ViewInfo view : new Views().load()) {
            logger.trace("Adding table: {}", view);
            views.add(new DefaultMutableTreeNode(view));
        }

        database.add(views);
    }

    private void createTables(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode("Tables");

        for (TableInfo table : new Tables().load()) {
            logger.trace("Adding table: {}", table);
            tables.add(new DefaultMutableTreeNode(table));
        }

        database.add(tables);
    }

}
