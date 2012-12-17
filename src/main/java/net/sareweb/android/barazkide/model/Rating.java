package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Rating extends LDObject{

	private long ratingId;
	private long userId;
	private String rateType;
	private long ratedObjectId;
	private long ratingDate;
	private double rate;
	
	public long getRatingId() {
		return ratingId;
	}
	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public long getRatedObjectId() {
		return ratedObjectId;
	}
	public void setRatedObjectId(long ratedObjectId) {
		this.ratedObjectId = ratedObjectId;
	}
	public long getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(long ratingDate) {
		this.ratingDate = ratingDate;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
