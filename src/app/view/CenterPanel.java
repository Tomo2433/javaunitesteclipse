package app.view;

import app.model.IntegerTableModel;
import app.model.SimpleComboBoxModel;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>CenterPanel</code> definiująca panel centralny aplikacji
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class CenterPanel extends JPanel {

    private JPanel parameterPanel, tablePanel, operationsPanel,
                    resultPanel,operationsButtonsPanel, taskPanePanel;
    private JTaskPane taskPane;
    private JTaskPaneGroup taskPaneCountGroup, taskPaneChartGroup,
                            taskPaneHelpGroup;
    protected JTextField numberTextField, rowTextField, colTextField;
    protected JTextArea resultTextArea = new JTextArea();
    private JScrollPane tableScrollPane, textAreaScrollPane;
    protected JSlider rowSlider, columnSlider;
    private JLabel numberLabel, rowLabel, colLabel, operationLabel;

    private JButton jbtAdd, jbtZero, jbtFill, jbtSave, jbtCount;
    private IntegerTableModel tableModel;
    private ComboBoxModel comboBoxModel;
    protected JTable table;
    private TitledBorder titledBorder;
    private Border border;
    private JComboBox jComboBox;
    private JDateChooser dateChooser;
    private Icons icons;
    private TableView tableView;

    /**
     * Konstruktor bezparametrowy klasy <code>CenterPanel</code>
     */
    public CenterPanel() {
        icons = new Icons();
        tableView = new TableView();
        tableModel = tableView.getTableModel();
        comboBoxModel = new SimpleComboBoxModel();
        createGUI(icons);
    }

    /**
     * Metoda tworząca gui panelu centralnego
     * @param icons
     */
    public void createGUI(Icons icons) {
        this.setLayout(new BorderLayout());
        taskPanePanel = createJTaskPane(icons);
        parameterPanel = createParametersPanel();
        tablePanel = createTablePanel();
        operationsButtonsPanel = createOperationsButtonsPanel(icons);
        operationsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        resultPanel = createResultPanel();

        this.add(parameterPanel, BorderLayout.NORTH);
        this.add(taskPanePanel, BorderLayout.WEST);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(operationsButtonsPanel, BorderLayout.EAST);
        this.add(resultPanel, BorderLayout.SOUTH);
    }

    /**
     * Metoda tworząca panel parametrów dodawanej wartości
     * @return JPanel
     */
    public JPanel createParametersPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setPreferredSize(new Dimension(350, 35));

        numberTextField = new JTextField("0");
        numberTextField.setPreferredSize(new Dimension(50,20));

        rowTextField = new JTextField("1");
        rowTextField.setEditable(false);
        rowSlider = new JSlider(JSlider.HORIZONTAL,
                1, tableModel.getRowCount(), 1);
        rowSlider.setMinorTickSpacing(1);
        rowSlider.setPaintTicks(true);
        rowSlider.setPreferredSize(new Dimension(100,20));
        rowSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            int value = source.getValue();
            rowTextField.setText(Integer.toString(value));
        });

        colTextField = new JTextField("1");
        colTextField.setEditable(false);
        columnSlider = new JSlider(JSlider.HORIZONTAL,
                1, tableModel.getColumnCount(), 1);
        columnSlider.setMinorTickSpacing(1);
        columnSlider.setPaintTicks(true);
        columnSlider.setPreferredSize(new Dimension(100,20));
        columnSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            int value = source.getValue();
            colTextField.setText(Integer.toString(value));
        });

        numberLabel = new JLabel("Wprowadź liczbę");
        rowLabel = new JLabel("Numer wiersza");
        colLabel = new JLabel("Numer kolumny");

        jPanel.add(numberLabel);
        jPanel.add(numberTextField);
        jPanel.add(rowLabel);
        jPanel.add(rowSlider);
        jPanel.add(rowTextField);
        jPanel.add(colLabel);
        jPanel.add(columnSlider);
        jPanel.add(colTextField);

        return jPanel;
    }

    /**
     * Metoda tworząca panel z tabela
     * @return
     */
    public JPanel createTablePanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        operationsPanel = createOperationPanel(new Icons());

        table = tableView.getTable();
        tableScrollPane = tableView.getTableScrollPane();

        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.add(operationsPanel, BorderLayout.SOUTH);

        return jPanel;
    }

    /***
     * Metoda tworząca panel przycisków operacji
     * @param icons
     * @return JPanel
     */
    public JPanel createOperationsButtonsPanel(Icons icons){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jbtAdd = createJButton("Dodaj", icons.mIconAdd);
        jbtZero = createJButton("Wyzeruj", icons.mIconZero);
        jbtFill = createJButton("Wypełnij", icons.mIconFill);
        jbtSave = createJButton("Zapisz", icons.mIconSave);
        
        jPanel.add(jbtAdd);
        jPanel.add(Box.createVerticalStrut(5));
        jPanel.add(jbtZero);
        jPanel.add(Box.createVerticalStrut(5));
        jPanel.add(jbtFill);
        jPanel.add(Box.createVerticalStrut(5));
        jPanel.add(jbtSave);

        return jPanel;
    }

    /**
     * Metoda zwraca panel operacji
     * @param icons
     * @return JPanel
     */
    public JPanel createOperationPanel(Icons icons){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        operationLabel = new JLabel("Obliczenia");
        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.setPreferredSize(new Dimension(100,20));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        jbtCount = createJButton("Oblicz", icons.mIconAvg );

        jPanel.add(operationLabel);
        jComboBox = new JComboBox();
        jComboBox.setModel(comboBoxModel);
        jComboBox.setSelectedItem(comboBoxModel.getElementAt(0));

        jPanel.add(jComboBox);
        jPanel.add(jbtCount);
        jPanel.add(dateChooser);

        return jPanel;
    }

    /**
     * Metoda zwreaca Panel wyniku
     * @return JPanel
     */
    public JPanel createResultPanel() {
        JPanel jPanel = new JPanel();

        border = BorderFactory.createLineBorder(Color.gray);
        titledBorder = BorderFactory.createTitledBorder(border,
                "Uzyskany rezultat");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        jPanel.setBorder(titledBorder);
        jPanel.setLayout(new BorderLayout());

        resultTextArea.setLineWrap(true);
        resultTextArea.setEditable(false);
        resultTextArea.setWrapStyleWord(true);
        textAreaScrollPane = new JScrollPane(resultTextArea);
        textAreaScrollPane.setPreferredSize(new Dimension(350,100));
        jPanel.add(textAreaScrollPane,BorderLayout.CENTER);

        return jPanel;
    }

    /**
     * Metoda tworzy Panel task pane
     * @param icons
     * @return JPanel
     */
    public JPanel createJTaskPane(Icons icons) {
        JPanel jPanel = new JPanel();
        taskPane = new JTaskPane();
        taskPaneCountGroup = new JTaskPaneGroup();
        taskPaneChartGroup = new JTaskPaneGroup();
        taskPaneHelpGroup = new JTaskPaneGroup();
        jPanel.setLayout(new BorderLayout());
        jPanel.add("Center", new JScrollPane(taskPane));

        taskPaneCountGroup.setTitle("Obliczenia");
        taskPaneCountGroup.setIcon(icons.mIconSigma);
        taskPaneChartGroup.setTitle("Wykres");
        taskPaneChartGroup.setIcon(icons.mIconChart);
        taskPaneHelpGroup.setTitle("Pomoc");
        taskPaneHelpGroup.setIcon(icons.mIconHelp);
        taskPane.add(taskPaneCountGroup);
        taskPane.add(taskPaneChartGroup);
        taskPane.add(taskPaneHelpGroup);
        taskPaneCountGroup.setExpanded(false);
        taskPaneChartGroup.setExpanded(true);
        taskPaneHelpGroup.setExpanded(false);

        return jPanel;
    }

    /**
     * Metoda tworzy przycisk
     * @param text
     * @param icon
     * @return JButton
     */
    private JButton createJButton(String text, Icon icon){
        JButton jButton = new JButton(text, icon);
        jButton.setMaximumSize(new Dimension(100,23));
        jButton.setFont(new Font("Arial", Font.BOLD, 10));
        return jButton;
    }

    /**
     * Metoda tworzy wewnętrze marginesy
     * @return
     */
    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }

    /**
     * metoda zwraca jbtAdd
     * @return Jbutton
     */
    public JButton getJbtAdd() {
        return jbtAdd;
    }
    /**
     * metoda zwraca jbtZero
     * @return Jbutton
     */
    public JButton getJbtZero() {
        return jbtZero;
    }
    /**
     * metoda zwraca jbtFill
     * @return Jbutton
     */
    public JButton getJbtFill() {
        return jbtFill;
    }
    /**
     * metoda zwraca jbtSave
     * @return Jbutton
     */
    public JButton getJbtSave() {
        return jbtSave;
    }
    /**
     * metoda zwraca jbtCount
     * @return Jbutton
     */
    public JButton getJbtCount() {
        return jbtCount;
    }
    /**
     * metoda zwraca combobox
     * @return JCombobox
     */
    public JComboBox getjComboBox() {
        return jComboBox;
    }

    /**
     * Metoda zwraca obszar tekstu
     * @return JtextArea
     */
    public JTextArea getResultTextArea() {
        return resultTextArea;
    }
    /**
     * Metoda zwraca pole tekstowe
     * @return JtextArea
     */
    public JTextField getNumberTextField() {
        return numberTextField;
    }
    /**
     * Metoda zwraca Slider wyboru kolumny
     * @return JSlider
     */
    public JSlider getColumnSlider() {
        return columnSlider;
    }
    /**
     * Metoda zwraca Slider wyboru wiersza
     * @return JSlider
     */
    public JSlider getRowSlider() {
        return rowSlider;
    }

    /**
     * Metoda zwraca obiekt IntegerTableModel;
     * @return IntegerTableModel
     */
    public IntegerTableModel getTableModel() { return tableView.getTableModel(); }

    /**
     * Metoda Zwraca grupę zadań obliczeń
     * @return JTaskPane
     */
    public JTaskPaneGroup getTaskPaneCountGroup() {
        return taskPaneCountGroup;
    }

    /**
     * Metoda Zwraca grupę zadań wykresu
     * @return JTaskPane
     */
    public JTaskPaneGroup getTaskPaneChartGroup() {
        return taskPaneChartGroup;
    }
    /**
     * Metoda Zwraca grupę zadań pomopcy
     * @return JTaskPane
     */
    public JTaskPaneGroup getTaskPaneHelpGroup() {
        return taskPaneHelpGroup;
    }

    /**
     * Metoda zwraca wybierak daty
     * @return JDateChooser
     */
    public JDateChooser getDateChooser() {
        return dateChooser;
    }
}
