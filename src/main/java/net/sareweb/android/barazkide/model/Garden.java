package net.sareweb.android.barazkide.model;

import net.sareweb.lifedroid.model.generic.LDObject;

public class Garden extends LDObject{

	private long gardenId;
	private String name;
	private String comment;
	private double lat;
	private double lng;
	
	
	public long getGardenId() {
		return gardenId;
	}
	public void setGardenId(long gardenId) {
		this.gardenId = gardenId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
}
