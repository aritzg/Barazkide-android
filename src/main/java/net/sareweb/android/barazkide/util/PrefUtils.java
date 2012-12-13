package net.sareweb.android.barazkide.util;

public class PrefUtils {
	
	public static boolean isUserLogged(BarazkidePrefs_ prefs){
		if(prefs.user().getOr("").equals(""))return false;
		return true;
	}
	
	public static void clearUserPrefs(BarazkidePrefs_ prefs){
		prefs.user().put("");
		prefs.userId().put(0);
		prefs.pass().put("");
	}

}
