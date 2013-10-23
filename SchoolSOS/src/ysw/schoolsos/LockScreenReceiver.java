package ysw.schoolsos;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;


public class LockScreenReceiver extends BroadcastReceiver{
	public static boolean screenoff;
	PendingIntent pendingItent;
	Intent gbIntent,changeIntent;
	ComponentName cn;
	ComponentName svcName;
	@Override
	public void onReceive(Context context, Intent intent) {
		SharedPreferences pref = context.getSharedPreferences("ysw.schoolsos_preferences", Context.MODE_PRIVATE);
		boolean serviceon = pref.getBoolean("service", false);
			Log.d("LOGTAB", "LSR_1");
			 if (intent.getAction().equals(intent.ACTION_SCREEN_OFF)) {
				 screenoff = true;
			 if(serviceon==true){
					Log.d("LOGTAB", "LSR_2");
				Intent changeIntent  = new Intent (context, LockScreen_gb.class);
				PendingIntent pendingItent = PendingIntent.getActivity(context, 0, changeIntent, 0);
				try {
					pendingItent.send();
				}catch (Exception ex) {
					;
				}
				Log.d("LOGTAB", "LSR_3");
			
			try {
				pendingItent.send();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
				    }
			 
			 }
}
}
