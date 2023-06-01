package app.controller;

import app.listeners.*;
import app.view.CenterPanel;
import app.view.HistogramWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CenterPanelController implements ActionListener {
    private CenterPanel _centerPanel;
    private HistogramWindow histogramWindow;
    private String selectedOption;
    private Action sumAction, avgAction, minAction, maxAction;

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
        _centerPanel.getJbtCount().addActionListener(e1 -> _centerPanel.getResultTextArea().append("Wybierz operacje! \n"));
        _centerPanel.getjComboBox().addActionListener(this);
        _centerPanel.getDateChooser().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    // Pobieranie wybranej daty
                    Date selectedDate = (Date) evt.getNewValue();

                    // Formatowanie daty
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(selectedDate);

                    // Ustawianie daty w JTextArea
                    _centerPanel.getResultTextArea().append("Wybrano datę: "+formattedDate+"\n");
                }
            }
        });
        _centerPanel.getTaskPaneCountGroup().add(new AbstractAction("Sumowanie") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SumListener(
                        _centerPanel.getTableModel(),
                        _centerPanel.getResultTextArea(),
                        _centerPanel.getTableModel().getRowCount(),
                        _centerPanel.getTableModel().getColumnCount())
                        .actionPerformed(e);
            }
        });
        _centerPanel.getTaskPaneCountGroup().add(new AbstractAction("Średnia") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AvgListener(
                        _centerPanel.getTableModel(),
                        _centerPanel.getResultTextArea(),
                        _centerPanel.getTableModel().getRowCount(),
                        _centerPanel.getTableModel().getColumnCount())
                        .actionPerformed(e);
            }
        });
        _centerPanel.getTaskPaneCountGroup().add(new AbstractAction("Min") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MinListener(
                        _centerPanel.getTableModel(),
                        _centerPanel.getResultTextArea(),
                        _centerPanel.getTableModel().getRowCount(),
                        _centerPanel.getTableModel().getColumnCount())
                        .actionPerformed(e);
            }
        });
        _centerPanel.getTaskPaneCountGroup().add(new AbstractAction("Max") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MaxListener(
                        _centerPanel.getTableModel(),
                        _centerPanel.getResultTextArea(),
                        _centerPanel.getTableModel().getRowCount(),
                        _centerPanel.getTableModel().getColumnCount())
                        .actionPerformed(e);
            }
        });
        _centerPanel.getTaskPaneChartGroup().add(new AbstractAction("Histogram") {
            @Override
            public void actionPerformed(ActionEvent e) {
                histogramWindow = new HistogramWindow(_centerPanel);
                histogramWindow.setVisible(true);
            }
        });
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
