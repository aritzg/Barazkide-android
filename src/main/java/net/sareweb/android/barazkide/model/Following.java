package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Following extends LDObject{

	private long followingId;
	private long userId;
	private long gardenId;
	private long followingDate;
	
	public long getFollowingId() {
		return followingId;
	}
	public void setFollowingId(long followingId) {
		this.followingId = followingId;
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
	public long getFollowingDate() {
		return followingDate;
	}
	public void setFollowingDate(long followingDate) {
		this.followingDate = followingDate;
	}
	
}
