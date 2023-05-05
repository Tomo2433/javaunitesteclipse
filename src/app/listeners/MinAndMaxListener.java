package app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinAndMaxListener implements ActionListener {
    private JTable table;
    private JTextArea textArea;
    private int rows, cols;
    private int value;


    public MinAndMaxListener(JTable table, JTextArea textArea, int rows, int cols){
        this.table = table;
        this.textArea = textArea;
        this.rows = rows;
        this.cols = cols;
    }
    public void actionPerformed(ActionEvent e) {
        int max = (int) table.getValueAt(0, 0);
        int min = max;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((int) table.getValueAt(i, j) >= max)
                    max = (int) table.getValueAt(i, j);
                if ((int) table.getValueAt(i, j) <= min)
                    min = (int) table.getValueAt(i, j);
            }
        }

        textArea.append("Maksymalna wartość z tabeli: " + max + "\n");
        textArea.append("Minimalna wartość z tabeli: " + min + "\n");
    }
}
