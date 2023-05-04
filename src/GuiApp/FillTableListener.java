package GuiApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillTableListener implements ActionListener {

    private JTable table;
    private JTextArea textArea;
    private JTextField textField;
    private int rows, cols;
    FillTableListener(JTable table, int rows, int cols,
                      JTextArea textArea, JTextField textField){
        this.table = table;
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
                table.setValueAt(value,i,j);
        textArea.append("Wypełniono tabelę wartością: "+value+"\n");
        }catch (NumberFormatException ne){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Błąd podana wartość nie jest liczbą!!!" );
        }
    }
}
