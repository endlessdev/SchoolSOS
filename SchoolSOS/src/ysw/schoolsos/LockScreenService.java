package ysw.schoolsos;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LockScreenService extends Service {
	private BroadcastReceiver mReceiver;

	
	@Override
	public void onCreate() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		mReceiver = new LockScreenReceiver();
		registerReceiver(mReceiver, filter);

		Log.d("LOGTAG" , "LockScreenService_1");
  		}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent==null)
		{
			IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
			filter.addAction(Intent.ACTION_SCREEN_OFF);
			mReceiver = new LockScreenReceiver();
			registerReceiver(mReceiver, filter);
			Log.d("LOGTAG" , "LockScreenService_2");
		}
		return START_STICKY;
	}
	
	
	
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onDestory(Context context) {
		unregisterReceiver(mReceiver);
	}
}