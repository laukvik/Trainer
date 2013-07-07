package org.laukvik.trainer.set;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextWrapTabellCellRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Color odd = new Color(232,242,254);
	private Color even = new Color(255,255,255);

	public TextWrapTabellCellRenderer() {
		super();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setFont( table.getFont() );
		setText( (String) value );
		setLineWrap(true);
		setWrapStyleWord(true);
		if (isSelected) {
			setBackground( table.getSelectionBackground() );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground(row % 2 == 0 ? odd : even);
			setForeground( table.getForeground() );
		}
		
		return this;
	}

}
