package GuiApp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenterPanel extends JPanel implements ActionListener {

    private static final int TABLE_ROWS = 5;
    private static final int TABLE_COLS = 5;
    private JPanel parameterPanel, tableWithButtonsPanel, operationsPanel,
                    resultPanel,operationsButtonsPanel;
    private JTextField numberTextField, rowTextField, colTextField;
    private JTextArea resultTextArea;
    private JScrollPane scrollPane;
    private JSlider rowSlider, columnSlider;
    private JLabel numberLabel, rowLabel, colLabel, operationLabel;
    private JButton jbtAdd, jbtZero, jbtFill, jbtSave, jbtCount;
    private JTable table;
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

    CenterPanel() {
        Icons icons = new Icons();
        createGUI();
    }
    public void createGUI() {
        this.setLayout(new BorderLayout());
        parameterPanel = createParametersPanel();
        tableWithButtonsPanel = createTableWithButtonsPanel();

        resultPanel = createResultPanel();

        this.add(parameterPanel, BorderLayout.NORTH);
        this.add(tableWithButtonsPanel, BorderLayout.CENTER);

        this.add(resultPanel, BorderLayout.SOUTH);
    }
    public JPanel createParametersPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        //jPanel.setPreferredSize(new Dimension(350, 30));

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
    public JPanel createTableWithButtonsPanel() {
        JPanel jPanel = new JPanel();
        scrollPane = new JScrollPane();
        jPanel.setLayout(new BorderLayout());
        operationsPanel = createOperationPanel(new Icons());

        table = new JTable(data, tableColumnNames);
        table.setEnabled(false);

        scrollPane.setPreferredSize(new Dimension(500,100));
        scrollPane.setViewportView(table);

        jPanel.add(scrollPane, BorderLayout.WEST);

        jPanel.add(operationsPanel, BorderLayout.SOUTH);

        return jPanel;
    }
    public JPanel createOperationsButtonsPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        return jPanel;
    }
    public JPanel createOperationPanel(Icons icons){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        operationLabel = new JLabel("Wybierz operacje");
        jbtCount = createJButton("Oblicz", icons.mIconAvg );
        jPanel.add(operationLabel);
        jPanel.add(new JComboBox<>(new String[]{"Opcja 1", "Opcja 2", "Opcja 3"}));
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

        resultTextArea = new JTextArea();
        resultTextArea.setPreferredSize(new Dimension(350,100));
        resultTextArea.setLineWrap(true);

        resultTextArea.setEditable(false);
        resultTextArea.append("Start aplikacji\n");
        jPanel.add(new JScrollPane(resultTextArea),BorderLayout.CENTER);

        return jPanel;
    }
    private JButton createJButton(String text, Icon icon){
        JButton jButton = new JButton(text, icon);
        jButton.addActionListener(this);
        return jButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }
}
