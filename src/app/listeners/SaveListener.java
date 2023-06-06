package app.listeners;

import app.Logger.MyLogger;
import app.model.IntegerTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveListener implements ActionListener {
    private IntegerTableModel tableModel;
    private JTextArea textArea;
    private File file, folder;
    private PrintWriter printWriter;
    private String filePath;
    private FileFilter selectedFileFilter, textFilter, pdfFilter;
    private int rows, cols;
    public SaveListener(IntegerTableModel tableModel, JTextArea textArea){
        this.tableModel = tableModel;
        this.rows = tableModel.getRowCount();
        this.cols = tableModel.getColumnCount();
        this.textArea = textArea;
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Zapisz plik");
        textFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        pdfFilter = new FileNameExtensionFilter("Pdf files (*.pdf)","pdf");
        jFileChooser.addChoosableFileFilter(textFilter);
        jFileChooser.addChoosableFileFilter(pdfFilter);
        JFrame fileFrame = new JFrame();
        if(jFileChooser.showSaveDialog(fileFrame) == JFileChooser.APPROVE_OPTION){
            file = jFileChooser.getSelectedFile();
            filePath = file.getAbsolutePath();
            // Pobierz wybrany filtr plików
            selectedFileFilter = jFileChooser.getFileFilter();
            if (selectedFileFilter == textFilter) {
                // Eksport do pliku tekstowego (.txt)
                saveToTextFile();
            } else if (selectedFileFilter == pdfFilter) {
                // Eksport do pliku PDF (.pdf)
                exportToPdf();
            }
        }
    }

    private void saveToTextFile() {
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
            MyLogger.writeLog("ERROR","Nie znaleziono pliku");
            ioex.printStackTrace();
        }
    }
    private void exportToPdf() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath+".pdf"));
            document.open();

            PdfPTable pdfTable = new PdfPTable(tableModel.getColumnCount());

            // Dodaj nagłówki kolumn
            for (int column = 0; column < tableModel.getColumnCount(); column++) {
                PdfPCell headerCell = new PdfPCell(new Paragraph(tableModel.getColumnName(column)));
                headerCell.setBackgroundColor(BaseColor.GRAY);
                pdfTable.addCell(headerCell);
            }

            // Dodaj dane wierszy
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int column = 0; column < tableModel.getColumnCount(); column++) {
                    Object value = tableModel.getValueAt(row, column);
                    PdfPCell cell = new PdfPCell(new Paragraph(value.toString()));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
        } catch (IOException | DocumentException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Błąd nie znaleziono pliku!!!");
            MyLogger.writeLog("ERROR","Nie znaleziono pliku");
            e.printStackTrace();
        } finally {
            document.close();
            textArea.append("Zapisano wartości tabeli do pliku "+file.getName()+".pdf"+"\n");
        }
    }
}
