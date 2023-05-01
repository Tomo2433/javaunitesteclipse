package GuiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow extends JFrame implements ActionListener {

    private final int WINDOW_WIDTH_SIZE = 650;
    private final int WINDOW_HEIGHT_SIZE = 450;

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

                }
            });
        }
        catch (Exception e){
            System.out.println("ERROR - error while creating gui of application");
        }
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
    private JButton createJButtonToolBar(String tooltip, Icon icon){
        JButton jButton = new JButton("", icon);
        jButton.setToolTipText(tooltip);
        jButton.addActionListener(this);
        return  jButton;
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
