package com.dinosaurwithakatana.jobhackcareerbuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

/**
 * Represents the currently logged in Job seeker.
 * All elements are static, as there can only be one user logged in at a time.
 * @author anjan
 *
 */
public class CurrentUser {
	public static String sUsername;
	public static String sResume;
	public static String sFirstName;
	public static String sLastName;
	public static int sExperience;
	public static String sEducation; // Career Builder Education Code
	public static String sSOCCode; // Standard Occupational Classification code
	public static String currentDID;
	public static List<Job> jobs;
	
	private static final String DEVELOPER_KEY = "WDHF0HJ60FHRP1N7XQ2K";
	
	public static String sendApplication() throws Exception {
		String xml = generateXmlRequest();
		String response = new PostJobApplication().execute(xml).get();
		return response;
	}
	
	private static String generateXmlRequest() {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<RequestApplication>");
		xml.append("<DeveloperKey>");
		xml.append(DEVELOPER_KEY);
		xml.append("</DeveloperKey>");
		xml.append("<JobDID>").append(currentDID).append("</JobDID>");
		xml.append("<Test>true</Test>");
		xml.append("<SiteID />");
		xml.append("<CoBrand />");
		xml.append("<Responses>");
		
		xml.append("<Response>");
		xml.append("<QuestionID>").append("ApplicantName").append("</QuestionID>");
	    xml.append("<ResponseText>").append(sFirstName + " " + sLastName).append("</ResponseText>");
	    xml.append("</Response>");
	    
	    xml.append("<Response>");
		xml.append("<QuestionID>").append("ApplicantEmail").append("</QuestionID>");
	    xml.append("<ResponseText>").append("user@email.com").append("</ResponseText>");
	    xml.append("</Response>");
	    
	    xml.append("<Response>");
		xml.append("<QuestionID>").append("Resume").append("</QuestionID>");
	    xml.append("<ResponseText>").append(sResume).append("</ResponseText>");
	    xml.append("</Response>");
	   
	    xml.append("<Response>");
		xml.append("<QuestionID>").append("Meets Requirements").append("</QuestionID>");
	    xml.append("<ResponseText>").append("Yes").append("</ResponseText>");
	    xml.append("</Response>");
	    
	    xml.append("<Response><QuestionID>104498</QuestionID><ResponseText>263315</ResponseText></Response><Response><QuestionID>107812</QuestionID><ResponseText>270970</ResponseText></Response>");
	    
		xml.append("</Responses>");
		xml.append("</RequestApplication>");
		
		return xml.toString();
	}
}