package org.laukvik.trainer.shop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class IconTableCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public static final Color EVEN = new Color(232,242,254);
	public static final Color ODD = new Color(255,255,255);
	
	public IconTableCellRenderer(){
		super();
		setOpaque( true );
		setHorizontalAlignment( IconTableCellRenderer.CENTER );
		setVerticalAlignment( IconTableCellRenderer.CENTER );
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected){
			setBackground( table.getSelectionBackground()  );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground(row % 2 == 0 ? ODD : EVEN );
			setForeground( table.getForeground()  );
		}
		Icon icon = (Icon) value;
		setIcon( icon );
		return this;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}

}