package com.dinosaurwithakatana.roamingrecruiter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class PostNewAcct extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL("https://api.mongolab.com/api/1/databases/users/collections/Users?apiKey=507989d2e4b0e9396b4a4a90");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type","application/json");
			
			OutputStream out = conn.getOutputStream();
			
			out.write(params[0].getBytes());
			out.flush();
		
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
	 
			String output;
			String response="";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				response += output;
			}
	 
			conn.disconnect();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
