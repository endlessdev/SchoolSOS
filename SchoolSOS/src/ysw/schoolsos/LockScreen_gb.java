package ysw.schoolsos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class LockScreen_gb extends Activity {
	Intent intent;
	String dateformat,timeformat;
	TextView date,master_pref;
	private LocationManager locManager;
	Geocoder geoCoder;
	CustomDigitalClock dc;
	private TextView contentTxt;
	Button button1;
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			int level = intent.getIntExtra("level", 0);
			contentTxt.setText(String.valueOf(level) + "%");
		}
	};
	SeekBar stu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		setContentView(R.layout.lockscreen_gb);
		dateformat = android.text.format.DateFormat.format("yyyy년 MM월 dd일", System.currentTimeMillis()).toString();
		date =(TextView) findViewById(R.id.date);
		date.setText(dateformat);
        dc = (CustomDigitalClock) findViewById(R.id.time);
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/NanumGothicLight.otf");
        Typeface Clockface=Typeface.createFromAsset(getAssets(), "fonts/AndroidClock.otf");
        dc.setTypeface(Clockface);
        date.setTypeface(face);
        contentTxt = (TextView) this.findViewById(R.id.battery);
	    this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	    contentTxt.setTypeface(face);
	    
		SharedPreferences myPreference = PreferenceManager
				.getDefaultSharedPreferences(this);
		master_pref = (TextView) findViewById(R.id.master_pref);
		master_pref.setText((myPreference
				.getString("master_pref", "SafeLocker")));
		master_pref.setTypeface(face);
	    
		WallpaperManager wpm = WallpaperManager.getInstance(this);
		BitmapDrawable bd = (BitmapDrawable) wpm.getDrawable();
		this.getWindow().setBackgroundDrawable(bd);
        stu = (SeekBar) findViewById(R.id.stu);
		// 일정간격을 맞추기위해
		stu.setThumbOffset(10);
		stu.setMax(100);
		stu.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (seekBar.getProgress() == 100) {
					finish();
					Intent FastSOS = new Intent(LockScreen_gb.this,
							LockScreenService.class);
						startService(FastSOS);
				}
					else if(seekBar.getProgress() == 0) {
						Intent callsos = new Intent(Intent.ACTION_CALL,
								Uri.parse("tel:" + 117));
						startActivity(callsos);
						seekBar.setProgress(50);
					} else {
					seekBar.setProgress(50);
				}
			}
		});
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					 AlertDialog.Builder alt_bld = new AlertDialog.Builder(LockScreen_gb.this);
					    alt_bld.setMessage("117 에 문자로 신고하시겠습니까?").setCancelable(
					        false).setPositiveButton("네",
					        new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					        	TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
								String phoneNum = telManager.getLine1Number();
				                SmsManager smsManager = SmsManager.getDefault();
				                
				                smsManager.sendTextMessage(("#0117"), null, phoneNum + "님이 위급합니다!!", null, null);
				                Toast.makeText(getApplicationContext(), "SMS 신고가 완료되었습니다.",
				                        Toast.LENGTH_LONG).show();
					        }
					        }).setNegativeButton("아니오",
					        new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					            // Action for 'NO' Button
					            dialog.cancel();
					        }
					        });
					    AlertDialog alert = alt_bld.create();
					    // Title for AlertDialog
					    alert.setTitle("문자신고");
					    // Icon for AlertDialog
					    alert.show();
					
	            } catch (Exception e) {
	                Toast.makeText(getApplicationContext(),
	                        "SMS 신고가 실패되었습니다.",
	                        Toast.LENGTH_LONG).show();
	                e.printStackTrace();
	            }

			}
		});
	}



	@Override
	public void onBackPressed() {
		// disable back key
		return;
	}

}
