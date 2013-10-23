package ysw.schoolsos;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

@SuppressLint("HandlerLeak")
public class FastSOS_service extends Service {

	protected boolean mRunning;

	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onDestroy() {
		mRunning = false;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);
	}

	@SuppressWarnings("unused")
	private Handler mHandler = new Handler() {
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				FastSOS_service.this);
		builder.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("FastSOS").setOngoing(true)
				.setOnlyAlertOnce(true).setWhen(0)
				.setContentText("신고를 하시려면 탭하세요").setAutoCancel(false)
				.setOnlyAlertOnce(true);

		Intent i = new Intent(getApplicationContext(), FastSOS.class);
		PendingIntent pIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, i,
				Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		builder.setContentIntent(pIntent);

		manager.notify(1, builder.build());
		return START_STICKY;
	}
}
