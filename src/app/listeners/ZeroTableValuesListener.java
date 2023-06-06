package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>ZeroTableValueListener</code> definiująca nasłuch dla metody
 * zerowania danych w tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class ZeroTableValuesListener implements ActionListener {

    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;

    /**
     * Konstruktor klasy <code>ZeroTableValuesListener</code>
     * @param tableModel
     * @param textArea
     */
    public ZeroTableValuesListener(IntegerTableModel tableModel, JTextArea textArea){
        this.tableModel = tableModel;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
        this.textArea = textArea;
    }

    /**
     * Metoda akcji zerowania
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                tableModel.setValue(0,i,j);
        textArea.append("Wyzerowano tabele\n");
    }
}
