package com.dinosaurwithakatana.jobhackcareerbuilder;

import com.actionbarsherlock.app.SherlockActivity;
import com.dinosaurwithakatana.jobhackcareerbuilder.LocationService.LocalBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends SherlockActivity {
	private LocationService mService;
	private boolean mBound = false;
	private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Main Created");
        
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	
    	Intent intent = new Intent(this, LocationService.class);
    	bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    	
    	Button button = (Button) findViewById(R.id.main_button);
    	
    	button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				TextView tv = (TextView) findViewById(R.id.main_text_view);
				tv.setText(mService.getLocation());
			}
    	});
    }
   
    @Override
    protected void onStop() {
    	super.onStop();
    	
    	if (mBound) {
    		unbindService(mConnection);
    		mBound = false;
    	}
    }
    
    // Handles the connection to the Android Service
    private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.d(TAG,"Service Connected");
			
			LocalBinder binder = (LocalBinder)service;
			mService = binder.getService();
			mBound = true;
		}
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Log.d(TAG, "Service Disconnected");
			
			mBound = false;
		}
    };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
