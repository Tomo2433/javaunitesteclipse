package GuiApp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    CenterPanel() {
        Icons icons = new Icons();

        createGUI(icons);
    }
    public void createGUI(Icons icons) {
        this.setLayout(new BorderLayout());
        parameterPanel = createParametersPanel();
        tablePanel = createTablePanel();
        operationsButtonsPanel = createOperationsButtonsPanel(icons);
        resultPanel = createResultPanel();

        this.add(parameterPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.WEST);
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

        tableScrollPane.setPreferredSize(new Dimension(500,100));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tableScrollPane.setViewportView(table);

        jPanel.add(tableScrollPane, BorderLayout.WEST);

        jPanel.add(operationsPanel, BorderLayout.SOUTH);

        return jPanel;
    }
    public JPanel createOperationsButtonsPanel(Icons icons){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jbtAdd = createJButton("Dodaj", icons.mIconAdd);
        jbtAdd.addActionListener(new AddValueListener(numberTextField,
                rowSlider,columnSlider, table, resultTextArea));
        jbtZero = createJButton("Wyzeruj", icons.mIconZero);
        jbtZero.addActionListener(new ZeroTableValuesListener(table, TABLE_ROWS,
                TABLE_COLS, resultTextArea));
        jbtFill = createJButton("Wypełnij", icons.mIconFill);
        jbtFill.addActionListener(new FillTableListener(table, TABLE_ROWS,
                TABLE_COLS, resultTextArea, numberTextField));
        jbtSave = createJButton("Zapisz", icons.mIconSave);
        jbtSave.addActionListener(new SaveListener(table, resultTextArea,
                TABLE_ROWS, TABLE_COLS));

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
        jbtCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTextArea.append("Wybierz operacje! \n");
            }
        });

        jPanel.add(operationLabel);
        jComboBox = new JComboBox<>(new String[]{"Wybierz operację",
                "Sumowanie", "Średnia", "Min i Max"});
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(ActionListener l : jbtCount.getActionListeners())
                    jbtCount.removeActionListener(l);

                String selectedOption = (String) jComboBox.getSelectedItem();
                if (selectedOption.equals("Sumowanie")){
                    jbtCount.addActionListener(new SumListener(table,
                            resultTextArea, TABLE_ROWS, TABLE_COLS));
                } else if (selectedOption.equals("Średnia")) {
                    jbtCount.addActionListener(new AvgListener(table,
                            resultTextArea, TABLE_ROWS, TABLE_COLS));
                } else if (selectedOption.equals("Min i Max")) {
                    jbtCount.addActionListener(new MinAndMaxListener(table,
                            resultTextArea, TABLE_ROWS, TABLE_COLS));
                } else {
                    jbtCount.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resultTextArea.append("Wybierz operacje! \n");
                        }
                    });
                }
            }
        });

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
        textAreaScrollPane = new JScrollPane(resultTextArea);
        textAreaScrollPane.setPreferredSize(new Dimension(350,100));
        jPanel.add(textAreaScrollPane,BorderLayout.CENTER);

        return jPanel;
    }
    private JButton createJButton(String text, Icon icon){
        JButton jButton = new JButton(text, icon);
        jButton.setMaximumSize(new Dimension(100,23));
        jButton.setFont(new Font("Arial", Font.BOLD, 11));
        return jButton;
    }

    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }
}
