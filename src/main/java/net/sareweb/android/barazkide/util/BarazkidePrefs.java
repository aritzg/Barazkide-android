package net.sareweb.android.barazkide.util;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value=Scope.UNIQUE)
public interface BarazkidePrefs {
	long userId();
	String user();
	String pass();
}
