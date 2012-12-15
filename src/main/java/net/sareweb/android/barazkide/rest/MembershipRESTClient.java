package net.sareweb.android.barazkide.rest;

import java.util.List;

import net.sareweb.android.barazkide.model.Membership;
import net.sareweb.lifedroid.model.User;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

import org.springframework.http.HttpMethod;

import android.util.Log;

public class MembershipRESTClient extends LDRESTClient<Membership> {

	public MembershipRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}

	public Membership addMembership(long userId, long gardenId) {
		String requestURL = getBaseURL() + "/add-membership";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return run(requestURL, HttpMethod.POST);
	}

	public Membership addMembershipRequest(long userId, long gardenId) {
		String requestURL = getBaseURL() + "/add-membership-request";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return run(requestURL, HttpMethod.POST);
	}

	public Membership acceptMembershipRequest(long userId, long gardenId) {
		String requestURL = getBaseURL() + "/accept-membership-request";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return run(requestURL, HttpMethod.POST);
	}

	public Membership rejectMembershipRequest(long userId, long gardenId) {
		String requestURL = getBaseURL() + "/reject-membership-request";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return run(requestURL, HttpMethod.POST);
	}

	public boolean removeMembership(long userId, long gardenId) {
		String requestURL = getBaseURL() + "/remove-membership";
		requestURL = addParamToRequestURL(requestURL, "user-id", userId);
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		Log.d(TAG, "requestURL " + requestURL);
		return runForBoolean(requestURL, HttpMethod.GET);
	}

	public List<User> findMemberUsers(long gardenId) {
		String requestURL = getBaseURL() + "/find-member-users";
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		Log.d(TAG, "requestURL " + requestURL);
		return (List<User>) getList(requestURL, HttpMethod.GET, User.class);
	}

	public String getPorltetContext() {
		return "Barazkide-portlet";
	}

	@Override
	public String getModelName() {
		return "membership";
	}

}
