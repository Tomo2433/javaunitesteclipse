package app.model;

import javax.swing.*;

public class SimpleComboBoxModel extends DefaultComboBoxModel {
    private String[] data;
    public SimpleComboBoxModel() {
        data = new String[]{"Wybierz operację",
                "Sumowanie", "Średnia", "Min i Max"};
    }
    @Override
    public int getSize() {
        return data.length;
    }
    @Override
    public String getElementAt(int index) {
        return data[index];
    }
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
