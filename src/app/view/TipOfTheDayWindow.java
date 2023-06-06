package app.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>TipOfTheDay</code> definiujaca okno tip of the day
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class TipOfTheDayWindow extends JFrame {
    private JLabel labelIcon, tipLabel;
    private JScrollPane tipAreaScrollPane;
    private JButton closeButton, prevButton, nextButton;
    private JTextArea textArea;
    private JPanel buttonsPanel;
    private int randomIndex;
    private String randomTip;
    MyWindow _myWindow  = null;
    private String[] tips = {
            "Tip 1: Lorem ipsum dolor sit amet.",
            "Tip 2: Consectetur adipiscing elit.",
            "Tip 3: Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Tip 4: Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
            "Tip 5: Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    };

    /**
     * Kontruktor z parametrem
     * @param myWindow
     */
    public TipOfTheDayWindow(MyWindow myWindow)
    {
        super("Tip of the Day");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/resources/author_logo.png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                _myWindow.getCenterPanel().setVisible(true);
            }
        });
        // Losowanie indeksu dla wybrania losowego tipu
        randomIndex = (int) (Math.random() * tips.length);
        randomTip = tips[randomIndex];

        // Tworzenie komponentów
        _myWindow = myWindow;
        try {
            labelIcon = new JLabel(new ImageIcon(
                    getClass().getResource("/resources/idea.png")));
        }
        catch(Exception e) {
            labelIcon = new JLabel();
        }
        buttonsPanel = createTipButtons();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBorder(new EmptyBorder(5,5,5,5));
        tipAreaScrollPane = new JScrollPane(textArea);
        tipAreaScrollPane.setPreferredSize(new Dimension(120,100));
        labelIcon.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));

        // Ustawianie layoutu
        setLayout(new BorderLayout());

        // Dodawanie komponentów do okna
        this.add(labelIcon, BorderLayout.WEST);
        this.add(textArea, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Ustawianie preferowanego rozmiaru okna
        setSize(330, 220);
        this.setResizable(false);

        // Ustawianie okna "Tip of the Day" na środku ekranu
        this.setLocationRelativeTo(null);

        textArea.append(randomTip);
        textArea.setFont(new Font("Helvetica",Font.BOLD,15));

        // Wyświetlanie okna
        setVisible(true);
    }

    /**
     * Metoda tworząca przyciski
     * @return Zwraca Jpanel
     */
    public JPanel createTipButtons() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        prevButton = new JButton("< prev");
        nextButton = new JButton("next >");
        closeButton = new JButton("Close");

        prevButton.addActionListener(e -> showPreviousTip());
        nextButton.addActionListener(e -> showNextTip());
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                _myWindow.getCenterPanel().setVisible(true);
            }
        });

        jPanel.add(prevButton);
        jPanel.add(nextButton);
        jPanel.add(closeButton);
        return jPanel;
    }

    /**
     * Metoda obsługująca zmiane porady do tyłu
     */
    private void showPreviousTip() {
        if (randomIndex > 0) {
            randomIndex--;
            textArea.setText(tips[randomIndex]);
        }
    }
    /**
     * Metoda obsługująca zmiane porady do przodu
     */
    private void showNextTip() {
        if (randomIndex < tips.length - 1) {
            randomIndex++;
            textArea.setText(tips[randomIndex]);
        }
    }
    /**
     * Metoda tworżaca marginesy wewnętrzne
     */
    public Insets getInsets() {
        return new Insets(50,20,20,20);
    }
}
