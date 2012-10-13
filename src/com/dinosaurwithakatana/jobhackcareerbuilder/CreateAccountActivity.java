/**
 * 
 */
package com.dinosaurwithakatana.jobhackcareerbuilder;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author vishnu
 *
 */
public class CreateAccountActivity extends SherlockActivity {
	private static final String TAG = CreateAccountActivity.class.getSimpleName();                                                                                                                  


	/**
	 * Creates the activity
	 */

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        
        Button createAccount = (Button)findViewById(R.id.btnCreate);
        createAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.v(TAG,"create clicked");
				Toast toast = Toast.makeText(getApplicationContext(), "You just clicked create!"  , Toast.LENGTH_SHORT);                                                       
				toast.show();
		
			}
		});
    }
}
