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
 * TODO - ExerciseDetailsPanel2 f�r feil st�rrelse n�r man �pner TODO - Kan ikke
 * slette exercisePhoto TODO - F� med markerte omr�der av muskel TODO - Lage
 * muskel/skin bilder av mann
 *
 *
 * BUGS
 *
 * TODO - Det st�r "Unknown" i pulldown for �velse TODO - Tooltip blir st�ende
 * igjen i droppable icon TODO - N�r man endrer spr�k blir workoutTable
 * deselected og setsTable ogs� TODO - Skulder m� deles inn i front, midtre og
 * bak
 *
 * Features
 *
 * TOOD - n�r man �pner bilder i exercise s� m� det �pnes en browser og ikke en
 * previewer TODO - ExercisePhoto burde ha cheesy speilbilde for � se stili ut
 * TODO - Bilder m� importeres til Bibliotek/Trainer som JPG TODO - �velser m�
 * kunne bli tagges som maskin og som sammensatt/isolasjons�velse TODO -
 * Mulighet for � gi terningkast for �velser i online delen TODO - F� med
 * TabbedPane p� shoppen for � vise 1). nyheter 2). mest popul�r og 3++
 * kroppsdelene TODO - Hver �velse m� ha forfatter navn og copyright p� foto og
 * link til forfatters hjemmeside TODO - �velser som downloades m� bli read-only
 * i ExerciseEditor TODO - N�r man �pner shoppen s� m� �velsene vise at man kan
 * oppdatere til siste versjon TODO - Grafen m� hoppe automatisk til siste
 * registrerte �velse TODO - Med checkbox for cheats i set TODO - Grafene m� ha
 * punkter over linja TODO - BodyStatus m� ha gradient farge slik at r�d blir
 * intenst og gr�nt blir ikke intenst TODO - M� ha "loading..." p� SVGpanel TODO
 * - Klikke p� graf m� f�re til angitt workout TODO - Vise �rskalender med
 * oversikt over alle treninger for et helt �r TODO - Mulighet for � flytte sets
 * opp og ned TODO - Lage s�k i ExerciseEditorPanel? TOOD - Lage st�tte for
 * uendelig mange bilder i exerciseEditorPanel TODO - Lage historikk for 1rep
 * max med graf som viser %sats ift 1r max? TODO - Lage backup/restore
 *
 * Forslag
 *
 * TODO - Legge til stjerner for hvert sett for � indikere hvor bra man f�lte
 * seg TODO - Legge til �rstidsbilder i graf TODO - Lage treningsprogram
 *
 * 10.jan - �velsespulldown st�r det bare "Ytre" og ikke "Ytre mage" 10.jan -
 * Language Combo klarer ikke � sette "ingen" 10.jan - Language Combo er ikke
 * alfabetisk 10.jan - Kan ikke g� an � sette ekstra muskler n�r den er
 * hovedmuskel i exercise 10.jan - Vanlig icon vises ikke lenger 10.jan -
 * YouTube knapp og WWW knapp m� bli disabled n�r det ikke er en URL 2.jan -
 * �velse m� f� spr�k alternativ 2.jan - �velse m� f� link til youtube 1.jan -
 * F� med heart beat p� aerobic exercises 31.des - N�r man viser at en ny
 * versjon er tilgjengelig s� m� en html side med features vises 31.des - F� med
 * velkomstmelding med [ x ] Dont show this again som viser features og
 * introduksjon 30.des - Username i OS blir default navn 30.des - Legge til
 * kommentarer for set 29.des - Mulighet for � laste ned Exercises 29.des - Drag
 * n drop m� huske orginal path til bildene slik at man kan etterh�ndtere de
 * 27.des - Kalender i properties st�r p� engelsk 31.juli - F� med aerobic
 * symboler i kalenderen 18.mai - N�r man legger til bilde s� synes de ikke i
 * combobox f�r man har restartet 14.mai - Pulldown i SetsTable viser disabled
 * �velser 14.mai - Det mangler flagg i spr�kpulldown 14.mai - Vises ingen
 * bilder i SVGene 14.mai - N�r man lager ny workout s� kan den ikke slettes med
 * en gang 14.mai - N�r man f�r sp�rsm�l om � slette exercise s� m� man liste
 * opp alle workouts som bruker exercisen 12.mai - Check update mangler ikon
 * 12.mai - vindustittel st�r p� engelsk 12.mai - Rediger menyen m� bli gr�et ut
 * n�r det ikke er valg 12.mai - Det st�r "Muskel: Shoulder" i pulldown 12.mai -
 * Fjerne Check update i meny 12.mai - Meny er p� engelsk 12.april - Programmer
 * m� velge spr�k basert p� OS innstillinger 12.april - Statusdialogen er ikke
 * gr� helt nederst 12.april - Status graf er ikke riktig 12.april - DatePicker
 * st�tter ikke spr�k 12.april - YearSpinner kan skrive inn tekst 11.april -
 * Exercises m� bli default enabled n�r de blir opprettet 11.apil - Pulldown i
 * SetsTable st�tter ikke spr�k 11.april - SetsTable endrer ikke spr�k 7.april -
 * Lage spr�kvalg 5.april - Pulldown i SetsTable m� vise alfabetisk listen over
 * �velser 5.april - Combobox viser feil st�rrelse ved lukking 5.april -
 * ComboBox i sets m� kun kunne opp ved dobbelt klikk 3.april - �velser blir
 * ikke selectet i treet n�r man legger til �velse 3.april - Alle komponentene i
 * exerciseeditorpanel m� v�re resizable 3.april - H�yre juster slider for
 * muskel/skin 2.april - Notatfeltet skal fylle helt ned til bunn av programmet
 * 2.april - Kalender har feil st�rrelse 2.april - Graf oppdaterer seg ikke n�r
 * kalender dato g�r utover nytt �r 2.april - St�tte for kj�nn i �velser og
 * status dialoger 2.april - Fjerne vekt fra innstillinger 1.april - Fjerne vekt
 * fra innstillinger 1.april - M�nedskalender mister fokus p� dag n�r m�ned
 * endres 31.mars - � trykke nei p� bekreft sletting av �velse f�rte til
 * deselektering av �velse 31.mars - La til relevante ikoner p� bekreft vinduder
 * 31.mars - La til vindu tittel p� exerciseditor og innstillinger 31.mars -
 * Fjernet set icon 31.mars - La til tykkere �rslinje med semitransparent og
 * ikke sort 31.mars - TabbedPane m� bli n�yaktig like stor som SVG grafen
 * 31.mars - Trenings�kt tabellen mister selection n�r en exercise endres
 * 31.mars - Rettet bug med enabling 30.mars - Enabling/disabling av exercise m�
 * oppdateres umiddelbart 30.mars - Legge til bekreft slett dialog 30.mars - N�r
 * man legger til ny trenings�kt s� blir ikke den valgt 30.mars - Mouse over i
 * graf er p� engelsk. Tekst i mouseover er ikke riktig posisjonert 30.mars -
 * Tekst i exercise description wrapper p� ord 29.mars - Application Menu
 * handling has been disabled. St�tte for Leopard. 29.mars - La til baseline
 * alignment i Text i SVG. Rettet TextAnchor bug. 29.mars - La inn manglende
 * scroll komponenter for at BodyStatusDialog skulle se bra ut 29.mars - La inn
 * dragdrop av bilder fra browser og paste til droppableicon. Pluss auto resize
 * av bilder. La p� tykkere border. 29.mars - Fikset feil muscleID p� �vre rygg
 * 28.mars - Lage av/p� knapp for �velse i ExerciseEditorPanel for � skjule
 * ubrukte �velser 19.mars - M�l m� ha med bakgrunnsbilde 19.mars - Legge til
 * st�tte for � lage historikk av vekt 18.mars - N�r man sletter en �velse m�
 * editoren f� tomme felter 18.mars - Tree i ExerciseEditor oppdateres ikke
 * riktig ved bla sletting av �velse 18.mars - Tooltip for �velser er ikke satt
 * 18.mars - �velsesknapper m� bli uthevet n�r man klikker p� de 18.mars - Flytt
 * details knapen helt til h�yre 18.mars - Mangler ikoner p� �velser 17.mars -
 * �velse editor husker ikke hvor den var 17.mars - Legge til valg for kj�nn
 * 17.mars - Tabbed pane f�r ikke med seg n�r man legger til en �velse 17.mars -
 * Exception n�r en �velse er valgt i sets table og man sletter en �velse
 * 17.mars - � legge til exercise f�r feil muscleID 17.mars - Ved sletting av
 * �velse s� m� man f� beskjed om hvor mange �velser som bruker denne 17.mars -
 * Blir exception n�r en exercise endres 17.mars - Mouseover i SVG grafen virker
 * ikke 17.mars - Ikonbilde for �velse blir lik for alle! 17.mars - Legg inn
 * hovedknapper for muskel og fjern multitabber 17.mars - Fjerne lite
 * muskelbilde og muskel notater 17.mars - Skal grafene vises i SVG? 17.mars -
 * Graf viser kun ett �r! 9.mars - Legge inn spr�ktekster for tree leafs i
 * exerciseeditorpanel 9.mars - Lage ferdig alle female SVG bilder 9.mars -
 * Slider skal huske verdier fra forrige gang og oppdater bildet ogs� 9.mars -
 * Fjerne scrollbar fra muscle bildet DONE - F� med antall �velser i muskel DONE
 * - Legge inn taber for muskelgrupper DONE - Innstillinger m� f� med dato DONE
 * - Innstillinger m� f� med OK og cancel DONE - Legge inn muskelgruppe pulldown
 * DONE - Legge inn st�tte for muskelgrupper i �velser DONE - �velser m� f� med
 * OK og cancel DONE - Mangler oversettelse n�r det er tom med exercises DONE -
 * Dato er ikke spr�k DONE - M�nedsnavn i workout tabell er p� engelsk
 *
 * @author morten
 *
 */
public class TrainerApp extends JFrame implements OSXApplication, JournalListener, LocaleListener {

    private static final long serialVersionUID = 1L;
    private JournalPanel journalPanel;

    public TrainerApp() {
        getRootPane().putClientProperty("Window.style", "small");
        getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);
        setLocale(JournalHelper.getDefaultLocale());
        UIManager.put("OptionPane.yesButtonText", JournalHelper.getLanguage(getLocale(), "yes"));
        UIManager.put("OptionPane.noButtonText", JournalHelper.getLanguage(getLocale(), "no"));
        UIManager.put("OptionPane.cancelButtonText", JournalHelper.getLanguage(getLocale(), "cancel"));

        setLayout(new BorderLayout());
        journalPanel = new JournalPanel(new Journal());
        add(journalPanel.getToolbar(), BorderLayout.NORTH);
        add(journalPanel, BorderLayout.CENTER);
        setJMenuBar(journalPanel.getJournalMenu());

        if (Toolkit.getDefaultToolkit().getScreenSize().width <= 1280) {
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            setLocation(0, 0);
        } else {
            setSize(new Dimension(955, 600));
            setLocationRelativeTo(null);
        }

        registerForMacOSXEvents();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        quit();
                    }
                }
        );

    }

    public void localeChanged(Locale locale) {
        setLocale(locale);
        UIManager.put("OptionPane.yesButtonText", JournalHelper.getLanguage(getLocale(), "yes"));
        UIManager.put("OptionPane.noButtonText", JournalHelper.getLanguage(getLocale(), "no"));
        UIManager.put("OptionPane.cancelButtonText", JournalHelper.getLanguage(getLocale(), "cancel"));
        journalPanel.localeChanged(locale);
    }

    public void about() {
        journalPanel.about();
    }

    public void quit() {
        save();
        System.exit(0);
    }

    public void loadJournal(Journal journal) {
        org.jdesktop.layout.GroupLayout gl;
        journal.removeLocaleListener(journalPanel);
        journal.removeLocaleListener(this);
        journal.removeJournalListener(this);
        journal.removeJournalListener(journalPanel);
        journal.addJournalListener(this);
        journal.addJournalListener(journalPanel);
        journalPanel.loadJournal(journal);
        journal.fireJournalLoaded();
        localeChanged(journal.getLocale());
        journal.addLocaleListener(this);
        journal.addLocaleListener(journalPanel);
//		journalPanel.checkForUpdate( true );
    }

    public void save() {
//		final JournalProgressDialog progress = new JournalProgressDialog( this, JournalHelper.getLanguage( getLocale(), "progress.saving" ) );
        final JournalProgressDialog progress = new JournalProgressDialog(this, "");
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {

                    JournalManager manager = new JournalManager();
                    manager.addReadWriteListener(progress);

                    manager.save(journalPanel.getJournal());
                } catch (Exception e) {
                    progress.setText(e.getMessage());
                } finally {
                    progress.closeDialog();
                    setVisible(true);
                }
            }
        });

        t.start();
        progress.showDialog();
    }

    public void load(final File file) {
//		final JournalProgressDialog progress = new JournalProgressDialog( this, JournalHelper.getLanguage( getLocale(), "progress.loading" ) );
        final JournalProgressDialog progress = new JournalProgressDialog(this, "");
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {

                    JournalManager manager = new JournalManager();
                    manager.addReadWriteListener(progress);
                    if (!file.exists()) {
                        manager.createEmpty();
                    }
                    Journal j = manager.read(new FileInputStream(file));
                    loadJournal(j);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    progress.closeDialog();
                    setVisible(true);
                }
            }
        });

        t.start();
        progress.showDialog();
    }

    public void preferences() {
        journalPanel.preferences();
    }

    public void setOwnerText(Journal journal) {
        setTitle(JournalHelper.getLanguage(journal.getLocale(), "journal.ownertext", journal.getOwner()));
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
    public void loadFile(String file) {
    }

    /**
     * Generic registration with the Mac OS X application menu Checks the
     * platform, then attempts to register with the Apple EAWT See
     * OSXAdapter.java to see how this is done without directly referencing any
     * Apple APIs
     */
    public void registerForMacOSXEvents() {
        if (CrossPlatformUtilities.isMacOSX()) {
            try {
                // Generate and register the OSXAdapter, passing it a hash of all the methods we wish to
                // use as delegates for various com.apple.eawt.ApplicationListener methods
                OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("quit", (Class[]) null));
                OSXAdapter.setAboutHandler(this, getClass().getDeclaredMethod("about", (Class[]) null));
                OSXAdapter.setPreferencesHandler(this, getClass().getDeclaredMethod("preferences", (Class[]) null));
                OSXAdapter.setFileHandler(this, getClass().getDeclaredMethod("loadFile", new Class[]{String.class}));
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
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CrossPlatformUtilities.setCrossPlatformProperties();
                TrainerApp app = new TrainerApp();
                app.load(JournalManager.getJournalFile());
            }
        });
    }

}
