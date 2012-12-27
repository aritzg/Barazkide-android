package net.sareweb.android.barazkide.rest;

import java.util.List;

import net.sareweb.android.barazkide.model.Badge;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

import org.springframework.http.HttpMethod;

public class BadgeRESTClient extends LDRESTClient<Badge> {

	public BadgeRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
	}

	public List<Badge> getGardenBadges(long gardenId) {
		String requestURL = getBaseURL() + "/get-garden-badges";
		requestURL = addParamToRequestURL(requestURL, "garden-id", gardenId);
		return getList(requestURL, HttpMethod.GET);
	}

	@Override
	public String getPorltetContext() {
		return "Barazkide-portlet";
	}

	@Override
	public String getModelName() {
		return "badge";
	}

}
