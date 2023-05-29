package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZeroTableValuesListener implements ActionListener {

    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private int rows, cols;
    public ZeroTableValuesListener(IntegerTableModel tableModel, int rows,
                                   int cols, JTextArea textArea){
        this.tableModel = tableModel;
        this.rows = rows;
        this.cols = cols;
        this.textArea = textArea;
    }
    public void actionPerformed(ActionEvent e){
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                tableModel.setValue(0,i,j);
        textArea.append("Wyzerowano tabele\n");
    }
}
