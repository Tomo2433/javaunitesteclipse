import app.controller.CenterPanelController;
import app.controller.MyWindowController;
import app.view.MyWindow;
import app.view.TipOfTheDayWindow;

public class Main {
    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
        MyWindowController myWindowController = new MyWindowController(myWindow);
        CenterPanelController centerPanelController = new CenterPanelController(myWindow.getCenterPanel(), myWindow);
        myWindow.setVisible(true);
    }
}