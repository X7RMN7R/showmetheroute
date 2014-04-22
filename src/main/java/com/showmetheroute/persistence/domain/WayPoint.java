package com.showmetheroute.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "waypoint")
public class WayPoint {
	@Id
	private String id;

	@Field("latitude")
	@Indexed
	private double latitude;

	@Field("longitude")
	@Indexed
	private double longitude;
	
	public String getId() {
	  return this.id;
	}

	public double getLatitude() {
	  return this.latitude;
	}
	
	public double getLongitude() {
	  return this.longitude;
	}
	
	public void setId(String id) {
	  this.id = id;
	}

	public void setLatitude(double latitude) {
	  this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
	  this.longitude = longitude;
	}
}
