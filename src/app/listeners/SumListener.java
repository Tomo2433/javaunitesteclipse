package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>SumListener</code> definiująca nasłuch dla metody sumowania
 * danych w tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class SumListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int sum;

    /**
     * Kontruktor klasy <code>SumListener</code>
     * @param tableModel
     * @param textArea
     */
    public SumListener(IntegerTableModel tableModel, JTextArea textArea){
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
    }

    /**
     * Metoda akcji dla sumowania wartości
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        sum = 0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += (int)tableModel.getValueAt(i,j);
        textArea.append("Suma wartości komórek: "+sum+"\n");
    }
}
