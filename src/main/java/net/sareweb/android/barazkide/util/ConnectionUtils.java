package net.sareweb.android.barazkide.util;

import net.sareweb.android.barazkide.rest.BarazkideConnectionData;
import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionUtils {

	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		return ((cm.getActiveNetworkInfo() != null) && cm
				.getActiveNetworkInfo().isConnectedOrConnecting());
	}
	
	/*public static BarazkideConnectionData initBarazkideConnectionData(BarazkidePrefs_ prefs){
		return new BarazkideConnectionData(prefs);
	}*/

}
