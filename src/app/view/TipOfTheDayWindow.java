package app.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public TipOfTheDayWindow(MyWindow myWindow)
    {
        super("Tip of the Day");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/resources/author_logo.png")));
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
    private void showPreviousTip() {
        if (randomIndex > 0) {
            randomIndex--;
            textArea.setText(tips[randomIndex]);
        }
    }

    private void showNextTip() {
        if (randomIndex < tips.length - 1) {
            randomIndex++;
            textArea.setText(tips[randomIndex]);
        }
    }
    public Insets getInsets() {
        return new Insets(50,20,20,20);
    }
}
