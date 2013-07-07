package org.laukvik.trainer.set;

import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;
//	private Color odd = new Color(232, 242, 254);
//	private Color even = new Color(255, 255, 255);
//	private JTable table;

	public TextAreaEditor( JTable table ){
		super(new JTextField());
		final JTextArea textArea = new JTextArea();
		textArea.setFont( table.getFont() );
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		editorComponent = scrollPane;

		delegate = new DefaultCellEditor.EditorDelegate() {

			private static final long serialVersionUID = 1L;

			public void setValue(Object value) {
				textArea.setText((value != null) ? value.toString() : "");
			}

			public Object getCellEditorValue() {
				return textArea.getText();
			}
		};
	}
}