package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>AvgListener</code> definiująca nasłuch dla metody liczenia
 * średniej
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class AvgListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int sum;
    private float value;

    /**
     * Kontruktor klasy <code>AvgListener</code>
     * @param tableModel
     * @param textArea
     */
    public AvgListener(IntegerTableModel tableModel, JTextArea textArea) {
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
    }

    /**
     * Metoda akcji liczenia średniej wartości komórek tabeli
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        sum = 0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += (int)tableModel.getValueAt(i,j);
        value = (float) sum / (rows*cols);

        textArea.append("Średnia wartości komórek: "+value+"\n");
    }
}
