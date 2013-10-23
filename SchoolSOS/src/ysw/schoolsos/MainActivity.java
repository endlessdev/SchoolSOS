package ysw.schoolsos;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends SlidingMenu {
	Button button1,button2,button3;
	@Override
	public void onCreate(Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xffFF4444));

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
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
			}
		});
		
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					 AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
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

			}
		});
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.wee.go.kr//home/consult/cons01001w.php"));
				startActivity(intent);
			}
		});
		
		

}@Override
public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getSupportMenuInflater();
	inflater.inflate(R.menu.main_menu, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	int id = item.getItemId();
if (id == R.id.setting) {
		startActivity(new Intent(MainActivity.this, setting.class));
		overridePendingTransition(R.anim.leftin, R.anim.leftout);
	}
	return super.onOptionsItemSelected(item);
}

@Override
public void onBackPressed() {
	AlertDialog.Builder alt_bld = new AlertDialog.Builder(MainActivity.this);
	alt_bld.setMessage("���� �����Ͻðڽ��ϱ�?")
			.setCancelable(false)
			.setPositiveButton("��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					moveTaskToBack(true);
					android.os.Process.killProcess(android.os.Process
							.myPid());
				}
			})
			.setNegativeButton("�ƴϿ�",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
	AlertDialog alert = alt_bld.create();
	alert.setTitle("School SOS");
	alert.show();
}



}