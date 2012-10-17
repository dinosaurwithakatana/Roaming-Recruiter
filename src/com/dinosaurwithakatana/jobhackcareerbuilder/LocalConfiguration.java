package com.dinosaurwithakatana.jobhackcareerbuilder;

import android.os.Parcel;
import android.os.Parcelable;
import com.dinosaurwithakatana.roamingrecruiter.R;

/**
 * Configuration that specifies the settings for the client.
 * @author anjan
 *
 */
public class LocalConfiguration implements Parcelable {
	private int searchRadius;
	private boolean gpsEnabled;
	
	/**
	 * Default constructor
	 */
	public LocalConfiguration() {
		searchRadius = 10;
		gpsEnabled = false;
	}
	
	public LocalConfiguration(Parcel in) {
		searchRadius = in.readInt();
		gpsEnabled = (in.readByte() == 1);
    }
	
	public int getSearchRadius() {
		return searchRadius;
	}
	
	public boolean isGpsEnabled() {
		return gpsEnabled;
	}
	
	public void setSearchRadius(int searchRadius) {
		this.searchRadius = searchRadius;
	}
	
	public void setGpsEnabled(boolean gpsEnabled) {
		this.gpsEnabled = gpsEnabled;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(searchRadius);
		dest.writeByte((byte) (gpsEnabled ? 1 : 0)); 
	}
	
	public static final Parcelable.Creator<LocalConfiguration> CREATOR = new Parcelable.Creator<LocalConfiguration>() {
		@Override
		public LocalConfiguration createFromParcel(Parcel source) {
			return new LocalConfiguration(source);
		}

		@Override
		public LocalConfiguration[] newArray(int size) {
			return new LocalConfiguration[size];
		}
	};
}
