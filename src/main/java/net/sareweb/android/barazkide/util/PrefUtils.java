package net.sareweb.android.barazkide.util;

public class PrefUtils {
	
	public static boolean isUserLogged(BarazkidePrefs_ prefs){
		if(prefs.user().getOr("").equals(""))return false;
		return true;
	}

}
