package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Event extends LDObject{

	private long eventId;
	private long gardenId;
	private long creatorUserId;
	private long destinationUserId;
	private long gardenImageId;
	private long createDate;
	private String eventType;
	private String eventText;
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getGardenId() {
		return gardenId;
	}
	public void setGardenId(long gardenId) {
		this.gardenId = gardenId;
	}
	public long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public long getDestinationUserId() {
		return destinationUserId;
	}
	public void setDestinationUserId(long destinationUserId) {
		this.destinationUserId = destinationUserId;
	}
	public long getGardenImageId() {
		return gardenImageId;
	}
	public void setGardenImageId(long gardenImageId) {
		this.gardenImageId = gardenImageId;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventText() {
		return eventText;
	}
	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

}
