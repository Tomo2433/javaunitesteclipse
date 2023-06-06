package app.view;

import app.Logger.MyLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>MyWindow</code> definiujaca glowne okno aplikacji
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class MyWindow extends JFrame implements Runnable{

    private static final long serialVersionUID = 1L;
    private final int WINDOW_WIDTH_SIZE = 680;
    private final int WINDOW_HEIGHT_SIZE = 490;
    private static final String ICON_PATH = "/resources/";
    private JPanel contentPane;
    private CenterPanel centerPanel;
    private BottomStatusPanel bottomStatusPanel;
    AboutWindow aboutWindow = null;
    HelpWindow helpWindow = null;
    HistogramWindow histogramWindow = null;
    TipOfTheDayWindow tipOfTheday = null;


    /**
     * Zmienne tworzace menu
     */
    private JMenu fileMenu, editMenu, viewMenu, calculationsMenu, helpMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem, printMenuItem,
            sigmaMenuItem, avgMenuItem, minMenuItem, maxMenuItem, saveMenuItem,
            histogramMenuItem;
    private JCheckBoxMenuItem viewStatusBarMenuItem, viewJToolBarMenuItem;

    /**
     * Zmienne tworzące pasek narzedziowy
     */
    JToolBar jToolBar;
    JButton jtbSave, jtbPrint, jtbExit, jtbAdd, jtbZero,
            jtbFill, jtbSigma, jtbAvg, jtbMin,
            jtbMax, jtbHelp, jtbAbout;

    /**
     * Konstruktor bezparametrowy klasy <code>MyWindow</code>
     */
    public MyWindow(){
        setTitle("MyWindow v1.0.1");
        setSize(WINDOW_WIDTH_SIZE,WINDOW_HEIGHT_SIZE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/author_logo.png")));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // definicja zdarzenia zamkniecia okna
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bottomStatusPanel.getInfoTextField().setText("Zamykanie aplikacji");
                windowClose();
            }
        });

        // Utworzenie glownego kontekstu (ContentPane)
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout());
        // utworzenie GUI w watku zdarzeniowym
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
            MyLogger.writeLog("ERROR","error while creating gui of application");
        }
        // utworzenie watku uruchamiajacgo okno tip of the day
        Thread thread = new Thread(this);
        thread.start();
    }
    /**
     * Metoda wątku uruchamiająca z opónieniem 1 sekundy (1000 ms)
     * okno TipOfTheDay zaraz po uruchomieniu aplikacji
     */
    public void run() {
        try {
            Thread.sleep(1000); // opoznienie 1 sekunda
        }
        catch(InterruptedException e) {}
        // uruchomienie okna TipOfTheDay
        openTipOfTheday();
    }
    public void openTipOfTheday() {
//        DefaultTipModel spis_porad = new DefaultTipModel();
//        spis_porad.add(new DefaultTip("tip1","Treść naszej pierwszej porady."));
//        spis_porad.add(new DefaultTip("tip2","Treść naszej drugiej porady."));
//        JTipOfTheDay porady = new JTipOfTheDay(spis_porad);
//        porady.showDialog(this);
        tipOfTheday = new TipOfTheDayWindow(this);
        tipOfTheday.setVisible(true);
    }
    /**
     * Metoda tworząca menu okna głównego
     */
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
        histogramMenuItem = createJMenuItem("Histogram", icons.mIconChart,
                KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));

        viewStatusBarMenuItem = createJCheckBoxMenuItem(
                "Ukryj pasek statusu",false);
        viewJToolBarMenuItem = createJCheckBoxMenuItem(
                "Ukryj pasek narzędziowy",false);

        // dodanie utworzonych elementow menu do paska menu JMenuBar
        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(histogramMenuItem);
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
    /**
     * Metoda tworząca GUI
     * @param icons
     */
    public void createGUI(Icons icons){
        //utworzenie Jpaneli dla centrum okna i spodu
        centerPanel = new CenterPanel();
        centerPanel.setVisible(false);
        bottomStatusPanel = new BottomStatusPanel();
        //utworzenie jtoolbara
        jToolBar = createJToolBar(icons);

        //dodanie elementów do contentPane
        contentPane.add(jToolBar, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomStatusPanel, BorderLayout.SOUTH);
    }

    /**
     * Metoda do dialogu drukowania
     */
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
            MyLogger.writeLog("ERROR","Blad drukowania");
        }
    }
    /**
     * Metoda tworz�ca obiekt typu JMenu
     * @param name zmienna okre�laj�ca nazw�
     * @param keyEvent zmienna okre�laj�ca klawisz skr�tu
     * @return zwraca utworzony obiekt typu JMenu
     */
    public JMenu createJMenu(String name, int keyEvent){
        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(keyEvent);
        return jMenu;
    }
    /**
     * Metoda tworz�ca obiekt typu JMenuItem
     * @param name zmienna okre�lajaca nazw�
     * @param icon zmienna okre�laj�ca icon� wy�wietlan� wraz z nazw�
     * @param key zmienna okre�laj�ca klawisze akceleracji dost�pu
     * @return zwraca utworzony obiekt typu JMenuItem
     */
    public JMenuItem createJMenuItem(String name, Icon icon, KeyStroke key){
        JMenuItem jMenuItem;
        if(icon != null)
            jMenuItem = new JMenuItem(name, icon);
        else jMenuItem = new JMenuItem(name);
        jMenuItem.setAccelerator(key);
        return jMenuItem;
    }
    /**
     * Metoda tworz�ca obiekt typu JCheckBoxMenuItem
     * @param name zmienna okre�lajaca nazw�
     * @param enable zmienna logiczna okre�laj�ca czy podmenu jest aktywne
     * @return utworzony obiekt typu JCheckBoxMenuItem
     */
    public JCheckBoxMenuItem createJCheckBoxMenuItem(String name, boolean enable){
        JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(name, enable);
        jCheckBoxMenuItem.setEnabled(true);
        return jCheckBoxMenuItem;
    }
    /**
     * Metoda tworz�ca pasek narz�dziowy
     * @param icons zmienna typu Icons
     */
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
    /**
     * Metoda tworz�ca obiekt typu JButton dla paska narz�dziowego
     * @param tooltip zmienna okre�laj�ca podpowiedz dla przycisku
     * @param icon zmienna okre�laj�ca obrazek przypisany do przycisku
     * @return zwraca utworzony obiekt typu JButton
     */
    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jButton = new JButton("", icon);
        jButton.setToolTipText(tooltip);
        //jButton.addActionListener(this);
        return jButton;
    }

    /**
     * Metoda tworząca okno zapytania czy zamknąć aplikację
     */
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
            MyLogger.writeLog("INFO","Zamykanie aplikacji");
            dispose();
            System.exit(0);
        } else {
            bottomStatusPanel.getInfoTextField().setText("Aplikacja kontynuuje");
        }
    }

    /**
     * Metoda zwracająca jtbSave
     * @return zwraca JButton
     */
    public JButton getJtbSave() {
        return jtbSave;
    }
    /**
     * Metoda zwracająca jtbAbout
     * @return zwraca JButton
     */
    public JButton getJtbAbout() {
        return jtbAbout;
    }
    /**
     * Metoda zwracająca jtbPrint
     * @return zwraca JButton
     */
    public JButton getJtbPrint() {
        return jtbPrint;
    }
    /**
     * Metoda zwracająca jtbAdd
     * @return zwraca JButton
     */
    public JButton getJtbAdd() {
        return jtbAdd;
    }
    /**
     * Metoda zwracająca jtbAvg
     * @return zwraca JButton
     */
    public JButton getJtbAvg() {
        return jtbAvg;
    }
    /**
     * Metoda zwracająca jtbExit
     * @return zwraca JButton
     */
    public JButton getJtbExit() {
        return jtbExit;
    }
    /**
     * Metoda zwracająca jtbFill
     * @return zwraca JButton
     */
    public JButton getJtbFill() {
        return jtbFill;
    }
    /**
     * Metoda zwracająca jtbHelp
     * @return zwraca JButton
     */
    public JButton getJtbHelp() {
        return jtbHelp;
    }
    /**
     * Metoda zwracająca jtbMax
     * @return zwraca JButton
     */
    public JButton getJtbMax() {
        return jtbMax;
    }
    /**
     * Metoda zwracająca jtbMin
     * @return zwraca JButton
     */
    public JButton getJtbMin() {
        return jtbMin;
    }
    /**
     * Metoda zwracająca jtbSigma
     * @return zwraca JButton
     */
    public JButton getJtbSigma() {
        return jtbSigma;
    }
    /**
     * Metoda zwracająca jtbZero
     * @return zwraca JButton
     */
    public JButton getJtbZero() {
        return jtbZero;
    }
    /**
     * Metoda zwracająca element checkbox menu
     * @return zwraca JCheckBoxMenuItem
     */
    public JCheckBoxMenuItem getViewJToolBarMenuItem() {
        return viewJToolBarMenuItem;
    }
    /**
     * Metoda zwracająca element Status bar menu
     * @return zwraca JCheckBoxMenuItem
     */
    public JCheckBoxMenuItem getViewStatusBarMenuItem() {
        return viewStatusBarMenuItem;
    }
    /**
     * Metoda zwracająca element AboutMenuItem
     * @return zwraca JMenuItem
     */
    public JMenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }
    /**
     * Metoda zwracająca element avgMenuItem
     * @return zwraca JMenuItem
     */
    public JMenuItem getAvgMenuItem() {
        return avgMenuItem;
    }
    /**
     * Metoda zwracająca element exitMenuItem
     * @return zwraca JMenuItem
     */
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }
    /**
     * Metoda zwracająca element avgMenuItem
     * @return zwraca JMenuItem
     */
    public JMenuItem getHelpMenuItem() {
        return helpMenuItem;
    }
    /**
     * Metoda zwracająca podmenu edit
     * @return zwraca JMenu
     */
    public JMenu getEditMenu() {
        return editMenu;
    }

    /**
     * Metoda zwara maxmenu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getMaxMenuItem() {
        return maxMenuItem;
    }
    /**
     * Metoda zwara minmenu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getMinMenuItem() {
        return minMenuItem;
    }
    /**
     * Metoda zwara Print menu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getPrintMenuItem() {
        return printMenuItem;
    }
    /**
     * Metoda zwara Sigma menu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getSigmaMenuItem() {
        return sigmaMenuItem;
    }
    /**
     * Metoda zwara Save menu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }
    /**
     * Metoda zwara Histogran menu item
     * @return zwraca JMenuItem
     */
    public JMenuItem getHistogramMenuItem() { return  histogramMenuItem; }

    /**
     * Metoda zwraca okno o autorze
     * @return zwraca obiekt AboutWindow
     */
    public AboutWindow getAboutWindow() {
        return aboutWindow;
    }
    /**
     * Metoda zwraca okno  pomocy
     * @return zwraca obiekt Help Window
     */
    public HelpWindow getHelpWindow() {
        return helpWindow;
    }
    /**
     * Metoda zwraca Dolny panel
     * @return zwraca obiekt BottomStatusPanel
     */
    public BottomStatusPanel getBottomStatusPanel() {
        return bottomStatusPanel;
    }
    /**
     * Metoda zwraca Centralny Panel
     * @return zwraca obiekt CenterPanel
     */
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }
    /**
     * Metoda zwraca pasek narżedziowy
     * @return zwraca JToolBar
     */
    public JToolBar getjToolBar() {
        return jToolBar;
    }

    /**
     * Metoda ustawia okno o autorze
     * @param aboutWindow
     */
    public void setAboutWindow(AboutWindow aboutWindow) {
        this.aboutWindow = aboutWindow;
    }

    /**
     * Metopda ustawia okno pomocy
     * @param helpWindow
     */
    public void setHelpWindow(HelpWindow helpWindow) {
        this.helpWindow = helpWindow;
    }

}
