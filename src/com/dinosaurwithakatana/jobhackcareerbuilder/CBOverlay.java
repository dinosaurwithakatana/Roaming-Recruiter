package com.dinosaurwithakatana.jobhackcareerbuilder;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.dinosaurwithakatana.roamingrecruiter.R;
import com.google.android.maps.*;

/**
 * Represents an overlay that will be placed on the Google Maps Map View.
 * @author anjan
 *
 */
public class CBOverlay extends ItemizedOverlay<OverlayItem>{
	private ArrayList<OverlayItem> overlayItemList;
	private Context mContext;

	public CBOverlay(Drawable marker, Context context, String DID) {
		super(boundCenterBottom(marker));
		mContext = context;
		if (DID != null)
			CurrentUser.currentDID = DID;
		else
			CurrentUser.currentDID = "";
		
		overlayItemList = new ArrayList<OverlayItem>();
		populate();
	}
	
	public void addItem(GeoPoint p, String title, String snippet) {
		OverlayItem newItem = new OverlayItem(p, title, snippet);
	    overlayItemList.add(newItem);
	    populate();
	}
	
	@Override
	public boolean onTap(int index) {
		OverlayItem item = overlayItemList.get(index);
		Log.d("CBOverlay", item.getTitle() + ": " + item.getSnippet());
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		
		dialog.setPositiveButton("Apply!", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	// Apply to CB Job	
	        	try {
	        		String response = CurrentUser.sendApplication();
	        		Toast.makeText(mContext, "Posted Job Successfully!", Toast.LENGTH_SHORT).show();
	        	} catch (Exception e) {
	        		e.printStackTrace();
	        	}
	        }
	    });
	    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	// Disregard
	        	dialog.dismiss();
	        }
	    });
		
		
		dialog.show();
		return true;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlayItemList.get(i);
	}

	@Override
	public int size() {
		return overlayItemList.size();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	    super.draw(canvas, mapView, shadow);
	}

}
