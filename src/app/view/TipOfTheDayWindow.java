package app.view;

import javax.swing.*;
import java.awt.*;

public class TipOfTheDayWindow extends JFrame {
    private JLabel labelIcon;
    private String[] tips = {
            "Tip 1: Lorem ipsum dolor sit amet.",
            "Tip 2: Consectetur adipiscing elit.",
            "Tip 3: Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Tip 4: Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
            "Tip 5: Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    };
    public TipOfTheDayWindow()
    {

        super("Tip of the Day");
        // Losowanie indeksu dla wybrania losowego tipu
        int randomIndex = (int) (Math.random() * tips.length);
        String randomTip = tips[randomIndex];

        // Tworzenie komponentów
        try {
            labelIcon = new JLabel(new ImageIcon(
                    getClass().getResource("/resources/help.png")));
        }
        catch(Exception e) {
            labelIcon = new JLabel();
        }
        JLabel tipLabel = new JLabel(randomTip);
        JButton closeButton = new JButton("Close");

        // Ustawianie layoutu
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        // Dodawanie komponentów do okna
        add(tipLabel);
        add(Box.createVerticalStrut(10)); // Dodatkowy odstęp
        add(closeButton);

        // Ustawianie preferowanego rozmiaru okna
        this.setMaximumSize(new Dimension(330, 220));
        this.setResizable(false);
        // Ustawianie położenia okna na środku ekranu
        setLocationRelativeTo(null);

        // Ustawianie zdarzenia dla przycisku zamykania
        closeButton.addActionListener(e -> dispose());
        closeButton.setMaximumSize(new Dimension(80, 30));

        // Wyświetlanie okna
        setVisible(true);
        pack();
    }
    public Insets getInsets() {
        return new Insets(50,50,30,50);
    }
}
