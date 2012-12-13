package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Membership extends LDObject{

	private long membershipId;
	private long userId;
	private long gardenId;
	private long membershipDate;
	public long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(long membershipId) {
		this.membershipId = membershipId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGardenId() {
		return gardenId;
	}
	public void setGardenId(long gardenId) {
		this.gardenId = gardenId;
	}
	public long getMembershipDate() {
		return membershipDate;
	}
	public void setMembershipDate(long membershipDate) {
		this.membershipDate = membershipDate;
	}
}
