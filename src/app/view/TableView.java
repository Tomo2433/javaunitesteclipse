package app.view;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.*;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>TableView</code> definiujaca widok tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
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

    /**
     * Metoda zwraca tabele
     * @return zwraca JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Metoda zwraca table scroll pane
     * @return JScrollPane
     */
    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    /**
     * Metoda zwraca model tabeli integer
     * @return zwraca obiekt IntegerTableModel
     */
    public IntegerTableModel getTableModel() {
        return tableModel;
    }
}
