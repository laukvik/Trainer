package org.laukvik.trainer.shop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BooleanTableCellRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public static final Color EVEN = new Color(232,242,254);
	public static final Color ODD = new Color(255,255,255);
	
	public BooleanTableCellRenderer(){
		super();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setFont( table.getFont() );
		setSelected( (Boolean) value );
		if (isSelected){
			setBackground( table.getSelectionBackground()  );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground( row % 2 == 0 ? ODD : EVEN );
			setForeground( table.getForeground()  );
		}
		if (!isEnabled()){
			return new JPanel();
		}
		
		return this;
	}

	public void paint(Graphics g) {
		if (isEnabled()){
			super.paint(g);
		} else {
			
		}
	}

}