package app.view;

import javax.swing.*;
import java.net.URL;

public class Icons {
    //icons definition
    public Icon iconExit, iconAbout, iconHelp, iconAdd,
            iconAvg, iconFill, iconLogin, iconLogout,
            iconMax, iconMin, iconPrint, iconZero, iconSave,
            iconSigma, iconChart;
    public Icon mIconExit, mIconAbout, mIconHelp, mIconAdd,
            mIconAvg, mIconFill, mIconLogin, mIconLogout,
            mIconMax, mIconMin, mIconPrint, mIconZero, mIconSave,
            mIconSigma, mIconChart;
    private static final String ICON_PATH = "/resources/";
    Icons(){
        createIcons();
    }
    public Icon createMyIcon(String nameFile){
        String name = ICON_PATH + nameFile;
        Icon icon = null;
        URL url = getClass().getResource(name);
        if(url != null) icon = new ImageIcon(url);
        return icon;
    }
    private void createIcons(){
        //create 24x24px icons for toolbar
        iconExit = createMyIcon("exit.png");
        iconAbout = createMyIcon("about.png");
        iconHelp = createMyIcon("help.png");
        iconAdd = createMyIcon("add.png");
        iconAvg = createMyIcon("avg.png");
        iconFill = createMyIcon("fill.png");
        iconLogin = createMyIcon("login.png");
        iconLogout = createMyIcon("logout.png");
        iconMax = createMyIcon("max.png");
        iconMin = createMyIcon("min.png");
        iconPrint = createMyIcon("print.png");
        iconZero = createMyIcon("zero.png");
        iconSave = createMyIcon("save.png");
        iconSigma = createMyIcon("sigma.png");
        iconChart = createMyIcon("bar_chart.png");
                //create 16x16px icons for menubar
        mIconExit = createMyIcon("min_exit.png");
        mIconAbout = createMyIcon("min_about.png");
        mIconHelp = createMyIcon("min_help.png");
        mIconAdd = createMyIcon("min_add.png");
        mIconAvg = createMyIcon("min_avg.png");
        mIconFill = createMyIcon("min_fill.png");
        mIconLogin = createMyIcon("min_login.png");
        mIconLogout = createMyIcon("min_logout.png");
        mIconMax = createMyIcon("min_max.png");
        mIconMin = createMyIcon("min_min.png");
        mIconPrint = createMyIcon("min_print.png");
        mIconZero = createMyIcon("min_zero.png");
        mIconSave = createMyIcon("min_save.png");
        mIconSigma = createMyIcon("min_sigma.png");
        mIconChart = createMyIcon("min_bar_chart.png");
    }
}
