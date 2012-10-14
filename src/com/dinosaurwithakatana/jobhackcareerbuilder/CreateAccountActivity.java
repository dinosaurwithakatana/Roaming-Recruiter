/**
 * 
 */
package com.dinosaurwithakatana.jobhackcareerbuilder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * @author vishnu
 *
 */
public class CreateAccountActivity extends SherlockFragmentActivity implements TabListener {


	/**
	 * Displays each section
	 */
	public static class CreateAcctFragment extends Fragment {
		public CreateAcctFragment() {
		}

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			Bundle args = getArguments();
			int screen = args.getInt(ARG_SECTION_NUMBER);
			switch (screen){
				case 1: return inflater.inflate(R.layout.loginfragment, container, false);
				case 2: return inflater.inflate(R.layout.personal_fragment, container, false);
				case 3: {
					
					return inflater.inflate(R.layout.experience_fragment, container, false);
				}
			}
			return null;
		}
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter{

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new CreateAcctFragment();
			Bundle args = new Bundle();
			args.putInt(CreateAcctFragment.ARG_SECTION_NUMBER, i + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0: return getString(R.string.tab_login_info).toUpperCase();
			case 1: return getString(R.string.tab_p_info).toUpperCase();
			case 2: return getString(R.string.tab_experience).toUpperCase();
			}
			return null;
		}
	}

	private static final String TAG = CreateAccountActivity.class.getSimpleName();                                                                                                                  
	private String usernameInput, passwordInput, confirmPasswordInput, fNameInput, mNameInput, lNameInput;
	protected String yrOfExperienceInput;
	private static Spinner spnrEducation;
	private static OnItemSelectedListener spnrListener;
	private String educationLevel;
	private static boolean DEBUG = true;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	 * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
	 * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
	 * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	// Create the adapter that will return a fragment for each of the three primary sections
	// of the app.
	/**
	 * Creates the activity
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding tab.
		// We can also use ActionBar.Tab#select() to do this if we have a reference to the
		// Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by the adapter.
			// Also specify this Activity object, which implements the TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(
					actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		final EditText username = (EditText)findViewById(R.id.txtCreateUsername);
		final EditText password = (EditText)findViewById(R.id.txtCreatePassword);
		final EditText confirmPassword = (EditText)findViewById(R.id.txtConfirmCreatePassword);
		final EditText fName = (EditText) findViewById(R.id.txtFirstName);
		final EditText mName = (EditText) findViewById(R.id.txtMiddleName);
		final EditText lName = (EditText) findViewById(R.id.txtLastName);
		final EditText yrOfExperience = (EditText) findViewById(R.id.txtExperienceInput);
		spnrEducation = (Spinner) findViewById(R.id.spnrEducation);
		
		
		
		username.setText("lol");
		password.setText("lol");
		confirmPassword.setText("lol");
		fName.setText("fname");
		mName.setText("poop");
		lName.setText("mahhhh");
		yrOfExperience.setText("4");
		
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
		spnrListener = new CustomOnItemSelectedListener();
		spnrEducation.setOnItemSelectedListener(spnrListener);
		Log.v(TAG,"Spinner listener added");
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}