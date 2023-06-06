package app.listeners;

import app.Logger.MyLogger;
import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillTableListener implements ActionListener {

    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private JTextField textField;
    private int rows, cols;
    public FillTableListener(IntegerTableModel tableModel, int rows, int cols,
                             JTextArea textArea, JTextField textField){
        this.tableModel = tableModel;
        this.rows = rows;
        this.cols = cols;
        this.textArea = textArea;
        this.textField = textField;
    }

    public void actionPerformed(ActionEvent e) {
        try{
        int value = Integer.parseInt(textField.getText());
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                tableModel.setValue(value,i,j);
        textArea.append("Wypełniono tabelę wartością: "+value+"\n");
        }catch (NumberFormatException ne){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Błąd podana wartość nie jest liczbą!!!" );
            MyLogger.writeLog("ERROR","Błąd podana wartość nie jest liczbą");
        }
    }
}
