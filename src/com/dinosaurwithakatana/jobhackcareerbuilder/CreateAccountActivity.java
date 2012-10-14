/**
 * 
 */
package com.dinosaurwithakatana.jobhackcareerbuilder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

/**
 * @author vishnu
 *
 */
public class CreateAccountActivity extends SherlockActivity {
	private static final String TAG = CreateAccountActivity.class.getSimpleName();                                                                                                                  
	private String usernameInput, passwordInput, confirmPasswordInput, fNameInput, mNameInput, lNameInput;
	protected String yrOfExperienceInput;
	private Spinner spnrEducation;
	private OnItemSelectedListener spnrListener;
	private String educationLevel;
	private static boolean DEBUG = true;

	/**
	 * Creates the activity
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);

		final EditText username = (EditText)findViewById(R.id.txtCreateUsername);
		final EditText password = (EditText)findViewById(R.id.txtCreatePassword);
		final EditText confirmPassword = (EditText)findViewById(R.id.txtConfirmCreatePassword);
		final EditText fName = (EditText) findViewById(R.id.txtFirstName);
		final EditText mName = (EditText) findViewById(R.id.txtMiddleName);
		final EditText lName = (EditText) findViewById(R.id.txtLastName);
		final EditText yrOfExperience = (EditText) findViewById(R.id.txtExperienceInput);
		
		username.setText("lol");
		password.setText("lol");
		confirmPassword.setText("lol");
		fName.setText("fname");
		mName.setText("poop");
		lName.setText("mahhhh");
		yrOfExperience.setText("4");
		spnrEducation = (Spinner)findViewById(R.id.spnrEducation);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.education_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spnrEducation.setAdapter(adapter);
		addListenerOnSpinnerItemSelection();
		Button createAccount = (Button)findViewById(R.id.btnCreate);
		createAccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(DEBUG){
					Log.v(TAG,"create clicked");
					Toast toast = Toast.makeText(getApplicationContext(), "You just clicked create!"  , Toast.LENGTH_SHORT);                                                       
					toast.show();
				}

				usernameInput = username.getText().toString();
				passwordInput = password.getText().toString();
				confirmPasswordInput = confirmPassword.getText().toString();
				fNameInput = fName.getText().toString();
				mNameInput = mName.getText().toString();
				lNameInput = lName.getText().toString();
				yrOfExperienceInput = yrOfExperience.getText().toString();
				educationLevel = ((CustomOnItemSelectedListener) spnrListener).getEducation();
				Log.v(TAG,educationLevel);

				if(passwordInput.equals(confirmPasswordInput)){
					JSONObject user = new JSONObject();
					try{
						user.put("username",usernameInput);
						user.put("password",passwordInput);
						user.put("f_name",fNameInput);
						user.put("m_name",mNameInput);
						user.put("l_name",lNameInput);
						user.put("Years of Experience",yrOfExperienceInput);
						user.put("Education",educationLevel);
					} catch (JSONException e){
						e.printStackTrace();
					}
					new PostNewAcct().execute(user.toString());
				}

				Intent i = new Intent(CreateAccountActivity.this,LoginActivity.class);
				startActivity(i);

			}
		});
	}

	public void addListenerOnSpinnerItemSelection() {
		spnrEducation = (Spinner) findViewById(R.id.spnrEducation);
		spnrListener = new CustomOnItemSelectedListener();
		spnrEducation.setOnItemSelectedListener(spnrListener);
		Log.v(TAG,"Spinner listener added");
	}
}
