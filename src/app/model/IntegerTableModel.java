package app.model;

import javax.swing.table.AbstractTableModel;
import java.util.Random;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>IntegerTableModel</code> definiująca model integerTable
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class IntegerTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final int countRowTable = 5;
    private final int countColumnTable = 5;
    private Integer[][] data = new Integer[countRowTable][countColumnTable];
    private String[] colName = {"1","2","3","4","5"};

    /**
     * Konstruktor bezparametrowy klasy <code>IntegerTableModel</code>
     */
    public IntegerTableModel() {
        super();
        setZeroTable();
    }

    /**
     * Przeciążenie klasy do zmiany typu danych na integer
     * @param columnIndex  the column being queried
     * @return
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    /**
     * Metoda zwraca liczbe wierszy
     * @return countRowTable
     */
    @Override
    public int getRowCount() {
        return countRowTable;
    }

    /**
     * Metoda zwraca liczbe kolumn
     * @return countColumnTable
     */
    @Override
    public int getColumnCount() {
        return countColumnTable;
    }

    /**
     * Metoda zwraca dane na indeksie tabeli
     * @param row        the row whose value is to be queried
     * @param col     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int row, int col) {
        Object object = (Object) data[row][col];
        return object;
    }

    /**
     * Metoda zwraca wartosci integer
     * @return data
     */
    public Integer[][] getIntegerValuesTable() {
        return data;
    }
    /**
     * Metoda zwraca wartości zmienione na typ Double
     * @return values
     */
    public double[] getDoubleValues() {
        double[] values = new double[countColumnTable*countRowTable];
        int counter = 0;
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                values[counter] = (double)data[i][j];
                counter++;
            }
        return values;
    }
    public String getStringValuesTable() {
        String str = "";
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                str = str + data[i][j] +" ";
            }
        return str;
    }

    /**
     * Metoda zwraca nazwy kolumn
     * @param col  the column being queried
     * @return
     */
    public String getColumnName(int col) {
        return colName[col];
    }
    public String[] getColumnNames() {
        return colName;
    }

    /**
     * Metoda ustawia wartosc komórki
     * @param value
     * @param row
     * @param col
     */
    public void setValue(Integer value, int row, int col) {
        data[row][col] = value;
        fireTableDataChanged();
    }

    /**
     * Metoda ustawia losowe wartości tabeli
     */
    public void setRandomTable() {
        Random random = new Random();
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                // ograniczenie znaku liczby i zakresu do 10000
                data[i][j] = Math.abs(random.nextInt()) % 10000;
            }
        fireTableDataChanged();
    }

    /**
     * Metoda ustawia zerowe wartości tabeli
     */
    public void setZeroTable() {
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                data[i][j] = new Integer(0);
            }
        fireTableDataChanged();
    }

    /**
     * Metoda sumuje wartości tabeli
     * @return
     */
    public Integer calculateSum() {
        Integer sum = new Integer(0);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                sum = sum + data[i][j];
            }
        return sum;
    }

    /**
     * Metoda liczy średnią wartość kolumn
     * @return
     */
    public Float calculateAverage() {
        Float avg = new Float(0.0);
        Integer sum = calculateSum();
        if(sum > 0) avg = (sum.floatValue())/(countRowTable*countColumnTable);
        return avg;
    }
}

