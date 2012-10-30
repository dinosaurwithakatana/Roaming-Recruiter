package com.dinosaurwithakatana.roamingrecruiter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;

public class GetLoginAcct extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String paramUsername = params[0].toString();
		String requestURL = "https://api.mongolab.com/api/1/databases/users/collections/Users?";
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("q", "{\"username\": \""+paramUsername+"\"}")); 
//		nameValuePairs.add(new BasicNameValuePair("q", "{\"username\": \"H\"}")); 
		nameValuePairs.add(new BasicNameValuePair("apiKey", "507989d2e4b0e9396b4a4a90"));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			String paramString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
			System.out.println(paramString);
			requestURL+=paramString;
			System.out.println(requestURL);
			HttpGet httpget = new HttpGet(requestURL);
			HttpResponse response;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println("Handling Response");
			InputStream in = entity.getContent();
			String json = convertStreamToString(in);
			System.out.println("Creating JSON object");
			JSONArray o = new JSONArray (json);
			System.out.println("This is it"+ o.toString(1));
			return o.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String convertStreamToString(InputStream inputStream) throws IOException {
		if (inputStream != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				inputStream.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

}