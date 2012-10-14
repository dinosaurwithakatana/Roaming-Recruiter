package com.dinosaurwithakatana.jobhackcareerbuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class XMLParser {
	private static final String namespace = null;
	private static final String CAREER_BUILDER_HEADER = "ResponseJobSearch";
	private static final String TAG = "XMLParser";
	
	/**
	 * Parses XML string, and returns a list of Jobs.
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public List<Job> parse(String xml) throws XmlPullParserException, IOException {
		Log.d(TAG, "Parse begun");
		Log.d(TAG, "Size of XML String: " + xml.length());
		Log.d(TAG, "String contents : " + xml.substring(0, 256) + "...");
		InputStream in = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(false);
		    XmlPullParser parser = factory.newPullParser(); 
		    in = new ByteArrayInputStream(xml.getBytes());
		    parser.setInput(in, null);
		    parser.nextTag();
		    return readJobSearch(parser);
		} finally {
			in.close();
		}
	}
	
	private List<Job> readJobSearch(XmlPullParser parser) throws XmlPullParserException, IOException {
		Log.d(TAG, "Reading Job Search");
		List<Job> jobs = new ArrayList<Job>();
		
		parser.require(XmlPullParser.START_TAG, namespace, CAREER_BUILDER_HEADER);
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			if (parser.getName() != null) {
				if (parser.getName().equals("JobSearchResult"))
					jobs.add(readJob(parser));
			} else 
				continue;
		}
		return jobs;
	}
	
	/**
	 * Reads individual job.
	 * @param parser
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private Job readJob(XmlPullParser parser) throws XmlPullParserException, IOException {
		Log.d(TAG, "Reading Job");
        Job currentJob = new Job();
        double latitude = 0.0, longitude = 0.0;
        
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name == null)
            	continue;
            if (name.equals("Company")) {
                currentJob.setCompany(readText(parser));
            } else if (name.equals("DID")) {
            	currentJob.setDID(readText(parser));
            } else if (name.equals("DescriptionTeaser")) {
            	currentJob.setJobDescription(readText(parser));
            } else if (name.equals("OnetCode")) { 
            	currentJob.setONetCode(readText(parser));
            } else if (name.equals("EmploymentType")) {
            	currentJob.setEmploymentType(readText(parser));
            } else if (name.equals("Location")) {
            	currentJob.setLocationFriendly(readText(parser));
            } else if (name.equals("LocationLatitude")) {
            	latitude = Double.parseDouble(readText(parser));
            } else if (name.equals("LocationLongitude")) {
            	longitude = Double.parseDouble(readText(parser));
            } else {
                skip(parser);
            }
        }
        currentJob.setLocationLatLong(new CBLocation(latitude,longitude));
        
        return currentJob; 
	}
		
	/**
	 * Used to read the text in between tags.
	 * @param parser
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
		Log.d(TAG, "Reading text");
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
	}
	
	/**
	 * Skips tags the parser isn't interested in.
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                    depth--;
                    break;
            case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
