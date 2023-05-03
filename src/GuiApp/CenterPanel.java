package GuiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenterPanel extends JPanel implements ActionListener {

    CenterPanel(){
        createGUI();
    }
    public void createGUI() {
        this.setLayout(new GridLayout(2, 1, 5, 5));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
