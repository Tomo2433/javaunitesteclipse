package app.model;

import javax.swing.*;

/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>SimpleComboBoxMode</code> definiująca model combobox
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class SimpleComboBoxModel extends DefaultComboBoxModel {
    /**
     * tablica danych 
     */
    private String[] data;
    /**
     * Konstruktor bezparametrowy klasy <code>SimpleComboBoxModel</code>
     */
    public SimpleComboBoxModel() {
        data = new String[]{"Wybierz operację",
                "Sumowanie", "Średnia", "Min i Max"};
    }

    /**
     * Metoda pobierająca długość tablicy danych
     * @return data.length
     */
    @Override
    public int getSize() {
        return data.length;
    }

    /**
     * Metoda pobiera element na indeksie
     * @param index the requested index
     * @return data[index]
     */
    @Override
    public String getElementAt(int index) {
        return data[index];
    }

    /**
     * Metoda ustawia zaznaczony element
     * @param item The combo box value or null for no selection.
     */
    @Override
    public void setSelectedItem(Object item) {
        if (item instanceof String) {
            String selectedItem = (String) item;
            if (selectedItem.equals(getSelectedItem())) {
                return;
            }
            super.setSelectedItem(item);
            fireContentsChanged(this, -1, -1);
        }
    }

}
