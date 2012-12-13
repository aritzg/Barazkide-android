package net.sareweb.android.barazkide.rest;

import net.sareweb.android.barazkide.model.Following;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

import org.springframework.http.HttpMethod;

import android.util.Log;

public class FollowingRESTClient extends LDRESTClient<Following> {

	public FollowingRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}
	
	public Following addFollowing(long userId, long gardenId){
		String requestURL = getBaseURL() + "/add-following";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return run(requestURL, HttpMethod.POST);
	}
	
	public boolean removeFollowing(long userId, long gardenId){
		String requestURL = getBaseURL() + "/remove-following";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		Log.d(TAG, "requestURL " + requestURL);
		return runForBoolean(requestURL, HttpMethod.GET);
	}
	
	
	public String getPorltetContext() {
		return "Barazkide-portlet";
	}

	@Override
	public String getModelName() {
		return "following";
	}

}
