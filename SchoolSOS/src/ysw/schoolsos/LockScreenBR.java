package ysw.schoolsos;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

public class LockScreenBR extends BroadcastReceiver {
	public static boolean screenoff;
	PendingIntent pendingItent;
	Intent gbIntent, changeIntent;
	private BroadcastReceiver mReceiver;
	ComponentName cn;
	ComponentName svcName;
	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences pref = context.getSharedPreferences("ysw.schoolsos_preferences", Context.MODE_PRIVATE);
		boolean serviceon = pref.getBoolean("service", true);
		Log.d("LOGTAB", "BRtest");
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Log.d("LOGTAB", "BRtest_2");
				if (serviceon==true) {
						 cn = new ComponentName(context.getPackageName(), LockScreenService.class.getName());
							context.startService(new Intent().setComponent(cn));
						Intent changeIntent  = new Intent (context, LockScreen_gb.class);
						PendingIntent pendingItent = PendingIntent.getActivity(context, 0, changeIntent, 0);
						try {
							pendingItent.send();
						}catch (Exception ex) {
							;
						}

		}
	}
}}