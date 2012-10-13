package com.dinosaurwithakatana.jobhackcareerbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;

public class LoginActivity extends SherlockActivity{
	private static final String TAG = CreateAccountActivity.class.getSimpleName();                                                                                                                  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        Button createAccountButton = (Button)findViewById(R.id.btnCreateAcct);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LoginActivity.this, CreateAccountActivity.class);
				startActivity(i);
				
			}
		});
    }
}
