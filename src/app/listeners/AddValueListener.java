package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddValueListener implements ActionListener {

    private JSlider rowSlider, colSlider;
    private IntegerTableModel tableModel;
    private JTextField jTextField;
    private JTextArea jTextArea;
    public AddValueListener(JTextField jTextField, JSlider rowSlider,
                            JSlider colSlider, IntegerTableModel tableModel,
                            JTextArea jTextArea){
        this.rowSlider = rowSlider;
        this.colSlider = colSlider;
        this.jTextField = jTextField;
        this.tableModel = tableModel;
        this.jTextArea = jTextArea;
    }
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
        }
    }
}
