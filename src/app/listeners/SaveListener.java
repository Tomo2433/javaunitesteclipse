package app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveListener implements ActionListener {
    private JTable table;
    private JTextArea textArea;
    private File file, folder;
    private PrintWriter printWriter;
    private int rows, cols;
    public SaveListener(JTable table, JTextArea textArea, int rows, int cols){
        this.table = table;
        this.rows = rows;
        this.cols = cols;
        this.textArea = textArea;
    }
    public void actionPerformed(ActionEvent e){
        file = new File("tabela.txt");
        try {
            printWriter = new PrintWriter(file);
            for(int i=1; i<=rows; i++){
                for (int j=1; j<=cols; j++){
                    printWriter.print("["+i+","+j+"]");
                    printWriter.print(": ");
                    printWriter.print(table.getValueAt(i-1,j-1));
                    printWriter.print("\t");
                }
                printWriter.println("");
            }
            printWriter.close();
            textArea.append("Zapisano wartości tabeli do pliku tabela.txt\n");
        }catch (FileNotFoundException fnfe){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Błąd nie znaleziono pliku!!!");
        }
    }
}
