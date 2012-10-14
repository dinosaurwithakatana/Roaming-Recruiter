package com.dinosaurwithakatana.jobhackcareerbuilder;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Service that polls the phone's current location and queries CareerBuilder jobs.
 * @author anjan
 *
 */
public class LocationService extends Service {
	private LocationManager mLocationManager;
	private final IBinder mBinder = new LocalBinder();
	private static final String TAG = LocationService.class.getSimpleName();
	
	public String getLocation() {
		return "LOCATION FROM SERVICE";
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	@Override
	public void onCreate() {
		Log.d(TAG,"CREATED");
		
		mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		final boolean gpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if (!gpsEnabled) {
			Log.d(TAG, "GPS Not Enabled");
			// TODO: Build alert dialog
			enableLocationSettings();
		}
	}

	private void enableLocationSettings() {
    	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    	startActivity(intent);
    }
	
	public class LocalBinder extends Binder {
		LocationService getService() {
			return LocationService.this;
		}
	}
}