package app.view;

import javax.swing.*;
import java.awt.*;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>BottomStatusPanel</code> definiujaca dolny panel aplikacji
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class BottomStatusPanel extends JPanel {

    private JLabel infoLabel, statusLabel;
    private JTextField infoTextField, statusTextField;

    /**
     * Konstruktor bezparametrowy
     */
    BottomStatusPanel(){
        createGUI();
    }

    /**
     * Metopda tworzaca gui panelu dolnego
     */
    public void createGUI(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        infoLabel = new JLabel("Info");
        statusLabel = new JLabel("Status");

        infoTextField = new JTextField("Start aplikacji");
        infoTextField.setMinimumSize(new Dimension(200,20));
        infoTextField.setBackground(new Color(227, 227, 227));
        infoTextField.putClientProperty("JComponent.sizeVariant","small");
        infoTextField.setEditable(false);
        statusTextField = new JTextField("ON");
        statusTextField.setMinimumSize(new Dimension(180, 20));
        statusTextField.setBackground(new Color(227, 227, 227));
        statusTextField.putClientProperty("JComponent.sizeVariant", "small");
        statusTextField.setEditable(false);

        this.add(infoLabel);
        this.add(Box.createHorizontalStrut(10));
        this.add(infoTextField);
        this.add(Box.createHorizontalStrut(20));
        this.add(statusLabel);
        this.add(Box.createHorizontalStrut(10));
        this.add(statusTextField);
    }

    /**
     * Metoda ustawia marginesy wewnętrzne
     * @return
     */
    public Insets getInsets() {
        return new Insets(5,10,5,10);
    }

    /**
     * Metoda zwraca infotextfield
     * @return wrcaca obiekt JtextField
     */
    public JTextField getInfoTextField() {
        return infoTextField;
    }
    /**
     * Metoda zwraca statusTextField
     * @return wrcaca obiekt JtextField
     */
    public JTextField getStatusTextField() {
        return statusTextField;
    }
}
