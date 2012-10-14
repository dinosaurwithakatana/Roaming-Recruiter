package com.dinosaurwithakatana.jobhackcareerbuilder;

/**
 * Custom location class. Uses double precision for latitude/longitude.
 * @author anjan
 *
 */
public class CBLocation {
	private double latitude;
	private double longitude;
	
	public CBLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
