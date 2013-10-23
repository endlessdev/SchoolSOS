package ysw.schoolsos;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class FastSOS_receiver extends BroadcastReceiver {
	ComponentName cn;
	ComponentName svcName;
	
	@Override
	
	public void onReceive(Context context, Intent intent) {
		SharedPreferences pref = context.getSharedPreferences("ysw.schoolsos_preferences", Context.MODE_PRIVATE);
		boolean fsos_service = pref.getBoolean("fsos_service", false);
			Log.d("LOGTAB", "lsreceiver_1");
			if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
				Log.d("LOGTAB", "lsreceiver_2");
			if(fsos_service==true){
				Log.d("LOGTAB", "lsreceiver_3");
				intent = new Intent(context, FastSOS_service.class);
		        context.startService(intent); 
			}
			}
			 }
			}
