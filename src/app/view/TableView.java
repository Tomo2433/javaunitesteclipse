package app.view;

import app.model.IntegerTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableView {
    private JScrollPane tableScrollPane;
    private IntegerTableModel tableModel;
    private JTable table;

    public TableView() {
        tableScrollPane = new JScrollPane();
        tableModel = new IntegerTableModel();
        table = new JTable(tableModel);

        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        tableScrollPane.setPreferredSize(new Dimension(500,100));
        tableScrollPane.setViewportView(table);
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public IntegerTableModel getTableModel() {
        return tableModel;
    }
}
