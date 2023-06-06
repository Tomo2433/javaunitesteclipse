package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>MaxListener</code> definiująca nasłuch dla metody
 * wyboru wartości minimalnej z tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class MinListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int value;

    /**
     * Kontruktor klasy <code>MaxListener</code>
     * @param tableModel
     * @param textArea
     */
    public MinListener(IntegerTableModel tableModel, JTextArea textArea){
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
    }
    /**
     * Metoda akcji wyboru mina
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        value = (int) tableModel.getValueAt(0,0);
        for (int i=0; i<rows; i++)
            for (int j = 0; j < cols; j++)
                if ((int) tableModel.getValueAt(i,j) <= value)
                    value = (int) tableModel.getValueAt(i,j);

        textArea.append("Minimalna wartość z tabeli: "+value+"\n");
    }
}
