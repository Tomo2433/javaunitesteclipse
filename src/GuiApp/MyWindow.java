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

    //icons definition
    private Icon iconExit, mIconExit, iconAbout, mIconAbout, iconHelp, mIconHelp;

    //menu variables definition
    private JMenu fileMenu, editMenu, viewMenu, calculationsMenu, helpMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem;

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

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout());

        try{
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    createIcons();
                    createMenus();
                    contentPane.add(createCenterPanel(), BorderLayout.CENTER);
                }
            });
        }
        catch (Exception e){
            System.out.println("ERROR - error while creating gui of application");
        }
    }
    private void createIcons(){
        //create 24x24px icons for toolbar
        iconExit = createMyIcon("close.jpg");
        iconAbout = createMyIcon("about.jpg");
        iconHelp = createMyIcon("help_context.jpg");
        //create 16x16px icons for menubar
        mIconExit = createMyIcon("min_close.jpg");
        mIconAbout = createMyIcon("min_about.jpg");
        mIconHelp = createMyIcon("min_help_context.jpg");
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

        return jToolBar;
    }
    private JButton createJButtonToolBar(String tooltip, Icon icon) {
        JButton jButton = new JButton("", icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        return jButton;
    }
    private JPanel createCenterPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jPanel.setBackground(Color.LIGHT_GRAY);

        return jPanel;
    }

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
