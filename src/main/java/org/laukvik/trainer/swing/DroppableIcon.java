package org.laukvik.trainer.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.laukvik.swing.platform.CrossPlatformUtilities;

public class DroppableIcon extends JLabel implements DropTargetListener, MouseListener, FocusListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Dimension size = new java.awt.Dimension( 100, 100 );
	private Border NORMAL = javax.swing.BorderFactory.createEtchedBorder();
	private Border MOUSEOVER = javax.swing.BorderFactory.createLineBorder( UIManager.getColor("TextArea.selectionBackground"), 2 );
	private Border SELECTED = javax.swing.BorderFactory.createLineBorder( UIManager.getColor("TextArea.selectionBackground"), 2 );
	private String NOIMAGE;
	private boolean selected;
	private Vector <IconDroppedListener> listeners;
	private boolean autoResize = true;
	private Dimension autoResizeSize = new java.awt.Dimension( 100, 100 ); 
	private URL originalURL;

	public DroppableIcon( String noimagetext ){
		listeners = new Vector<IconDroppedListener>();
		this.NOIMAGE = noimagetext;
		setFont( new Font( getFont().getName(), Font.PLAIN, 8 ) );
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setText( noimagetext );
        setBorder( NORMAL );
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setMaximumSize( size );
        setMinimumSize( size );
        setPreferredSize( size );
        new DropTarget( this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null );
        setFocusable( true );
        addMouseListener( this );
        addFocusListener( this );
        addKeyListener( this );
	}

	public void setLocale(Locale l) {
		super.setLocale(l);
		repaint();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (enabled){
			setForeground( UIManager.getColor("Label.enabledForeground") );
			setBorder( NORMAL );
		}else{
			setForeground( UIManager.getColor("Label.disabledForeground") );
			setBorder( NORMAL );
		}
	}
	
	public void setNoImage(String noimage) {
		this.NOIMAGE = noimage;
	}
	
	public void addIconListener( IconDroppedListener l ){
		listeners.add( l );
	}
	
	public void removeIconListener( IconDroppedListener l ){
		listeners.remove( l );
	}
	
	public void setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
	}
	
	public boolean isAutoResize() {
		return autoResize;
	}
	
	public void fireIconChanged( Icon icon ){
		for (IconDroppedListener l : listeners){
			l.iconChanged( icon, originalURL ); 
		}
	}
	
	public void fireIconRemoved(){
		if (listeners == null){
			return;
		}
		for (IconDroppedListener l : listeners){
			l.iconRemoved();
		}
	}

	public void dragEnter(DropTargetDragEvent dtde) {
		if (isEnabled()){
			if (dtde.isDataFlavorSupported( DataFlavor.javaFileListFlavor ) || dtde.isDataFlavorSupported( DataFlavor.imageFlavor )|| dtde.isDataFlavorSupported( DataFlavor.stringFlavor )){
				dtde.acceptDrag( DnDConstants.ACTION_COPY );
			} else {
				dtde.rejectDrag();
			}
			
		} else {
			dtde.rejectDrag();
		}
	}

	public void dragExit(DropTargetEvent dte) {
		setBorder( NORMAL );
	}

	public void dragOver(DropTargetDragEvent dtde) {
		if (isEnabled()){
			setBorder( MOUSEOVER );
		}
	}

	
	public void log( Object message ){
		System.out.println( this.getClass().getName() + ": "+ message );
	}
	
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop( dtde.getDropAction() );
		Transferable t = dtde.getTransferable();
		importTransferable( t );
		setBorder( NORMAL );
	}
	
	public static String toHtml( String text ){
		if (text != null){
			return "<html>" + text.replaceAll("\n", "<br>" ) + "</html>";	
		} else {
			return null;
		}
	}
	
	public void setIcon( Icon icon, URL url ) {
		this.originalURL = url;
		setIcon( icon );
		if (url == null){
			setToolTipText( null );	
		} else {
			setToolTipText( url.toExternalForm() );	
		}
	}
	
	public void setIcon( Icon icon ) {
		super.setIcon(icon);
		if (icon == null || icon.getIconWidth() == -1 || icon.getIconHeight() == -1){
			setText( toHtml( NOIMAGE ) );
			setURL( null );
			fireIconRemoved();
		} else {
			setText( "" );
			if (isAutoResize()){
				ImageIcon imageIcon = (ImageIcon) icon;
				Image bim = resize( getBufferedImage(imageIcon.getImage()), autoResizeSize, true );
				super.setIcon( new ImageIcon( bim ) );
			}
			fireIconChanged( icon );
		}
	}


	public void setNoImage(){
		setIcon( null );
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		setBorder( NORMAL );
	}

	/**
	 * 
	 */
	public void mouseClicked(MouseEvent e) {
		if (isEnabled()){
			if (e.getClickCount() == 2){
				log( "mouseClicked: " + getURL() );
				if (getURL() != null){
					try {
						CrossPlatformUtilities.openBrowser( getURL() );
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}

			} else {
				toggleSelected();
			}	
		}
	}

	public void toggleSelected() {
		setSelected( !isSelected() );
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (this.selected){
			grabFocus();
			setBorder( SELECTED );
		} else {
			
			setBorder( NORMAL );
		}
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		setSelected( false );
	}
	
	/**
	 * 
	 * @param t
	 */
	public boolean importTransferable( Transferable t ){
		if (t.isDataFlavorSupported( DataFlavor.javaFileListFlavor )){
			try {
				@SuppressWarnings("unchecked")
				List<File> fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
				@SuppressWarnings("unused")
				File [] files = (File[]) fileList.toArray();
				if (files.length == 1){
					File file = files[ 0 ];
					ImageIcon icon = getImageIcon( file );
					if (icon != null){
						setIcon( icon, file.toURL() );
						return true;
					}
				} else {
					
				}
			} catch (Exception e) {
				log( "Could not import file: " + e.getMessage() );
			}

		}
		
		if (t.isDataFlavorSupported( DataFlavor.imageFlavor )){
			try {
				
				for (DataFlavor df : t.getTransferDataFlavors()){
					try{
						if (df.isMimeTypeEqual("image")){
							java.awt.Image image = (Image) t.getTransferData( DataFlavor.imageFlavor );
							log( "drop: " + image );
							setIcon( (Icon) image, null );
							return true;
						}
					} catch (Exception e2){
						e2.printStackTrace();
					}
				}
				

			} catch (Exception e) {
				log( "Could not import file: " + e.getMessage() );
			}	
		}  
		
		if (t.isDataFlavorSupported( DataFlavor.stringFlavor )){
			try {
				String s = (String) t.getTransferData( DataFlavor.stringFlavor );
				log( "drop: " + s );
				URL url = new URL(s);
				ImageIcon icon = getImageIcon( url );
				if (icon != null){
					setIcon( icon, url );
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log( "Could not import file: " + e.getMessage() );
			}
		}  
		
		return false;
	}
	
	public void setURL( URL url ){
		log( "Setting URL to: " + url );
		this.originalURL = url;
	}
	
	public URL getURL(){
		return originalURL;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE){
			setIcon( null );
			setToolTipText( null );
			fireIconRemoved();
		} else if (e.getKeyCode() == KeyEvent.VK_V && e.isMetaDown()){
			// Get clipboard
			Clipboard c = this.getToolkit().getSystemClipboard(); 
			// Get its contents
		    Transferable t = c.getContents(this);                 
		    /* Import */
		    importTransferable( t );
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
	
	/* FEATURES COPIED FROM PHOTOMANAGER */
	
	public static ImageIcon getImageIcon( URL url ) throws InterruptedException, MalformedURLException{
		Image img = getImage( url );
		ImageIcon icon = new ImageIcon( img );
		return icon;
	}

	public static ImageIcon getImageIcon( File file ) throws InterruptedException, MalformedURLException{
		Image img = getImage( file.toURL() );
		ImageIcon icon = new ImageIcon( img );
		return icon;
	}
	
	public static Image getImage( URL url ) throws InterruptedException, MalformedURLException{
		Image img = getFakeImageObserver().getToolkit().createImage( url );
		MediaTracker tracker = new MediaTracker( getFakeImageObserver() );
		tracker.addImage(img, 0);
		tracker.waitForID(0);
		return img;	
	}

	public static BufferedImage getBufferedImage( Image img ){
		int width = img.getWidth( getFakeImageObserver() );
	    int height= img.getHeight( getFakeImageObserver() );
	    BufferedImage bufferedImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
	    Graphics2D g2 = bufferedImage.createGraphics();
	    g2.drawImage( img, 0 ,0 , null );
	    return bufferedImage;
	}
	
	public final static Component getFakeImageObserver(){
		return new Component(){
			private static final long serialVersionUID = 1L;
		};
	}
	
	public Image resize( BufferedImage bi, Dimension size, boolean keepAspectRatio ){
		if (keepAspectRatio){
			Dimension aspect = getSize( new Dimension(bi.getWidth(),bi.getHeight()), size );
			return bi.getScaledInstance( aspect.width, aspect.height, BufferedImage.SCALE_SMOOTH );
		} else {
			return bi.getScaledInstance( size.width, size.height, BufferedImage.SCALE_SMOOTH );
		}
	}
	
	public static Dimension getSize( Dimension from, Dimension to ){
		Dimension size = new Dimension();
		boolean portrait = from.height > from.width;
		float ratio = (float)from.width / (float)from.height;
		if (portrait){
			size.height = to.height;
			float factor = (float) from.height / (float) to.height;
			size.width = (int) ( (float)from.width / factor);
		} else {
			size.width = to.width;
			float factor = (float)from.width / (float)to.width;
			size.height = (int) ((float)from.height / factor);
		}
		System.out.println( "From: " + from.width+"x"+from.height + " To:" +  to.width+"x"+to.height +  " Ratio: " + ratio );
		return size;
	}

	public static void main( String [] args ){
//		Dimension fromSize = new Dimension( 181, 296 );
//		Dimension fromSize = new Dimension( 317, 480 );
		Dimension fromSize = new Dimension( 191, 279 );
		Dimension toSize = new Dimension( 100, 100 );
		Dimension newSize = getSize( fromSize, toSize );
		
//		System.out.println( newSize.width + "x" + newSize.height  );
	}
	
}