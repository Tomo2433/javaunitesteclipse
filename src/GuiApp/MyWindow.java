package GuiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MyWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH_SIZE = 650;
    private final int WINDOW_HEIGHT_SIZE = 450;
    private static final String ICON_PATH = "/resources/";
    private JPanel contentPane;

    //icons definition
    private Icon iconExit, iconAbout, iconHelp, iconAdd,
            iconAvg, iconFill, iconLogin, iconLogout,
            iconMax, iconMin, iconPrint, iconZero, iconSave,
            iconSigma;
    private Icon mIconExit, mIconAbout, mIconHelp, mIconAdd,
            mIconAvg, mIconFill, mIconLogin, mIconLogout,
            mIconMax, mIconMin, mIconPrint, mIconZero, mIconSave,
            mIconSigma;

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
                    createIcons();
                    createMenus();
                    createGUI();
                }
            });
        }
        catch (Exception e){
            System.out.println("ERROR - error while creating gui of application");
        }
    }
    private void createIcons(){
        //create 24x24px icons for toolbar
        iconExit = createMyIcon("exit.png");
        iconAbout = createMyIcon("about.png");
        iconHelp = createMyIcon("help.png");
        iconAdd = createMyIcon("add.png");
        iconAvg = createMyIcon("avg.png");
        iconFill = createMyIcon("fill.png");
        iconLogin = createMyIcon("login.png");
        iconLogout = createMyIcon("logout.png");
        iconMax = createMyIcon("max.png");
        iconMin = createMyIcon("min.png");
        iconPrint = createMyIcon("print.png");
        iconZero = createMyIcon("zero.png");
        iconSave = createMyIcon("save.png");
        iconSigma = createMyIcon("sigma.png");
        //create 16x16px icons for menubar
        mIconExit = createMyIcon("min_close.png");
        mIconAbout = createMyIcon("min_about.png");
        mIconHelp = createMyIcon("min_help.png");
        mIconAdd = createMyIcon("min_add.png");
        mIconAvg = createMyIcon("min_avg.png");
        mIconFill = createMyIcon("min_fill.png");
        mIconLogin = createMyIcon("min_login.png");
        mIconLogout = createMyIcon("min_logout.png");
        mIconMax = createMyIcon("min_max.png");
        mIconMin = createMyIcon("min_min.png");
        mIconPrint = createMyIcon("min_print.png");
        mIconZero = createMyIcon("min_zero.png");
        mIconSave = createMyIcon("min_save.png");
        mIconSigma = createMyIcon("min_sigma.png");
    }
    private void createMenus(){
        //create menu bar
        JMenuBar menuBar = new JMenuBar();
        //create main menu poles
        fileMenu = createJMenu("Plik", KeyEvent.VK_P);
        editMenu = createJMenu("Edycja", KeyEvent.VK_E);
        viewMenu = createJMenu("Widok", KeyEvent.VK_W);
        calculationsMenu = createJMenu("Obliczenia", KeyEvent.VK_O);
        helpMenu = createJMenu("Pomoc", KeyEvent.VK_I);
        //create menu item
        exitMenuItem = createJMenuItem("Zamknij",mIconExit,
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        aboutMenuItem = createJMenuItem("O autorze",mIconAbout,
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        helpMenuItem = createJMenuItem("Kontekst pomocy",mIconHelp,
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
    public void createGUI(){
        CenterPanel centerPanel = new CenterPanel();
        BottomStatusPanel bottomStatusPanel = new BottomStatusPanel();

        contentPane.add(createJToolBar(), BorderLayout.NORTH);
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
    private JToolBar createJToolBar(){
        JToolBar jToolBar = new JToolBar();
        jToolBar.setFloatable(false);

        jtbSave = createJButtonToolBar("Zapisanie danych", iconSave);
        jtbPrint = createJButtonToolBar("Drukowanie", iconPrint);
        jtbExit = createJButtonToolBar("Wyjście z aplikacji", iconExit);
        jtbAdd = createJButtonToolBar("Dodaj wartość", iconAdd);
        jtbZero = createJButtonToolBar("Wyzeruj tabele", iconZero);
        jtbFill = createJButtonToolBar("Wypełnij tabele", iconFill);
        jtbSigma = createJButtonToolBar("Sumowanie", iconSigma);
        jtbAvg = createJButtonToolBar("Średnia", iconAvg);
        jtbMin = createJButtonToolBar("Minimum", iconMin);
        jtbMax = createJButtonToolBar("Maksimum", iconMax);
        jtbHelp = createJButtonToolBar("Pomoc", iconHelp);
        jtbAbout = createJButtonToolBar("O autorze", iconAbout);

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
