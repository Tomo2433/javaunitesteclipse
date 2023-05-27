package app.controller;

import app.listeners.*;
import app.view.AboutWindow;
import app.view.HelpWindow;
import app.view.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyWindowController implements ActionListener {
    private MyWindow _myWindow;
    public MyWindowController(MyWindow myWindow){
        _myWindow = myWindow;
        _myWindow.getJtbAbout().addActionListener(this);
        _myWindow.getAboutMenuItem().addActionListener(this);
        _myWindow.getViewStatusBarMenuItem().addActionListener(this);
        _myWindow.getViewJToolBarMenuItem().addActionListener(this);
        _myWindow.getJtbHelp().addActionListener(this);
        _myWindow.getHelpMenuItem().addActionListener(this);
        _myWindow.getJtbExit().addActionListener(this);
        _myWindow.getExitMenuItem().addActionListener(this);
        _myWindow.getJtbPrint().addActionListener(this);
        _myWindow.getPrintMenuItem().addActionListener(this);
        _myWindow.getJtbSave().addActionListener(new SaveListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getSaveMenuItem().addActionListener(new SaveListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getSigmaMenuItem().addActionListener(new SumListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getJtbSigma().addActionListener(new SumListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getAvgMenuItem().addActionListener(new AvgListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getJtbAvg().addActionListener(new AvgListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getMinMenuItem().addActionListener(new MinListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getJtbMin().addActionListener(new MinListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getJtbMax().addActionListener(new MaxListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getMaxMenuItem().addActionListener(new MaxListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols()));
        _myWindow.getJtbFill().addActionListener(new FillTableListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols(),
                _myWindow.getCenterPanel().getResultTextArea(),
                _myWindow.getCenterPanel().getNumberTextField()));
        _myWindow.getJtbZero().addActionListener(new ZeroTableValuesListener(
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getTableRows(),
                _myWindow.getCenterPanel().getTableCols(),
                _myWindow.getCenterPanel().getResultTextArea()));
        _myWindow.getJtbAdd().addActionListener(new AddValueListener(
                _myWindow.getCenterPanel().getNumberTextField(),
                _myWindow.getCenterPanel().getRowSlider(),
                _myWindow.getCenterPanel().getColumnSlider(),
                _myWindow.getCenterPanel().getTable(),
                _myWindow.getCenterPanel().getResultTextArea()));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _myWindow.getJtbAbout() ||
                e.getSource() == _myWindow.getAboutMenuItem()) {
            if(_myWindow.getAboutWindow() != null) _myWindow.getAboutWindow().setVisible(true);
            else {
                _myWindow.setAboutWindow(new AboutWindow());
                _myWindow.getAboutWindow().setVisible(true);
            }
        } else if (e.getSource() == _myWindow.getViewStatusBarMenuItem()) {
            boolean visible = _myWindow.getViewStatusBarMenuItem().getState();
            _myWindow.getBottomStatusPanel().setVisible(!visible);
        } else if (e.getSource() == _myWindow.getViewJToolBarMenuItem()) {
            boolean visible = _myWindow.getViewJToolBarMenuItem().getState();
            _myWindow.getjToolBar().setVisible(!visible);
        } else if (e.getSource() == _myWindow.getJtbHelp() ||
                e.getSource() == _myWindow.getHelpMenuItem()) {
            if(_myWindow.getHelpWindow() != null) _myWindow.getHelpWindow().setVisible(true);
            else {
                _myWindow.setHelpWindow(new HelpWindow());
                _myWindow.getHelpWindow().setVisible(true);
            }
        } else if((e.getSource() == _myWindow.getExitMenuItem()) ||
                (e.getSource() == _myWindow.getJtbExit())) {
            _myWindow.getBottomStatusPanel().getInfoTextField().setText("Zamykanie aplikacji");
            _myWindow.windowClose();
        } else if ((e.getSource() == _myWindow.getPrintMenuItem()) ||
                (e.getSource() == _myWindow.getJtbPrint())) {
            _myWindow.printListForm();
        }
    }
}
