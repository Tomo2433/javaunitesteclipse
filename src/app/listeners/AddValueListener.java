package app.listeners;

import app.Logger.MyLogger;
import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>AddValueListener</code> definiująca nasłuch dla metody dodawania
 * danych do komórek tabeli
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class AddValueListener implements ActionListener {

    private JSlider rowSlider, colSlider;
    private IntegerTableModel tableModel;
    private JTextField jTextField;
    private JTextArea jTextArea;

    /**
     * Kontruktor klasy <code>AddValueListener</code>
     * @param jTextField
     * @param rowSlider
     * @param colSlider
     * @param tableModel
     * @param jTextArea
     */
    public AddValueListener(JTextField jTextField, JSlider rowSlider,
                            JSlider colSlider, IntegerTableModel tableModel,
                            JTextArea jTextArea){
        this.rowSlider = rowSlider;
        this.colSlider = colSlider;
        this.jTextField = jTextField;
        this.tableModel = tableModel;
        this.jTextArea = jTextArea;
    }

    /**
     * Metoda akcji dodawania wartości do tabeli
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        try{
            String text = jTextField.getText();
            int rowValue = rowSlider.getValue();
            int colValue = colSlider.getValue();
            int value = Integer.parseInt(text);
            tableModel.setValue(value, rowValue-1, colValue-1);
            jTextArea.append("Dodano "+value+" do wiersza: "+rowValue+" kolumny: "+colValue+"\n");
        }catch (NumberFormatException ne){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Błąd podana wartość nie jest liczbą!!!" );
            MyLogger.writeLog("ERROR","Błąd podana wartość nie jest liczbą");
        }
    }
}
