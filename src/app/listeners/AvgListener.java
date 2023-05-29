package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvgListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    private int sum;
    private float value;

    public AvgListener(IntegerTableModel tableModel, JTextArea textArea,
                       int rows, int cols) {
        this.tableModel = tableModel;
        this.textArea = textArea;
        this.rows = rows;
        this.cols = cols;
    }
    public void actionPerformed(ActionEvent e) {
        sum = 0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += (int)tableModel.getValueAt(i,j);
        value = (float) sum / (rows*cols);

        textArea.append("Średnia wartości komórek: "+value+"\n");
    }
}
