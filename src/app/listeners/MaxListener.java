package app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaxListener implements ActionListener {
    private JTable table;
    private JTextArea textArea;
    private int rows, cols;
    private int value;


    MaxListener(JTable table, JTextArea textArea, int rows, int cols){
        this.table = table;
        this.textArea = textArea;
        this.rows = rows;
        this.cols = cols;
    }
    public void actionPerformed(ActionEvent e) {
        value = (int) table.getValueAt(0,0);
        for (int i=0; i<rows; i++)
            for (int j = 0; j < cols; j++)
                if ((int) table.getValueAt(i,j) >= value)
                    value = (int) table.getValueAt(i,j);

        textArea.append("Maksymalna wartość z tabeli: "+value+"\n");
    }
}
