package GuiApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZeroTableValuesListener implements ActionListener {

    private JTable table;
    private JTextArea textArea;
    private int rows, cols;
    ZeroTableValuesListener(JTable table, int rows, int cols, JTextArea textArea){
        this.table = table;
        this.rows = rows;
        this.cols = cols;
        this.textArea = textArea;
    }
    public void actionPerformed(ActionEvent e){
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                table.setValueAt(0,i,j);
        textArea.append("Wyzerowano tabele\n");
    }
}
