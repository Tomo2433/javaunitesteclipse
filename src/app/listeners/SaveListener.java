package app.listeners;

import app.model.IntegerTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private File file, folder;
    private PrintWriter printWriter;
    private int rows, cols;
    public SaveListener(IntegerTableModel tableModel, JTextArea textArea,
                        int rows, int cols){
        this.tableModel = tableModel;
        this.rows = rows;
        this.cols = cols;
        this.textArea = textArea;
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Zapisz plik");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));
        JFrame fileFrame = new JFrame();
        if(jFileChooser.showSaveDialog(fileFrame) == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            try {
                printWriter = new PrintWriter(file);
                for(int i=1; i<=rows; i++){
                    for (int j=1; j<=cols; j++){
                        printWriter.print("["+i+","+j+"]");
                        printWriter.print(": ");
                        printWriter.print(tableModel.getValueAt(i-1,j-1));
                        printWriter.print("\t");
                    }
                    printWriter.println("");
                }
                printWriter.close();
                textArea.append("Zapisano wartości tabeli do pliku "+file.getName()+"\n");
            }catch (IOException ioex){
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame,
                        "Błąd nie znaleziono pliku!!!");
                ioex.printStackTrace();
            }
        }
    }
}
