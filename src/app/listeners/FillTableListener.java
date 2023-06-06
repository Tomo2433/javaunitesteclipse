package app.listeners;

import app.Logger.MyLogger;
import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>FillTableListener</code> definiująca nasłuch dla metody
 * wypełniania tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class FillTableListener implements ActionListener {

    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private JTextField textField;
    private int rows, cols;

    /**
     * Kontruktor dla klasy <code>FillTableListener</code>
     * @param tableModel
     * @param textArea
     * @param textField
     */
    public FillTableListener(IntegerTableModel tableModel,
                             JTextArea textArea, JTextField textField){
        this.tableModel = tableModel;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
        this.textArea = textArea;
        this.textField = textField;
    }

    /**
     * Metoda akcji wypełniania tabeli
     * @param e the event to be processed
     */
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
