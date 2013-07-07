package org.laukvik.trainer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.laukvik.swing.locale.LocaleListener;
import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.swing.platform.osx.OSXAdapter;
import org.laukvik.swing.platform.osx.OSXApplication;
import org.laukvik.trainer.journal.Journal;
import org.laukvik.trainer.journal.JournalListener;
import org.laukvik.trainer.journal.JournalManager;
import org.laukvik.trainer.journal.JournalPanel;
import org.laukvik.trainer.swing.JournalHelper;
import org.laukvik.trainer.swing.JournalProgressDialog;
 
/** 
 * Bugs CRITICAL
 * 
 * TODO - ExerciseDetailsPanel2 får feil størrelse når man åpner 
 * TODO - Kan ikke slette exercisePhoto
 * TODO - Få med markerte områder av muskel
 * TODO - Lage muskel/skin bilder av mann
 * 
 * 
 * BUGS
 * 
 * TODO - Det står "Unknown" i pulldown for øvelse
 * TODO - Tooltip blir stående igjen i droppable icon
 * TODO - Når man endrer språk blir workoutTable deselected og setsTable også
 * TODO - Skulder må deles inn i front, midtre og bak
 * 
 * Features
 * 
 * TOOD - når man åpner bilder i exercise så må det åpnes en browser og ikke en previewer
 * TODO - ExercisePhoto burde ha cheesy speilbilde for å se stili ut
 * TODO - Bilder må importeres til Bibliotek/Trainer som JPG
 * TODO - Øvelser må kunne bli tagges som maskin og som sammensatt/isolasjonsøvelse
 * TODO - Mulighet for å gi terningkast for øvelser i online delen
 * TODO - Få med TabbedPane på shoppen for å vise 1). nyheter 2). mest populær og 3++ kroppsdelene
 * TODO - Hver øvelse må ha forfatter navn og copyright på foto og link til forfatters hjemmeside
 * TODO - Øvelser som downloades må bli read-only i ExerciseEditor
 * TODO - Når man åpner shoppen så må øvelsene vise at man kan oppdatere til siste versjon
 * TODO - Grafen må hoppe automatisk til siste registrerte øvelse
 * TODO - Med checkbox for cheats i set
 * TODO - Grafene må ha punkter over linja
 * TODO - BodyStatus må ha gradient farge slik at rød blir intenst og grønt blir ikke intenst
 * TODO - Må ha "loading..." på SVGpanel
 * TODO - Klikke på graf må føre til angitt workout
 * TODO - Vise årskalender med oversikt over alle treninger for et helt år
 * TODO - Mulighet for å flytte sets opp og ned 
 * TODO - Lage søk i ExerciseEditorPanel?
 * TOOD - Lage støtte for uendelig mange bilder i exerciseEditorPanel
 * TODO - Lage historikk for 1rep max med graf som viser %sats ift 1r max?
 * TODO - Lage backup/restore
 * 
 * Forslag
 * 
 * TODO - Legge til stjerner for hvert sett for å indikere hvor bra man følte seg
 * TODO - Legge til årstidsbilder i graf
 * TODO - Lage treningsprogram
 * 
 * 10.jan - Øvelsespulldown står det bare "Ytre" og ikke "Ytre mage"
 * 10.jan - Language Combo klarer ikke å sette "ingen"
 * 10.jan - Language Combo er ikke alfabetisk
 * 10.jan - Kan ikke gå an å sette ekstra muskler når den er hovedmuskel i exercise
 * 10.jan - Vanlig icon vises ikke lenger
 * 10.jan - YouTube knapp og WWW knapp må bli disabled når det ikke er en URL
 * 2.jan - Øvelse må få språk alternativ 
 * 2.jan - Øvelse må få link til youtube
 * 1.jan - Få med heart beat på aerobic exercises
 * 31.des - Når man viser at en ny versjon er tilgjengelig så må en html side med features vises
 * 31.des - Få med velkomstmelding med [ x ] Dont show this again som viser features og introduksjon
 * 30.des - Username i OS blir default navn
 * 30.des - Legge til kommentarer for set
 * 29.des - Mulighet for å laste ned Exercises
 * 29.des - Drag n drop må huske orginal path til bildene slik at man kan etterhåndtere de
 * 27.des - Kalender i properties står på engelsk
 * 31.juli - Få med aerobic symboler i kalenderen
 * 18.mai - Når man legger til bilde så synes de ikke i combobox før man har restartet
 * 14.mai - Pulldown i SetsTable viser disabled øvelser
 * 14.mai - Det mangler flagg i språkpulldown
 * 14.mai - Vises ingen bilder i SVGene
 * 14.mai - Når man lager ny workout så kan den ikke slettes med en gang
 * 14.mai - Når man får spørsmål om å slette exercise så må man liste opp alle workouts som bruker exercisen
 * 12.mai - Check update mangler ikon
 * 12.mai - vindustittel står på engelsk
 * 12.mai - Rediger menyen må bli grået ut når det ikke er valg
 * 12.mai - Det står "Muskel: Shoulder" i pulldown
 * 12.mai - Fjerne Check update i meny
 * 12.mai - Meny er på engelsk
 * 12.april - Programmer må velge språk basert på OS innstillinger
 * 12.april - Statusdialogen er ikke grå helt nederst
 * 12.april - Status graf er ikke riktig
 * 12.april - DatePicker støtter ikke språk
 * 12.april - YearSpinner kan skrive inn tekst
 * 11.april - Exercises må bli default enabled når de blir opprettet
 * 11.apil - Pulldown i SetsTable støtter ikke språk
 * 11.april - SetsTable endrer ikke språk
 * 7.april - Lage språkvalg
 * 5.april - Pulldown i SetsTable må vise alfabetisk listen over øvelser
 * 5.april - Combobox viser feil størrelse ved lukking
 * 5.april - ComboBox i sets må kun kunne opp ved dobbelt klikk
 * 3.april - Øvelser blir ikke selectet i treet når man legger til øvelse
 * 3.april - Alle komponentene i exerciseeditorpanel må være resizable
 * 3.april - Høyre juster slider for muskel/skin
 * 2.april - Notatfeltet skal fylle helt ned til bunn av programmet
 * 2.april - Kalender har feil størrelse
 * 2.april - Graf oppdaterer seg ikke når kalender dato går utover nytt år
 * 2.april - Støtte for kjønn i øvelser og status dialoger
 * 2.april - Fjerne vekt fra innstillinger
 * 1.april - Fjerne vekt fra innstillinger
 * 1.april - Månedskalender mister fokus på dag når måned endres
 * 31.mars - Å trykke nei på bekreft sletting av øvelse førte til deselektering av øvelse
 * 31.mars - La til relevante ikoner på bekreft vinduder
 * 31.mars - La til vindu tittel på exerciseditor og innstillinger
 * 31.mars - Fjernet set icon
 * 31.mars - La til tykkere årslinje med semitransparent og ikke sort
 * 31.mars - TabbedPane må bli nøyaktig like stor som SVG grafen
 * 31.mars - Treningsøkt tabellen mister selection når en exercise endres
 * 31.mars - Rettet bug med enabling 
 * 30.mars - Enabling/disabling av exercise må oppdateres umiddelbart
 * 30.mars - Legge til bekreft slett dialog
 * 30.mars - Når man legger til ny treningsøkt så blir ikke den valgt
 * 30.mars - Mouse over i graf er på engelsk. Tekst i mouseover er ikke riktig posisjonert
 * 30.mars - Tekst i exercise description wrapper på ord
 * 29.mars - Application Menu handling has been disabled. Støtte for Leopard.
 * 29.mars - La til baseline alignment i Text i SVG. Rettet TextAnchor bug.
 * 29.mars - La inn manglende scroll komponenter for at BodyStatusDialog skulle se bra ut
 * 29.mars - La inn dragdrop av bilder fra browser og paste til droppableicon. Pluss auto resize av bilder. La på tykkere border.
 * 29.mars - Fikset feil muscleID på øvre rygg
 * 28.mars - Lage av/på knapp for øvelse i ExerciseEditorPanel for å skjule ubrukte øvelser 
 * 19.mars - Mål må ha med bakgrunnsbilde
 * 19.mars - Legge til støtte for å lage historikk av vekt
 * 18.mars - Når man sletter en øvelse må editoren få tomme felter
 * 18.mars - Tree i ExerciseEditor oppdateres ikke riktig ved bla sletting av øvelse
 * 18.mars - Tooltip for øvelser er ikke satt
 * 18.mars - Øvelsesknapper må bli uthevet når man klikker på de
 * 18.mars - Flytt details knapen helt til høyre
 * 18.mars - Mangler ikoner på øvelser
 * 17.mars - Øvelse editor husker ikke hvor den var
 * 17.mars - Legge til valg for kjønn
 * 17.mars - Tabbed pane får ikke med seg når man legger til en øvelse
 * 17.mars - Exception når en øvelse er valgt i sets table og man sletter en øvelse
 * 17.mars - Å legge til exercise får feil muscleID
 * 17.mars - Ved sletting av øvelse så må man få beskjed om hvor mange øvelser som bruker denne
 * 17.mars - Blir exception når en exercise endres
 * 17.mars - Mouseover i SVG grafen virker ikke
 * 17.mars - Ikonbilde for øvelse blir lik for alle!
 * 17.mars - Legg inn hovedknapper for muskel og fjern multitabber
 * 17.mars - Fjerne lite muskelbilde og muskel notater
 * 17.mars - Skal grafene vises i SVG?
 * 17.mars - Graf viser kun ett år!
 * 9.mars - Legge inn språktekster for tree leafs i exerciseeditorpanel
 * 9.mars - Lage ferdig alle female SVG bilder
 * 9.mars - Slider skal huske verdier fra forrige gang og oppdater bildet også
 * 9.mars - Fjerne scrollbar fra muscle bildet
 * DONE - Få med antall øvelser i muskel
 * DONE - Legge inn taber for muskelgrupper
 * DONE - Innstillinger må få med dato 
 * DONE - Innstillinger må få med OK og cancel 
 * DONE - Legge inn muskelgruppe pulldown
 * DONE - Legge inn støtte for muskelgrupper i øvelser
 * DONE - Øvelser må få med OK og cancel 
 * DONE - Mangler oversettelse når det er tom med exercises
 * DONE - Dato er ikke språk
 * DONE - Månedsnavn i workout tabell er på engelsk
 * @author morten
 *
 */
public class TrainerApp extends JFrame implements OSXApplication, JournalListener, LocaleListener {

	private static final long serialVersionUID = 1L;
	private JournalPanel journalPanel;

	public TrainerApp(){
		getRootPane().putClientProperty( "Window.style", "small" );
		getRootPane().putClientProperty( "apple.awt.brushMetalLook", Boolean.TRUE );
		setLocale( JournalHelper.getDefaultLocale() );
		UIManager.put("OptionPane.yesButtonText", JournalHelper.getLanguage( getLocale(), "yes") );
		UIManager.put("OptionPane.noButtonText", JournalHelper.getLanguage( getLocale(), "no") );
		UIManager.put("OptionPane.cancelButtonText", JournalHelper.getLanguage( getLocale(), "cancel") );
		
		setLayout( new BorderLayout() );
		journalPanel = new JournalPanel( new Journal() );
		add( journalPanel.getToolbar(), BorderLayout.NORTH );
		add( journalPanel, BorderLayout.CENTER );
		setJMenuBar( journalPanel.getJournalMenu() );
	
		if (Toolkit.getDefaultToolkit().getScreenSize().width <= 1280){
			setSize( Toolkit.getDefaultToolkit().getScreenSize() );
			setLocation(0,0);	
		} else {
			setSize( new Dimension(955,600) );
			setLocationRelativeTo( null );
		}

		registerForMacOSXEvents();

		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		addWindowListener(
			new WindowAdapter(){						
				public void windowClosing(WindowEvent evt){
					quit();
				}
			}
		);
		
	}
	
	public void localeChanged(Locale locale) {
		setLocale( locale );
		UIManager.put("OptionPane.yesButtonText", JournalHelper.getLanguage( getLocale(), "yes" ) );
		UIManager.put("OptionPane.noButtonText", JournalHelper.getLanguage( getLocale(),  "no" ) );
		UIManager.put("OptionPane.cancelButtonText", JournalHelper.getLanguage( getLocale(), "cancel" ) );
		journalPanel.localeChanged(locale);
	}
	
	public void about(){
		journalPanel.about();
	}
	
	public void quit() {
		save();
		System.exit(0);
	}
	
	public void loadJournal( Journal journal ){
		journal.removeLocaleListener( journalPanel );
		journal.removeLocaleListener( this );
		journal.removeJournalListener( this );
		journal.removeJournalListener( journalPanel );
		journal.addJournalListener( this );
		journal.addJournalListener( journalPanel );
		journalPanel.loadJournal( journal );
		journal.fireJournalLoaded();
		localeChanged( journal.getLocale() );
		journal.addLocaleListener( this );
		journal.addLocaleListener( journalPanel );
//		journalPanel.checkForUpdate( true );
	}
	
	public void save(){
//		final JournalProgressDialog progress = new JournalProgressDialog( this, JournalHelper.getLanguage( getLocale(), "progress.saving" ) );
		final JournalProgressDialog progress = new JournalProgressDialog( this, "" );
        Thread t = new Thread( new Runnable() {
			public void run() {
				try {
					
					JournalManager manager = new JournalManager();
					manager.addReadWriteListener( progress );
					
					manager.save( journalPanel.getJournal() );
				} catch (Exception e) {
					progress.setText( e.getMessage() );
				} finally {
					progress.closeDialog();
					setVisible( true );
				}
			}
		});
        
        t.start();
        progress.showDialog();
	}
	
	public void load( final File file ){
//		final JournalProgressDialog progress = new JournalProgressDialog( this, JournalHelper.getLanguage( getLocale(), "progress.loading" ) );
		final JournalProgressDialog progress = new JournalProgressDialog( this, "" );
        Thread t = new Thread( new Runnable() {
			public void run() {
				try {
					
					JournalManager manager = new JournalManager();
					manager.addReadWriteListener( progress );
					if (!file.exists()){
						manager.createEmpty();
					}
					Journal j = manager.read( new FileInputStream( file ) );
					loadJournal( j );
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					progress.closeDialog();
					setVisible( true );
				}
			}
		});
        
		t.start();
		progress.showDialog();
	}

	public void preferences() {
		journalPanel.preferences();
	}
	
	public void setOwnerText(Journal journal){
		setTitle( JournalHelper.getLanguage( journal.getLocale(), "journal.ownertext", journal.getOwner() ) ); 
	}

	public void journalChanged(Journal journal) {
		setOwnerText(journal);
	}

	public void ownerChanged(Journal journal) {
		setOwnerText(journal);
	}

	public void journalLoaded(Journal journal) {
		setOwnerText(journal);
	}
	
	/**
	 * Loads a file from finder
	 * 
	 * @param file
	 */
	public void loadFile( String file ){		
	}

	/**
	 * Generic registration with the Mac OS X application menu 
	 * Checks the platform, then attempts to register with the Apple EAWT
	 * See OSXAdapter.java to see how this is done without directly referencing any Apple APIs
	 */
    public void registerForMacOSXEvents() {
        if (CrossPlatformUtilities.isMacOSX()) {
            try {
                // Generate and register the OSXAdapter, passing it a hash of all the methods we wish to
                // use as delegates for various com.apple.eawt.ApplicationListener methods
                OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("quit", (Class[])null));
                OSXAdapter.setAboutHandler(this, getClass().getDeclaredMethod("about", (Class[])null));
                OSXAdapter.setPreferencesHandler(this, getClass().getDeclaredMethod("preferences", (Class[])null));
                OSXAdapter.setFileHandler(this, getClass().getDeclaredMethod("loadFile", new Class[] { String.class }));
            } catch (Exception e) {
                System.err.println("Error while loading the OSXAdapter:");
                e.printStackTrace();
            }
        }
    }

	/**
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main( String [] args ) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CrossPlatformUtilities.setCrossPlatformProperties();
				TrainerApp app = new TrainerApp();
				app.load(JournalManager.getJournalFile());
			}
		});
	}
    
}