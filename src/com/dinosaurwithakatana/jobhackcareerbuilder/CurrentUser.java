package com.dinosaurwithakatana.jobhackcareerbuilder;

/**
 * Represents the currently logged in Job seeker.
 * All elements are static, as there can only be one user logged in at a time.
 * @author anjan
 *
 */
public class CurrentUser {
	public static String sUsername;
	public static String sFirstName;
	public static String sLastName;
	public static int sExperience;
	public static String sEducation; // Career Builder Education Code
	public static String sSOCCode; // Standard Occupational Classification code
}