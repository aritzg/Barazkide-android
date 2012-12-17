package net.sareweb.android.barazkide.rest;

import net.sareweb.android.barazkide.model.Garden;
import net.sareweb.android.barazkide.model.Rating;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

import org.springframework.http.HttpMethod;

import android.util.Log;

public class RatingRESTClient extends LDRESTClient<Rating> {

	public RatingRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}
	
	public Rating addRating(long userId, String rateType, long ratedObjectId, double rate){
		String requestURL = getBaseURL() + "/add-rating";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "rate-type", rateType);
		requestURL = addParamToRequestURL(requestURL, "rated-object-id", ratedObjectId);
		requestURL = addParamToRequestURL(requestURL, "rate", rate);
		return run(requestURL, HttpMethod.POST);
	}
	
	public double getAvgRating(long ratedObjectId){
		String requestURL = getBaseURL() + "/get-avg-rating";
		requestURL = addParamToRequestURL(requestURL, "rated-object-id", ratedObjectId);
		Log.d(TAG, "requestURL " + requestURL);
		return runForDouble(requestURL, HttpMethod.GET);
	}

	@Override
	public String getPorltetContext() {
		return "Barazkide-portlet";
	}

	@Override
	public String getModelName() {
		return "rating";
	}

}
