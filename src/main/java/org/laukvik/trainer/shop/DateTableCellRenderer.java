package org.laukvik.trainer.shop;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class DateTableCellRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public static final Color EVEN = new Color(232,242,254);
	public static final Color ODD = new Color(255,255,255);
	private SimpleDateFormat format;
	
	public DateTableCellRenderer( Locale locale ){
		super();
		format = new SimpleDateFormat( "d MMMMM yyyy", locale );
		setLineWrap( true );
		setWrapStyleWord( true );
	}
	
	public DateTableCellRenderer(){
		super();
		format = new SimpleDateFormat( "d MMMMM yyyy");
		setLineWrap( true );
		setWrapStyleWord( true );
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		setFont( table.getFont() );		
		if (isSelected){
			setBackground( table.getSelectionBackground()  );
			setForeground( table.getSelectionForeground() );
		} else {
			setBackground(row % 2 == 0 ? ODD : EVEN );
			setForeground( table.getForeground()  );
		}
		GregorianCalendar cal = (GregorianCalendar) value;
		setText( format.format( cal.getTime() ) );
		return this;
	}

}