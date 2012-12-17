package net.sareweb.android.barazkide.rest;

import net.sareweb.android.barazkide.model.Rating;
import net.sareweb.lifedroid.rest.generic.LDRESTClient;

public class RatingRESTClient extends LDRESTClient<Rating> {

	public RatingRESTClient(BarazkideConnectionData connectionData) {
		super(connectionData);
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
