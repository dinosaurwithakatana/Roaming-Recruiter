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
	
	/**
	 * Takes a double coordinate, and varies it ever so slightly by randomly
	 * changing the last two significant figures.
	 * This arbitrary change will help deal with duplicate coordinates, so that they
	 * will be shown very close to each other on the map, but not on top of one another.
	 * @param coordinate
	 * @return
	 */
	public static double addVariation(double coordinate) {
		return coordinate + (0.0001 * (-100 + (200 * Math.random())));
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
