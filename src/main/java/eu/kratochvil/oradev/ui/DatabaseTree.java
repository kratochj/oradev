package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.database.Structure;
import eu.kratochvil.oradev.database.Tables;
import eu.kratochvil.oradev.database.Views;
import eu.kratochvil.oradev.database.model.EntityInfo;
import eu.kratochvil.oradev.database.model.TableInfo;
import eu.kratochvil.oradev.database.model.ViewInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class DatabaseTree {
    public static final Logger logger = LogManager.getLogger(DatabaseTree.class);

    public JScrollPane refreshAsScrollPane() {
        return new JScrollPane(refresh());
    }

    private JTree refresh() {
        DefaultMutableTreeNode database = new DefaultMutableTreeNode(new DatabaseNodeObject("Database", Icons.DATABASE, ""));
        createTables(database);
        createViews(database);
        createPlSQL(database);

        final JTree tree = new JTree(database);
        setTreeRenderer(tree);

        tree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 2) {
                        if ((selPath != null) && (selPath.getLastPathComponent() instanceof DefaultMutableTreeNode)) {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                            if (node.getUserObject() instanceof EntityInfo) {
                                String sql = ((EntityInfo) (node.getUserObject())).getQuery();
                                logger.debug("Loading entity preview - {}", sql);
                            }
                        }
                    }
                }
            }
        });

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


                if ((value instanceof DefaultMutableTreeNode) && (((DefaultMutableTreeNode) value).getUserObject() instanceof DatabaseNodeObject)) {
                    setIcon(((DatabaseNodeObject) (((DefaultMutableTreeNode) value).getUserObject())).getIcon().getIcon());
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
        DefaultMutableTreeNode views = new DefaultMutableTreeNode(new DatabaseNodeObject("Views", Icons.VIEW, "select * from {}"));

        Structure.getInstance().setViews(new Views().load());
        for (ViewInfo view : Structure.getInstance().getViews()) {
            logger.trace("Adding table: {}", view);
            views.add(new DefaultMutableTreeNode(view));
        }

        database.add(views);
    }

    private void createTables(DefaultMutableTreeNode database) {
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode(new DatabaseNodeObject("Tables", Icons.TABLE, "select * from {}"));

        Structure.getInstance().setTables(new Tables().load());

        for (TableInfo table : Structure.getInstance().getTables()) {
            logger.trace("Adding table: {}", table);
            tables.add(new DefaultMutableTreeNode(table));
        }

        database.add(tables);
    }

}
