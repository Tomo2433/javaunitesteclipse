import app.Logger.MyLogger;
import app.controller.CenterPanelController;
import app.controller.MyWindowController;
import app.view.MyWindow;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>Main</code> inicjalizująca główne obiekty aplikacji i
 * definiująca nowe okno
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class Main {
    public static void main(String[] args) {
        MyLogger.writeLog("INFO","Start Aplikacji");
        MyWindow myWindow = new MyWindow();
        MyWindowController myWindowController = new MyWindowController(myWindow);
        CenterPanelController centerPanelController = new CenterPanelController(myWindow.getCenterPanel(), myWindow);
        myWindow.setVisible(true);
    }
}