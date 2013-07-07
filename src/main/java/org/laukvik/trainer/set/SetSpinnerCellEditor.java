package org.laukvik.trainer.set;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class SetSpinnerCellEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

	private static final long serialVersionUID = 1L;
	final RepSpinner spinner;

	public SetSpinnerCellEditor(){
		super();
		spinner = new RepSpinner();
	}
	
    // Prepares the spinner component and returns it.
    public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    // Enables the editor only for double-clicks.
    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent)evt).getClickCount() >= 2;
        }
        return true;
    }

    // Returns the spinners current value.
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return spinner;
	}
	
}