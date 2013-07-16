package net.fred.feedex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utils {
	static public void setPreferenceTheme(Activity a) {
		if (!PrefsManager.getBoolean(PrefsManager.LIGHT_THEME, true)) {
			a.setTheme(android.R.style.Theme_Holo);
		}
	}
	
	static public void showSimpleDialog(Context c, int titleId, int msgId) {
		new AlertDialog.Builder(c) //
		.setTitle(titleId) //
		.setMessage(msgId) //
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
			}
		}).show();
	}
}
