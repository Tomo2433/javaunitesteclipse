package app.controller;

import app.listeners.*;
import app.view.CenterPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenterPanelController implements ActionListener {
    private CenterPanel _centerPanel;
    private String selectedOption;

    public CenterPanelController(CenterPanel centerPanel) {
        _centerPanel = centerPanel;
        _centerPanel.getJbtAdd().addActionListener(new AddValueListener(
                _centerPanel.getNumberTextField(),
                _centerPanel.getRowSlider(),
                _centerPanel.getColumnSlider(),
                _centerPanel.getTable(),
                _centerPanel.getResultTextArea()));
        _centerPanel.getJbtZero().addActionListener(new ZeroTableValuesListener(
                _centerPanel.getTable(),
                _centerPanel.getTableRows(),
                _centerPanel.getTableCols(),
                _centerPanel.getResultTextArea()));
        _centerPanel.getJbtFill().addActionListener(new FillTableListener(
                _centerPanel.getTable(),
                _centerPanel.getTableRows(),
                _centerPanel.getTableCols(),
                _centerPanel.getResultTextArea(),
                _centerPanel.getNumberTextField()));
        _centerPanel.getJbtSave().addActionListener(new SaveListener(
                _centerPanel.getTable(),
                _centerPanel.getResultTextArea(),
                _centerPanel.getTableRows(),
                _centerPanel.getTableCols()));
        _centerPanel.getJbtCount().addActionListener(e1->_centerPanel.getResultTextArea().append("Wybierz operacje! \n"));
        _centerPanel.getjComboBox().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(ActionListener l : _centerPanel.getJbtCount().getActionListeners())
            _centerPanel.getJbtCount().removeActionListener(l);
        selectedOption = (String) _centerPanel.getjComboBox().getSelectedItem();
        if (selectedOption.equals("Sumowanie")){
            _centerPanel.getJbtCount().addActionListener(new SumListener(
                    _centerPanel.getTable(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableRows(),
                    _centerPanel.getTableCols()));
        } else if (selectedOption.equals("Średnia")) {
            _centerPanel.getJbtCount().addActionListener(new AvgListener(
                    _centerPanel.getTable(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableRows(),
                    _centerPanel.getTableCols()));
        } else if (selectedOption.equals("Min i Max")) {
            _centerPanel.getJbtCount().addActionListener(new MinAndMaxListener(
                    _centerPanel.getTable(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableRows(),
                    _centerPanel.getTableCols()));
        } else {
            _centerPanel.getJbtCount().addActionListener(e1->_centerPanel.getResultTextArea().append("Wybierz operacje! \n"));
        }
    }
}
