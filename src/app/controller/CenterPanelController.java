package app.controller;

import app.listeners.*;
import app.view.CenterPanel;
import com.l2fprod.common.swing.JTaskPaneGroup;

import javax.swing.*;
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
                _centerPanel.getTableModel(),
                _centerPanel.getResultTextArea()));
        _centerPanel.getJbtZero().addActionListener(new ZeroTableValuesListener(
                _centerPanel.getTableModel(),
                _centerPanel.getTableModel().getRowCount(),
                _centerPanel.getTableModel().getColumnCount(),
                _centerPanel.getResultTextArea()));
        _centerPanel.getJbtFill().addActionListener(new FillTableListener(
                _centerPanel.getTableModel(),
                _centerPanel.getTableModel().getRowCount(),
                _centerPanel.getTableModel().getColumnCount(),
                _centerPanel.getResultTextArea(),
                _centerPanel.getNumberTextField()));
        _centerPanel.getJbtSave().addActionListener(new SaveListener(
                _centerPanel.getTableModel(),
                _centerPanel.getResultTextArea(),
                _centerPanel.getTableModel().getRowCount(),
                _centerPanel.getTableModel().getColumnCount()));
        _centerPanel.getJbtCount().addActionListener(e1->_centerPanel.getResultTextArea().append("Wybierz operacje! \n"));
        _centerPanel.getjComboBox().addActionListener(this);
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Add New Product");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Edit Product");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Delete Product");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Find Product");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Product List");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Expire Date");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Breakage");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Low Stock");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Transfer");
        addNestedGroupAction(_centerPanel.getTaskPaneGroup(),"Price List");
    }
    void addNestedGroupAction(final JTaskPaneGroup parent, String menu) {
        Action addNestedGroup = new AbstractAction(menu) {
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Add New Product")) {
                    
                }
                if (s.equals("Edit Product")) {
                    // Your code here
                }
                if (s.equals("Delete Product")) {
                    // Your code here
                }
                if (s.equals("Find Product")) {
                    // Your code here
                }
                if (s.equals("Product List")) {
                    // Your code here
                }
                if (s.equals("Expire Date")) {
                    // Your code here
                }
            }
        };
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for(ActionListener l : _centerPanel.getJbtCount().getActionListeners())
            _centerPanel.getJbtCount().removeActionListener(l);
        selectedOption = (String) _centerPanel.getjComboBox().getSelectedItem();
        if (selectedOption.equals("Sumowanie")){
            _centerPanel.getJbtCount().addActionListener(new SumListener(
                    _centerPanel.getTableModel(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableModel().getRowCount(),
                    _centerPanel.getTableModel().getColumnCount()));
        } else if (selectedOption.equals("Średnia")) {
            _centerPanel.getJbtCount().addActionListener(new AvgListener(
                    _centerPanel.getTableModel(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableModel().getRowCount(),
                    _centerPanel.getTableModel().getColumnCount()));
        } else if (selectedOption.equals("Min i Max")) {
            _centerPanel.getJbtCount().addActionListener(new MinAndMaxListener(
                    _centerPanel.getTableModel(),
                    _centerPanel.getResultTextArea(),
                    _centerPanel.getTableModel().getRowCount(),
                    _centerPanel.getTableModel().getColumnCount()));
        } else {
            _centerPanel.getJbtCount().addActionListener(e1->_centerPanel.getResultTextArea().append("Wybierz operacje! \n"));
        }
    }
}
