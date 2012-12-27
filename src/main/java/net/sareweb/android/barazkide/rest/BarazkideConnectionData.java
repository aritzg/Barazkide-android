package net.sareweb.android.barazkide.rest;

import net.sareweb.android.barazkide.util.BarazkidePrefs_;
import net.sareweb.android.barazkide.util.Constants;
import net.sareweb.lifedroid.rest.ConnectionData;

public class BarazkideConnectionData extends ConnectionData {

	private String user=null;
	private String pass=null;
	private String defaultUser=Constants.DEFAULT_USER;
	private String defaultPass=Constants.DEFAULT_PASS;
	
	public BarazkideConnectionData(BarazkidePrefs_ prefs){
		user = prefs.user().get();
		if(user==null || "".equals(user))user=defaultUser;
		pass = prefs.pass().get();
		if(pass==null || "".equals(pass))pass=defaultPass;
	}
	
	@Override
	public String getProtocol() {
		return "http";
	}

	@Override
	public String getServerURL() {
		return Constants.SERVICE_URL;
	}

	@Override
	public String getPort() {
		return String.valueOf(Constants.SERVICE_PORT);
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPass() {
		return pass;
	}

	@Override
	public String getDefaultUser() {
		return defaultUser;
	}

	@Override
	public String getDefaultPass() {
		return defaultPass;
	}

	@Override
	public String getCompanyId() {
		return "10154";
	}

}
