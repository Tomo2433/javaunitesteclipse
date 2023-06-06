package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SumListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int sum;

    public SumListener(IntegerTableModel tableModel, JTextArea textArea){
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
    }
    public void actionPerformed(ActionEvent e) {
        sum = 0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += (int)tableModel.getValueAt(i,j);
        textArea.append("Suma wartości komórek: "+sum+"\n");
    }
}
