package com.dinosaurwithakatana.jobhackcareerbuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class PostJobApplication extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) { 
		try {
			URL url = new URL("http://api.careerbuilder.com/v1/application/submit");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			
			OutputStream out = conn.getOutputStream();
	
			out.write(params[0].getBytes());
			out.flush();
	
	
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			String output;
			StringBuilder response = new StringBuilder();
			while ((output = br.readLine()) != null) {
					response.append(output);
			}
			
			Log.d("ApplyJob", response.toString());
			
			br.close();
	
			conn.disconnect();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
