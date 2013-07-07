package org.laukvik.trainer.swing;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import org.laukvik.swing.locale.LocaleEnabled;
import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.trainer.ImageBadgeIcon;
import org.laukvik.trainer.anatomy.Human;
import org.laukvik.trainer.anatomy.muscle.Muscle;
import org.laukvik.trainer.muscle.MuscleComparator;

public class JournalHelper implements LocaleEnabled{

	public static ImageBadgeIcon getIcon( String filename ){
		return new ImageBadgeIcon( JournalHelper.class.getResource( "icons/" + filename ));
//		return  new javax.swing.ImageIcon( JournalHelper.class.getResource( "icons/" + filename ));
	}

	public static final Muscle [] getMuscles( Locale locale ){
		Muscle [] muscles = Human.BODY.listAllMuscles();
		for (Muscle m : muscles){
			m.setName( getLanguage( locale, m.getLatin().toLowerCase().replaceAll(" ", "." ) ) );
		}
		List<Muscle> list = new ArrayList<Muscle>( Arrays.asList(muscles) );
		Collections.sort( list, new MuscleComparator() );
		Muscle [] sorted = new Muscle[ list.size() ];
		list.toArray( sorted );		
		return sorted;
	}
	
    public static KeyStroke getKeystroke( int keyevent ){ 	
		return CrossPlatformUtilities.getKeystroke( keyevent );
    }
    
	public static String [] listMonths( Locale locale ) {
		String [] months = { 
				getBundle( locale ).getString( "month.0" ), getBundle( locale ).getString( "month.1" ), getBundle( locale ).getString( "month.2" ),
				getBundle( locale ).getString( "month.3" ), getBundle( locale ).getString( "month.4" ), getBundle( locale ).getString( "month.5" ),
				getBundle( locale ).getString( "month.6" ), getBundle( locale ).getString( "month.7" ), getBundle( locale ).getString( "month.8" ),
				getBundle( locale ).getString( "month.9" ), getBundle( locale ).getString( "month.10" ), getBundle( locale ).getString( "month.11" )
		};
		return months;
	}
	
	public static String [] listDays( Locale locale ) {
		String [] days = { 
				getBundle( locale ).getString( "day.0" ), getBundle( locale ).getString( "day.1" ), getBundle( locale ).getString( "day.2" ),
				getBundle( locale ).getString( "day.3" ), getBundle( locale ).getString( "day.4" ), getBundle( locale ).getString( "day.5" ),
				getBundle( locale ).getString( "day.6" ),
		};
		return days;
	}

	public static Icon getIcon() {
		return getIcon( "trainer.png" );
	}
	
	/**************************************************************************/
	public static Locale [] listAvailableLocale(){
		Locale [] ls = { new Locale("no","NO"), Locale.US };
		return ls;
	}
	
	public static Locale getDefaultLocale(){
		String lang = System.getProperty("user.language");
		for (Locale l : listAvailableLocale()){
			if (l.getISO3Language().equalsIgnoreCase( lang )){
				return l;
			}
		}
		return Locale.US;
	}
	
	
	public static String getLanguage( Locale locale, String key ){
		return getBundle( locale ).getString(key);
	}
	
	public static ResourceBundle getBundle( Locale locale ){
		return ResourceBundle.getBundle( "org.laukvik.trainer.swing.messages", locale );
	}

//	public static String getLanguage( Locale locale, String key, Object parameter ) {
//		Object [] o = new Object[ 1 ];
//		o[0] = parameter;
//		return getLanguage( locale, key, o );
//	}
	
	public static String getLanguage( Locale locale, String key, Object... params ) {
		MessageFormat formatter = new MessageFormat("");
		formatter.applyPattern( JournalHelper.getLanguage( locale, key) );
		return formatter.format( params );
	}

	/**
	 * Returns the Locale to use when we don't have guessed the users
	 * language or dont have support for it
	 * 
	 * @return
	 */
	public static Locale getDefaultUnknownLocale() {
		return Locale.US;
	}


}