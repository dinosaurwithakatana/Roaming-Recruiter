package com.dinosaurwithakatana.roamingrecruiter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class EducationItemSelectedListener implements OnItemSelectedListener {
	private static final String TAG = "Roaming Recruiter " +EducationItemSelectedListener.class.getSimpleName();                                                                                                                  
	private String education;
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		education = arg0.getItemAtPosition(arg2).toString();
		Log.v(TAG,education);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
