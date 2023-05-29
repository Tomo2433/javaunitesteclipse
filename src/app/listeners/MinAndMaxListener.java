package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinAndMaxListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int value;


    public MinAndMaxListener(IntegerTableModel tableModel, JTextArea textArea,
                             int rows, int cols){
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = rows;
        this.cols = cols;
    }
    public void actionPerformed(ActionEvent e) {
        int max = (int) tableModel.getValueAt(0, 0);
        int min = max;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((int) tableModel.getValueAt(i, j) >= max)
                    max = (int) tableModel.getValueAt(i, j);
                if ((int) tableModel.getValueAt(i, j) <= min)
                    min = (int) tableModel.getValueAt(i, j);
            }
        }

        textArea.append("Maksymalna wartość z tabeli: " + max + "\n");
        textArea.append("Minimalna wartość z tabeli: " + min + "\n");
    }
}
