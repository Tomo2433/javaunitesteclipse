package app;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public class HelpWindow extends JDialog {
    private JEditorPane jEditorPane = null;
    private URL urlHelpPage = null;

    public HelpWindow(){
        this.setTitle("Pomoc - My Window");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/author_logo.png")));
        this.setModal(false);
        this.setResizable(true);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        this.setLayout(new BorderLayout());

        jEditorPane = new JEditorPane();
        jEditorPane.setEditable(false);

        urlHelpPage = app.HelpWindow.class.getResource("/help/index.html");
        setUrlPage();
        jEditorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        jEditorPane.setPage(e.getURL());
                    } catch (IOException ex) {
                        jEditorPane.setText("Exception: " + ex);
                    }
                }
            }
        });
        this.add(new JScrollPane(jEditorPane), BorderLayout.CENTER);
    }

    private void setUrlPage(){
        try{
            jEditorPane.setPage(urlHelpPage);
        } catch (Exception e) {
            jEditorPane.setText("Nie można otworzyć pliku z pomocą " + urlHelpPage);
        }
    }
}
