package net.sareweb.android.barazkide.rest;

import java.util.List;

import org.springframework.http.HttpMethod;

import android.util.Log;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

public class GardenRESTClient extends LDRESTClient<Garden> {

	public GardenRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}
	
	public Garden addGarden(String name, String comment, double lat, double lng, long gardenImageId){
		String requestURL = getBaseURL() + "/add-garden";
		requestURL = addParamToRequestURL(requestURL, "name", name, true);
		requestURL = addParamToRequestURL(requestURL, "comment", comment, true);
		requestURL = addParamToRequestURL(requestURL, "lat", lat);
		requestURL = addParamToRequestURL(requestURL, "lng", lng);
		requestURL = addParamToRequestURL(requestURL, "gardenImageId", gardenImageId);
		return run(requestURL, HttpMethod.POST);
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
