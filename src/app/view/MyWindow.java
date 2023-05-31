package app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.net.URL;

public class MyWindow extends JFrame{

    private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH_SIZE = 670;
    private final int WINDOW_HEIGHT_SIZE = 450;
    private static final String ICON_PATH = "/resources/";
    private JPanel contentPane;
    private CenterPanel centerPanel;
    private BottomStatusPanel bottomStatusPanel;
    AboutWindow aboutWindow = null;
    HelpWindow helpWindow = null;


    //menu variables definition
    private JMenu fileMenu, editMenu, viewMenu, calculationsMenu, helpMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem, printMenuItem,
            sigmaMenuItem, avgMenuItem, minMenuItem, maxMenuItem, saveMenuItem;
    private JCheckBoxMenuItem viewStatusBarMenuItem, viewJToolBarMenuItem;

    //toolbar variables definition
    JToolBar jToolBar;
    JButton jtbSave, jtbPrint, jtbExit, jtbAdd, jtbZero,
            jtbFill, jtbSigma, jtbAvg, jtbMin,
            jtbMax, jtbHelp, jtbAbout;

    public MyWindow(){
        setTitle("MyWindow v1.0.1");
        setSize(WINDOW_WIDTH_SIZE,WINDOW_HEIGHT_SIZE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/author_logo.png")));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bottomStatusPanel.getInfoTextField().setText("Zamykanie aplikacji");
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
        saveMenuItem = createJMenuItem("Zapisz",icons.mIconSave,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        exitMenuItem = createJMenuItem("Zamknij",icons.mIconExit,
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        aboutMenuItem = createJMenuItem("O autorze",icons.mIconAbout,
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        helpMenuItem = createJMenuItem("Kontekst pomocy",icons.mIconHelp,
                KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        printMenuItem = createJMenuItem("Drukuj",icons.mIconPrint,
                KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        sigmaMenuItem = createJMenuItem("Sumowanie", icons.mIconSigma,
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        avgMenuItem = createJMenuItem("Średnia", icons.mIconAvg,
                KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        minMenuItem = createJMenuItem("Minimum", icons.mIconMin,
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        maxMenuItem = createJMenuItem("Maksimum", icons.mIconMax,
                KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));

        viewStatusBarMenuItem = createJCheckBoxMenuItem(
                "Ukryj pasek statusu",false);
        viewJToolBarMenuItem = createJCheckBoxMenuItem(
                "Ukryj pasek narzędziowy",false);


        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);
        viewMenu.add(viewStatusBarMenuItem);
        viewMenu.add(viewJToolBarMenuItem);
        calculationsMenu.add(sigmaMenuItem);
        calculationsMenu.add(avgMenuItem);
        calculationsMenu.add(minMenuItem);
        calculationsMenu.add(maxMenuItem);
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
        jToolBar = createJToolBar(icons);

        contentPane.add(jToolBar, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomStatusPanel, BorderLayout.SOUTH);
    }
    public void printListForm() {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = new PageFormat();
            job.pageDialog(pf);
            if(job.printDialog()) {
                job.print();	// Jesli uzytkownik wybral ok drukujemy panel
                bottomStatusPanel.getInfoTextField().setText("Wydrukowanie listy");
            }
        } catch(Exception ex) {
            bottomStatusPanel.getStatusTextField().setText("Błąd drukowania");
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Nastąpił błąd drukowania!" );
        }
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
        //jMenuItem.addActionListener(this);
        return jMenuItem;
    }
    public JCheckBoxMenuItem createJCheckBoxMenuItem(String name, boolean enable){
        JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(name, enable);
       // jCheckBoxMenuItem.addActionListener(this);
        jCheckBoxMenuItem.setEnabled(true);
        return jCheckBoxMenuItem;
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

        return jToolBar;
    }
    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jButton = new JButton("", icon);
        jButton.setToolTipText(tooltip);
        //jButton.addActionListener(this);
        return jButton;
    }
    public void windowClose(){
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
        } else {
            bottomStatusPanel.getInfoTextField().setText("Aplikacja kontynuuje");
        }
    }
    
    public JButton getJtbSave() {
        return jtbSave;
    }
    public JButton getJtbAbout() {
        return jtbAbout;
    }
    public JButton getJtbPrint() {
        return jtbPrint;
    }

    public JButton getJtbAdd() {
        return jtbAdd;
    }

    public JButton getJtbAvg() {
        return jtbAvg;
    }

    public JButton getJtbExit() {
        return jtbExit;
    }

    public JButton getJtbFill() {
        return jtbFill;
    }

    public JButton getJtbHelp() {
        return jtbHelp;
    }

    public JButton getJtbMax() {
        return jtbMax;
    }

    public JButton getJtbMin() {
        return jtbMin;
    }

    public JButton getJtbSigma() {
        return jtbSigma;
    }

    public JButton getJtbZero() {
        return jtbZero;
    }

    public JCheckBoxMenuItem getViewJToolBarMenuItem() {
        return viewJToolBarMenuItem;
    }

    public JCheckBoxMenuItem getViewStatusBarMenuItem() {
        return viewStatusBarMenuItem;
    }

    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public JMenuItem getAvgMenuItem() {
        return avgMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }

    public JMenu getEditMenu() {
        return editMenu;
    }

    public JMenuItem getMaxMenuItem() {
        return maxMenuItem;
    }

    public JMenuItem getMinMenuItem() {
        return minMenuItem;
    }

    public JMenuItem getPrintMenuItem() {
        return printMenuItem;
    }

    public JMenuItem getSigmaMenuItem() {
        return sigmaMenuItem;
    }
    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public AboutWindow getAboutWindow() {
        return aboutWindow;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public BottomStatusPanel getBottomStatusPanel() {
        return bottomStatusPanel;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public JToolBar getjToolBar() {
        return jToolBar;
    }

    public void setAboutWindow(AboutWindow aboutWindow) {
        this.aboutWindow = aboutWindow;
    }

    public void setHelpWindow(HelpWindow helpWindow) {
        this.helpWindow = helpWindow;
    }

}
