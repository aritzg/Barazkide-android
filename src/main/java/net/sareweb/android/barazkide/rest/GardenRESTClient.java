package net.sareweb.android.barazkide.rest;

import java.util.List;

import org.springframework.http.HttpMethod;

import android.util.Log;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

public class GardenRESTClient extends LDRESTClient<Garden> {

	public GardenRESTClient(String emailAddress, String password) {
		super(emailAddress, password);
	}
	
	public List<Garden> getGardens(){
		String requestURL = getBaseURL() + "/get-gardens";
		Log.d(TAG, "requestURL " + requestURL);
		return getList(requestURL, HttpMethod.GET);
	}
	
	public List<Garden> getUserGardensFromDate(long ownerUserId, long date, boolean ascending, int blockSize){
		String requestURL = getBaseURL() + "/get-user-gardens-from-date";
		requestURL = addParamToRequestURL(requestURL, "owner-user-id", ownerUserId);
		requestURL = addParamToRequestURL(requestURL, "date", date);
		requestURL = addParamToRequestURL(requestURL, "ascending", ascending);
		requestURL = addParamToRequestURL(requestURL, "block-size", blockSize);
		Log.d(TAG, "requestURL " + requestURL);
		return getList(requestURL, HttpMethod.GET);
	}

	@Override
	public String getPorltetContext() {
		return "Barazkide-portlet";
	}

	@Override
	public String getModelName() {
		return "garden";
	}

}
