package ysw.schoolsos;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class setting extends SherlockPreferenceActivity implements
		OnPreferenceClickListener {
	LinearLayout layout;
	CheckBoxPreference service, receiver, fsos_service, fsos_receiver;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xffFF4444));
		getSupportActionBar().setTitle("설정");
		getSupportActionBar().setIcon(R.drawable.abslogo);
		service = (CheckBoxPreference)findPreference("service");
		fsos_service = (CheckBoxPreference)findPreference("fsos_service");
		service.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				boolean myValue = (Boolean) newValue;
				Intent FastSOS = new Intent(setting.this,
						LockScreenService.class);
				if (myValue) {
					startService(FastSOS);
				} else {
					stopService(FastSOS);
				}
				return true;
			}
		});
		fsos_service
				.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						boolean myValue = (Boolean) newValue;
						Intent service = new Intent(setting.this,
								FastSOS_service.class);
						if (myValue) {
							startService(service);
						} else {
							stopService(service);
						}
						return true;
					}
				});
		Preference developers = findPreference("app_developers");
		developers.setOnPreferenceClickListener(this);
	}
	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater inflater = getSupportMenuInflater();
	  inflater.inflate(R.menu.setting_action_menu, menu);
	  return true;
	 }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			startActivity(new Intent(setting.this, MainActivity.class));
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
		} else if (id == R.id.call_sos) {
		    AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
		    alt_bld.setMessage("117 에 전화로 신고하시겠습니까?").setCancelable(
		        false).setPositiveButton("네",
		        new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		        	Intent callsos = new Intent(Intent.ACTION_CALL,
							Uri.parse("tel:" + 117));
					startActivity(callsos);
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
		    alert.setTitle("전화신고");
		    // Icon for AlertDialog
		    alert.show();

		} else if (id == R.id.sms_sos) {
			try {
				 AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
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
		} else if (id == R.id.call_sos) {
			Intent callsos = new Intent(Intent.ACTION_CALL,
					Uri.parse("tel:" + 117));
			startActivity(callsos);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if(preference.getKey().equals("app_developers"))
        {
        	Intent developers = new Intent(setting.this, developers.class); 
	    	 startActivity(developers);
	    	 overridePendingTransition(R.anim.leftin, R.anim.leftout);
        }
		return false;
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(setting.this, MainActivity.class));
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
		}
	
}