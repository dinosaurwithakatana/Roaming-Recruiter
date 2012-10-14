package com.dinosaurwithakatana.jobhackcareerbuilder;

import com.actionbarsherlock.app.SherlockMapActivity;
import com.dinosaurwithakatana.jobhackcareerbuilder.LocationService.LocalBinder;
import com.google.android.maps.MapView;

import android.os.*;
import android.content.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends SherlockMapActivity {
	private LocationService mService;
	private LocalConfiguration mConfiguration;
	private boolean mBound = false;
	private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Main Created");
        
        setContentView(R.layout.activity_main);
        mConfiguration = new LocalConfiguration();
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	Log.d(TAG, "STARTED");
    	
    	Intent intent = new Intent(this, LocationService.class);
    	intent.putExtra("LocalConfiguration", mConfiguration);
    	bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    	
    	Button button = (Button) findViewById(R.id.main_button);
    	
    	button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Update Map
				Log.d(TAG, "Button click");
				TextView tv = (TextView) findViewById(R.id.main_text);
				tv.setText(mService.getJobs());
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
    

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
