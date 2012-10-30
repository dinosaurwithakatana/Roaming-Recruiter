package com.dinosaurwithakatana.roamingrecruiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import android.os.AsyncTask;

/**
 * This asynchronous task runs as a background thread process.
 * It calls CareerBuilder's RESTful API by executing an HTTP GET request.
 * The result is the XML response returned from CareerBuilder's servers.
 * @author anjan
 *
 */
public class QueryJobsTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL(params[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			String output;
			StringBuilder response = new StringBuilder();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			br.close();

			conn.disconnect();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
