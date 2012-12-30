package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Badge extends LDObject{

	private long badgeId;
	private String badgeType;
	private String badgeTextKey;
	private String badgeImageUrl;
	private long createDate;
	public long getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(long badgeId) {
		this.badgeId = badgeId;
	}
	public String getBadgeType() {
		return badgeType;
	}
	public void setBadgeType(String badgeType) {
		this.badgeType = badgeType;
	}
	public String getBadgeTextKey() {
		return badgeTextKey;
	}
	public void setBadgeTextKey(String badgeTextKey) {
		this.badgeTextKey = badgeTextKey;
	}
	public String getBadgeImageUrl() {
		return badgeImageUrl;
	}
	public void setBadgeImageUrl(String badgeImageUrl) {
		this.badgeImageUrl = badgeImageUrl;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	
}
