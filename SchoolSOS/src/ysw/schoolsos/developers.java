package ysw.schoolsos;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class developers extends SherlockPreferenceActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.developers);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xffFF4444));
		getSupportActionBar().setIcon(R.drawable.abslogo);
		getSupportActionBar().setTitle("������ ����");
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
			startActivity(new Intent(developers.this, MainActivity.class));
			overridePendingTransition(R.anim.rightin, R.anim.rightout);
		} else if (id == R.id.call_sos) {
		    AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
		    alt_bld.setMessage("117 �� ��ȭ�� �Ű��Ͻðڽ��ϱ�?").setCancelable(
		        false).setPositiveButton("��",
		        new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		        	Intent callsos = new Intent(Intent.ACTION_CALL,
							Uri.parse("tel:" + 117));
					startActivity(callsos);
		        }
		        }).setNegativeButton("�ƴϿ�",
		        new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            // Action for 'NO' Button
		            dialog.cancel();
		        }
		        });
		    AlertDialog alert = alt_bld.create();
		    // Title for AlertDialog
		    alert.setTitle("��ȭ�Ű�");
		    // Icon for AlertDialog
		    alert.show();

		} else if (id == R.id.sms_sos) {
			try {
				 AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
				    alt_bld.setMessage("117 �� ���ڷ� �Ű��Ͻðڽ��ϱ�?").setCancelable(
				        false).setPositiveButton("��",
				        new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				        	TelephonyManager telManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
							String phoneNum = telManager.getLine1Number();
			                SmsManager smsManager = SmsManager.getDefault();
			                
			                smsManager.sendTextMessage(("#0117"), null, phoneNum + "���� �����մϴ�!!", null, null);
			                Toast.makeText(getApplicationContext(), "SMS �Ű� �Ϸ�Ǿ����ϴ�.",
			                        Toast.LENGTH_LONG).show();
				        }
				        }).setNegativeButton("�ƴϿ�",
				        new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            // Action for 'NO' Button
				            dialog.cancel();
				        }
				        });
				    AlertDialog alert = alt_bld.create();
				    // Title for AlertDialog
				    alert.setTitle("���ڽŰ�");
				    // Icon for AlertDialog
				    alert.show();
				
           } catch (Exception e) {
               Toast.makeText(getApplicationContext(),
                       "SMS �Ű� ���еǾ����ϴ�.",
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
	public void onBackPressed() {
		startActivity(new Intent (developers.this, setting.class));
		overridePendingTransition(R.anim.rightin, R.anim.rightout);
	}
} 