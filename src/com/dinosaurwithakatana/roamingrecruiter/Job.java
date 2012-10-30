package com.dinosaurwithakatana.roamingrecruiter;

import java.util.Date;

/**
 * Represents a CareerBuilder Job.
 * @author anjan
 *
 */
public class Job {
	private String company, DID;
	private String ONetCode;
	private String jobTitle;
	private String jobDescription;
	private String employmentType;
	private CBLocation locationLatLong;
	private String locationFriendly;
	private Date jobPosted;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDID() {
		return DID;
	}
	public void setDID(String dID) {
		DID = dID;
	}
	public String getONetCode() {
		return ONetCode;
	}
	public void setONetCode(String ONetCode) {
		this.ONetCode = ONetCode;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public CBLocation getLocationLatLong() {
		return locationLatLong;
	}
	public void setLocationLatLong(CBLocation locationLatLong) {
		this.locationLatLong = locationLatLong;
	}
	public String getLocationFriendly() {
		return locationFriendly;
	}
	public void setLocationFriendly(String locationFriendly) {
		this.locationFriendly = locationFriendly;
	}
	public Date getJobPosted() {
		return jobPosted;
	}
	public void setJobPosted(Date jobPosted) {
		this.jobPosted = jobPosted;
	}
}
