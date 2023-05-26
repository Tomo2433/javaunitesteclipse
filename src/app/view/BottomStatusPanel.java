package app.view;

import javax.swing.*;
import java.awt.*;

public class BottomStatusPanel extends JPanel {

    private JLabel infoLabel, statusLabel;
    private JTextField infoTextField, statusTextField;
    BottomStatusPanel(){
        createGUI();
    }
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

    public Insets getInsets() {
        return new Insets(5,10,5,10);
    }

    public JTextField getInfoTextField() {
        return infoTextField;
    }

    public JTextField getStatusTextField() {
        return statusTextField;
    }
}
