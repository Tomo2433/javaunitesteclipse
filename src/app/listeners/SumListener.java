package app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SumListener implements ActionListener {
    private JTable table;
    private JTextArea textArea;
    private int rows, cols;
    private int sum;

    public SumListener(JTable table, JTextArea textArea, int rows, int cols){
        this.table = table;
        this.textArea = textArea;
        this.rows = rows;
        this.cols = cols;
    }
    public void actionPerformed(ActionEvent e) {
        sum = 0;
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                sum += (int)table.getValueAt(i,j);
        textArea.append("Suma wartości komórek: "+sum+"\n");
    }
}
