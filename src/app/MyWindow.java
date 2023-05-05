package app;

import app.listeners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static app.CenterPanel.TABLE_COLS;
import static app.CenterPanel.TABLE_ROWS;

public class MyWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH_SIZE = 650;
    private final int WINDOW_HEIGHT_SIZE = 450;
    private static final String ICON_PATH = "/resources/";
    private JPanel contentPane;
    private CenterPanel centerPanel;
    private BottomStatusPanel bottomStatusPanel;


    //menu variables definition
    private JMenu fileMenu, editMenu, viewMenu, calculationsMenu, helpMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem;

    //toolbar variables definition
    JButton jtbSave, jtbPrint, jtbExit, jtbAdd, jtbZero,
            jtbFill, jtbSigma, jtbAvg, jtbMin,
            jtbMax, jtbHelp, jtbAbout;

    public MyWindow(){
        setTitle("MyWindow v1.0.1");
        setSize(WINDOW_WIDTH_SIZE,WINDOW_HEIGHT_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windowClose();
            }
        });

        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout());

        try{
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Icons icons = new Icons();
                    createMenus(icons);
                    createGUI(icons);
                }
            });
        }
        catch (Exception e){
            System.out.println("ERROR - error while creating gui of application");
        }
    }

    private void createMenus(Icons icons){
        //create menu bar
        JMenuBar menuBar = new JMenuBar();
        //create main menu poles
        fileMenu = createJMenu("Plik", KeyEvent.VK_P);
        editMenu = createJMenu("Edycja", KeyEvent.VK_E);
        viewMenu = createJMenu("Widok", KeyEvent.VK_W);
        calculationsMenu = createJMenu("Obliczenia", KeyEvent.VK_O);
        helpMenu = createJMenu("Pomoc", KeyEvent.VK_I);
        //create menu item
        exitMenuItem = createJMenuItem("Zamknij",icons.mIconExit,
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        aboutMenuItem = createJMenuItem("O autorze",icons.mIconAbout,
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        helpMenuItem = createJMenuItem("Kontekst pomocy",icons.mIconHelp,
                KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(calculationsMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }
    public void createGUI(Icons icons){
        centerPanel = new CenterPanel();
        bottomStatusPanel = new BottomStatusPanel();

        contentPane.add(createJToolBar(icons), BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomStatusPanel, BorderLayout.SOUTH);
    }
    public Icon createMyIcon(String nameFile){
        String name = ICON_PATH + nameFile;
        Icon icon = null;
        URL url = getClass().getResource(name);
        if(url != null) icon = new ImageIcon(url);
        return icon;
    }
    public JMenu createJMenu(String name, int keyEvent){
        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(keyEvent);
        return jMenu;
    }
    public JMenuItem createJMenuItem(String name, Icon icon, KeyStroke key){
        JMenuItem jMenuItem;
        if(icon != null)
            jMenuItem = new JMenuItem(name, icon);
        else jMenuItem = new JMenuItem(name);
        jMenuItem.setAccelerator(key);
        jMenuItem.addActionListener(this);
        return jMenuItem;
    }
    private JToolBar createJToolBar(Icons icons){
        JToolBar jToolBar = new JToolBar();
        jToolBar.setFloatable(false);

        jtbSave = createJButtonToolBar("Zapisanie danych", icons.iconSave);
        jtbPrint = createJButtonToolBar("Drukowanie", icons.iconPrint);
        jtbExit = createJButtonToolBar("Wyjście z aplikacji", icons.iconExit);
        jtbAdd = createJButtonToolBar("Dodaj wartość", icons.iconAdd);
        jtbZero = createJButtonToolBar("Wyzeruj tabele", icons.iconZero);
        jtbFill = createJButtonToolBar("Wypełnij tabele", icons.iconFill);
        jtbSigma = createJButtonToolBar("Sumowanie", icons.iconSigma);
        jtbAvg = createJButtonToolBar("Średnia", icons.iconAvg);
        jtbMin = createJButtonToolBar("Minimum", icons.iconMin);
        jtbMax = createJButtonToolBar("Maksimum", icons.iconMax);
        jtbHelp = createJButtonToolBar("Pomoc", icons.iconHelp);
        jtbAbout = createJButtonToolBar("O autorze", icons.iconAbout);

        jtbMin.setSize(24,24);
        jtbMax.setSize(24,24);

        jToolBar.add(Box.createHorizontalStrut(5));
        jToolBar.add(jtbSave);
        jToolBar.add(jtbPrint);
        jToolBar.add(jtbExit);
        jToolBar.addSeparator();
        jToolBar.add(jtbAdd);
        jToolBar.add(jtbZero);
        jToolBar.add(jtbFill);
        jToolBar.addSeparator();
        jToolBar.add(jtbSigma);
        jToolBar.add(jtbAvg);
        jToolBar.add(jtbMin);
        jToolBar.add(jtbMax);
        jToolBar.addSeparator();
        jToolBar.add(jtbHelp);
        jToolBar.add(jtbAbout);

        jtbAdd.addActionListener(new AddValueListener(centerPanel.numberTextField,
                centerPanel.rowSlider, centerPanel.columnSlider,
                centerPanel.table, centerPanel.resultTextArea));
        jtbZero.addActionListener(new ZeroTableValuesListener(centerPanel.table,
                TABLE_ROWS, TABLE_COLS, centerPanel.resultTextArea));
        jtbFill.addActionListener(new FillTableListener(centerPanel.table,
                TABLE_ROWS, TABLE_COLS, centerPanel.resultTextArea,
                centerPanel.numberTextField));
        jtbSave.addActionListener(new SaveListener(centerPanel.table,
                centerPanel.resultTextArea, TABLE_ROWS, TABLE_COLS));
        jtbSigma.addActionListener(new SumListener(centerPanel.table,
                centerPanel.resultTextArea, TABLE_ROWS, TABLE_COLS));
        jtbAvg.addActionListener(new AvgListener(centerPanel.table,
                centerPanel.resultTextArea, TABLE_ROWS, TABLE_COLS));
        jtbMin.addActionListener(new MinListener(centerPanel.table,
                centerPanel.resultTextArea, TABLE_ROWS, TABLE_COLS));
        jtbMax.addActionListener(new MaxListener(centerPanel.table,
                centerPanel.resultTextArea, TABLE_ROWS, TABLE_COLS));

        return jToolBar;
    }
    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jButton = new JButton("", icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        return jButton;
    }
//    private JPanel createCenterPanel(){
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        jPanel.setBackground(Color.LIGHT_GRAY);
//
//        return jPanel;
//    }

    private void windowClose(){
        int value = JOptionPane.showOptionDialog(this,
                "Czy chcesz zamknąć aplikacje?",
                "UWAGA",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[] {"Tak", "Nie"},
                "Tak");
        if(value == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
