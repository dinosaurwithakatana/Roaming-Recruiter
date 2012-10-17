package com.dinosaurwithakatana.jobhackcareerbuilder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.dinosaurwithakatana.roamingrecruiter.R;

public class LocationDialog extends SherlockDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.location_dialog)
               .setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	   Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                   startActivity(intent);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}