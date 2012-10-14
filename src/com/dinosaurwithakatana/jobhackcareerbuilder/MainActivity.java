package com.dinosaurwithakatana.jobhackcareerbuilder;

import java.util.List;

import com.actionbarsherlock.app.SherlockMapActivity;
import com.dinosaurwithakatana.jobhackcareerbuilder.LocationService.LocalBinder;
import com.google.android.maps.MapView;

import android.os.*;
import android.provider.Settings;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.support.v4.app.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends SherlockMapActivity {
	private LocationService mService;
	private LocalConfiguration mConfiguration;
	private boolean mBound = false;
	protected int mId;
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
				if(!mService.isIs_gpsEnabled()){
					NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
					        .setSmallIcon(R.drawable.ic_launcher)
					        .setContentTitle("My notification")
					        .setContentText("Hello World!");
					
					Intent resultIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS );
					
					// The stack builder object will contain an artificial back stack for the
					// started Activity.
					// This ensures that navigating backward from the Activity leads out of
					// your application to the Home screen.
					TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
					// Adds the back stack for the Intent (but not the Intent itself)
//					stackBuilder.addParentStack(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					// Adds the Intent that starts the Activity to the top of the stack
					stackBuilder.addNextIntent(resultIntent);
					PendingIntent resultPendingIntent =
					        stackBuilder.getPendingIntent(
					            0,
					            PendingIntent.FLAG_UPDATE_CURRENT
					        );
					mBuilder.setContentIntent(resultPendingIntent);
					NotificationManager mNotificationManager =
					    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
					// mId allows you to update the notification later on.
					mNotificationManager.notify(mId, mBuilder.build());
				}
				// Update Map
				Log.d(TAG, "Button click");
				TextView tv = (TextView) findViewById(R.id.main_text);
				List<Job> jobs = mService.getJobs();
				
				Log.d(TAG, "Number of Jobs: " + jobs.size());
				StringBuilder sb = new StringBuilder();
				for (Job job : jobs) {
					sb.append(job.getCompany()).append("--\n");
				}
				
				tv.setText(sb.toString());
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
