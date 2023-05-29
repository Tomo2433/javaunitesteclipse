package app.model;

import javax.swing.table.AbstractTableModel;
import java.util.Random;

public class IntegerTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final int countRowTable = 5;
    private final int countColumnTable = 5;
    private Integer[][] data = new Integer[countRowTable][countColumnTable];
    private String[] colName = {"1","2","3","4","5"};
    public IntegerTableModel() {
        super();
        setZeroTable();
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }
    @Override
    public int getRowCount() {
        return countRowTable;
    }

    @Override
    public int getColumnCount() {
        return countColumnTable;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object object = (Object) data[row][col];
        return object;
    }
    public Integer[][] getIntegerValuesTable() {
        return data;
    }
    public String getStringValuesTable() {
        String str = "";
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                str = str + data[i][j] +" ";
            }
        return str;
    }
    public String getColumnName(int col) {
        return colName[col];
    }
    public String[] getColumnNames() {
        return colName;
    }
    public void setValue(Integer value, int row, int col) {
        data[row][col] = value;
        fireTableDataChanged();
    }
    public void setRandomTable() {
        Random random = new Random();
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                // ograniczenie znaku liczby i zakresu do 10000
                data[i][j] = Math.abs(random.nextInt()) % 10000;
            }
        fireTableDataChanged();
    }
    public void setZeroTable() {
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                data[i][j] = new Integer(0);
            }
        fireTableDataChanged();
    }
    public Integer calculateSum() {
        Integer sum = new Integer(0);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                sum = sum + data[i][j];
            }
        return sum;
    }
    public Float calculateAverage() {
        Float avg = new Float(0.0);
        Integer sum = calculateSum();
        if(sum > 0) avg = (sum.floatValue())/(countRowTable*countColumnTable);
        return avg;
    }
}

