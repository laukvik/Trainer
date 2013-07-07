package org.laukvik.trainer.shop;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

import org.laukvik.trainer.exercise.Exercise;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.shop.multi.MultiTasker;
import org.laukvik.trainer.shop.multi.Task;
import org.laukvik.trainer.shop.multi.TaskListener;

public class ShopDialog extends JDialog implements ActionListener, TaskListener {

	private static final long serialVersionUID = 1L;
	private JournalPanel journalPanel;
	private ShopTable table;
	private JPanel buttonPanel;
	private JButton cancelButton, acceptButton;
	private WebBrowser browser;
	private MultiTasker multi;

	public ShopDialog( JournalPanel journalPanel ){
		super();
		this.journalPanel = journalPanel;
		this.multi = new MultiTasker();
		initComponents();
	}

	public void initComponents(){
		acceptButton = new JButton( journalPanel.getLanguage("shop.install") );
		cancelButton = new JButton( journalPanel.getLanguage("shop.cancel") );
		
		acceptButton.addActionListener( this );
		cancelButton.addActionListener( this );
		
		buttonPanel = new JPanel();
		buttonPanel.add( acceptButton );
		buttonPanel.add( cancelButton );
		
		table = new ShopTable();
		setLayout( new BorderLayout() );
				
//		shopPanel = new ShopPanel( journalPanel.getJournal() );
//		add( shopPanel, BorderLayout.NORTH );
		URL url = null;
		try {
			url = new URL("http://www.ibm.com");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		browser = new WebBrowser();
		browser.setURL( url );
		add( browser, BorderLayout.CENTER );
		
		
//		add( new JScrollPane( table ) );
//		add( buttonPanel, BorderLayout.SOUTH );
		setTitle( journalPanel.getLanguage( "shop" ) );
	}
	
	/**
	 * 
	 */
	public void openDialog(){
		JournalManager mgr = new JournalManager();
		try {
			table.setJournal( mgr.readShop() );
		} catch (Exception e) {
			table.setJournal( new Journal() );
		}
		loadImages();
		setSize( 600, 640 );
		setModal( true );
		setLocationRelativeTo( journalPanel.getRootPane() );
		setVisible( true );
		
	}
	
	public void loadImages(){
		
		multi.addTaskListener( this );
		
		Journal j = table.getJournal();
		log( "Found " + j.getExercises().size() );
		for (int x=0; x<j.getExercises().size(); x++){
			Exercise ex = j.getExercises().get( x );
			multi.addTask( new ExerciseImageDownloadTask( ex ) );
		}
		multi.setMaxTasks( 5 );
		multi.start( 250 );
	}
	
	public void log( Object message ){
		System.out.println( this.getClass().getName() + ": "+ message );
	}

	public void actionPerformed(ActionEvent e) {
		multi.stop();
		if (e.getSource() == acceptButton){
			/* User clicked on accept button */
			journalPanel.getJournal().addExercise( table.getSelectedExercises() );
		}
		
		dispose();
	}

	public void taskAborted(Task task) {

	}

	public void taskCompleted(Task task) {
		Exercise exercise = ((ExerciseImageDownloadTask) task).getExercise();
		table.tableChanged( new TableModelEvent( table.getModel(), table.getJournal().getExercises().indexOf( exercise ) ) );
	}

	public void taskStarted(Task task) {

	}

	public void taskStatus(Task task) {
		// TODO Auto-generated method stub
		
	}

    public void taskCompleted(Task task) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
}