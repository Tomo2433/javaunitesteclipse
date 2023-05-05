package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutWindow extends JDialog implements ActionListener {

    private JLabel lLogo, lBorder;
    private JLabel lTitle, lVersion, lRights, lAuthor, lDepartment, lMail;
    private Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
    private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
    private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
    private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
    private JButton jbtOk;
    private Border line = null;


    public AboutWindow(){
        this.setTitle("Informacje o programie");
        this.setModal(true);
        this.setSize(370,255);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(null);

        try {
            lLogo = new JLabel(new ImageIcon(
                    getClass().getResource("/resources/author_logo.png")));
        } catch (Exception e) {
            lLogo = new JLabel();
        }
        lTitle = new JLabel("My Window");
        lTitle.setFont(font1);
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lVersion = new JLabel("wersja 1.0.1");
        lVersion.setFont(font2);
        lVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lRights = new JLabel("Copyright (C) by 2023");
        lRights.setFont(font2);
        lRights.setHorizontalAlignment(SwingConstants.CENTER);
        lAuthor = new JLabel("Tomasz Lech");
        lAuthor.setFont(font3);
        lAuthor.setHorizontalAlignment(SwingConstants.CENTER);
        lDepartment = new JLabel("Politechnika Koszalinska - WEiI");
        lDepartment.setFont(font3);
        lDepartment.setHorizontalAlignment(SwingConstants.CENTER);
        lMail = new JLabel("e-mail: email@gmail.pl");
        lMail.setFont(font4);
        lBorder = new JLabel("");
        jbtOk = new JButton("OK");
        jbtOk.addActionListener(this);
        line = new EtchedBorder(EtchedBorder.LOWERED);

        lLogo.setBounds(10,15,120,150);
        lTitle.setBounds(135,20,210,30);
        lVersion.setBounds(135,50,210,30);
        lRights.setBounds(135,100,210,20);
        lAuthor.setBounds(135,120,210,20);
        lDepartment.setBounds(135,140,210,20);
        lBorder.setBounds(5,175,getSize().width-14,2);
        lMail.setBounds(10,184,200,20);
        jbtOk.setBounds(getSize().width-90,182,60,25);

        lBorder.setBorder(line);
        contentPane.add(lTitle);
        contentPane.add(lVersion);
        contentPane.add(lRights);
        contentPane.add(lAuthor);
        contentPane.add(lDepartment);
        contentPane.add(lMail);
        contentPane.add(lBorder);
        contentPane.add(jbtOk);
        contentPane.add(lLogo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbtOk){
            setVisible(false);
        }
    }
}
