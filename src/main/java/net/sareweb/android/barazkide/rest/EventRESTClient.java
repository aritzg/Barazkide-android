package net.sareweb.android.barazkide.rest;

import java.util.List;

import org.springframework.http.HttpMethod;

import android.util.Log;

import net.sareweb.android.barazkide.model.Event;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

public class EventRESTClient extends LDRESTClient<Event> {

	public EventRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}
	
	public List<Event> findEventsInFollowedGardensOlderThanDate(long userId, long followingDate, int blockSize){
		String requestURL = getBaseURL() + "/find-events-in-followed-gardens-older-than-date";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "following-date", followingDate);
		requestURL = addParamToRequestURL(requestURL, "block-size", blockSize);
		Log.d(TAG, "requestURL " + requestURL);
		return getList(requestURL, HttpMethod.GET);
	}
	
	public List<Event> findEventsInFollowedGardensNewerThanDate(long userId, long followingDate, int blockSize){
		String requestURL = getBaseURL() + "/find-events-in-followed-gardens-newer-than-date";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "following-date", followingDate);
		requestURL = addParamToRequestURL(requestURL, "block-size", blockSize);
		Log.d(TAG, "requestURL " + requestURL);
		return getList(requestURL, HttpMethod.GET);
	}
	
	public List<Event> findEventsInGardenOlderThanDate(long userId, long eventDate, int blockSize){
		String requestURL = getBaseURL() + "/find-events-in-garden-older-than-date";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "event-date", eventDate);
		requestURL = addParamToRequestURL(requestURL, "block-size", blockSize);
		Log.d(TAG, "requestURL " + requestURL);
		return getList(requestURL, HttpMethod.GET);
	}
	
	public List<Event> findEventsInGardenNewerThanDate(long userId, long eventDate, int blockSize){
		String requestURL = getBaseURL() + "/find-events-in-garden-newer-than-date";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "event-date", eventDate);
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
		// TODO Auto-generated method stub
		return "event";
	}

}
