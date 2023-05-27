package app.view;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


public class CenterPanel extends JPanel {

    protected static final int TABLE_ROWS = 5;
    protected static final int TABLE_COLS = 5;
    private JPanel parameterPanel, tablePanel, operationsPanel,
                    resultPanel,operationsButtonsPanel;
    protected JTextField numberTextField, rowTextField, colTextField;
    protected JTextArea resultTextArea = new JTextArea();
    private JScrollPane tableScrollPane, textAreaScrollPane;
    protected JSlider rowSlider, columnSlider;
    private JLabel numberLabel, rowLabel, colLabel, operationLabel;

    private JButton jbtAdd, jbtZero, jbtFill, jbtSave, jbtCount;
    protected JTable table;
    public Object[][] data = {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
    };
    private Object[] tableColumnNames = {"1", "2", "3", "4", "5"};
    private TitledBorder titledBorder;
    private Border border;
    private JComboBox jComboBox;


    public CenterPanel() {
        Icons icons = new Icons();
        createGUI(icons);
    }
    public void createGUI(Icons icons) {
        this.setLayout(new BorderLayout());
        parameterPanel = createParametersPanel();
        tablePanel = createTablePanel();
        operationsButtonsPanel = createOperationsButtonsPanel(icons);
        operationsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        resultPanel = createResultPanel();

        this.add(parameterPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(operationsButtonsPanel, BorderLayout.EAST);
        this.add(resultPanel, BorderLayout.SOUTH);
    }
    public JPanel createParametersPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setPreferredSize(new Dimension(350, 35));

        numberTextField = new JTextField("0");
        numberTextField.setPreferredSize(new Dimension(50,20));

        rowTextField = new JTextField("1");
        rowTextField.setEditable(false);
        rowSlider = new JSlider(JSlider.HORIZONTAL,
                1, TABLE_ROWS, 1);
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
                1, TABLE_COLS, 1);
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
    public JPanel createTablePanel() {
        JPanel jPanel = new JPanel();
        tableScrollPane = new JScrollPane();
        jPanel.setLayout(new BorderLayout());
        operationsPanel = createOperationPanel(new Icons());

        table = new JTable(data, tableColumnNames);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setPreferredSize(new Dimension(500,100));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tableScrollPane.setViewportView(table);

        jPanel.add(tableScrollPane, BorderLayout.CENTER);

        jPanel.add(operationsPanel, BorderLayout.SOUTH);

        return jPanel;
    }
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


    public JPanel createOperationPanel(Icons icons){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        operationLabel = new JLabel("Obliczenia");
        jbtCount = createJButton("Oblicz", icons.mIconAvg );

        jPanel.add(operationLabel);
        jComboBox = new JComboBox<>(new String[]{"Wybierz operację",
                "Sumowanie", "Średnia", "Min i Max"});

        jPanel.add(jComboBox);
        jPanel.add(jbtCount);

        return jPanel;
    }
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
    private JButton createJButton(String text, Icon icon){
        JButton jButton = new JButton(text, icon);
        jButton.setMaximumSize(new Dimension(100,23));
        jButton.setFont(new Font("Arial", Font.BOLD, 10));
        return jButton;
    }

    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }
    public JButton getJbtAdd() {
        return jbtAdd;
    }
    public JButton getJbtZero() {
        return jbtZero;
    }
    public JButton getJbtFill() {
        return jbtFill;
    }
    public JButton getJbtSave() {
        return jbtSave;
    }
    public JButton getJbtCount() {
        return jbtCount;
    }
    public JComboBox getjComboBox() {
        return jComboBox;
    }
    public JTextArea getResultTextArea() {
        return resultTextArea;
    }
    public JTextField getNumberTextField() {
        return numberTextField;
    }
    public JSlider getColumnSlider() {
        return columnSlider;
    }
    public JSlider getRowSlider() {
        return rowSlider;
    }
    public JTable getTable() {
        return table;
    }
    public static int getTableCols() {
        return TABLE_COLS;
    }
    public static int getTableRows() {
        return TABLE_ROWS;
    }
}
