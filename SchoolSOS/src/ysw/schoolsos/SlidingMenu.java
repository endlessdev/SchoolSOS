package ysw.schoolsos;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class SlidingMenu extends SlidingActivity {
	Vibrator sirenvib;
	// ListView
	private ArrayList<MainMenu> Array_Data;
	private MainMenu data;
	private ListAdapter adapter;
	ListView list;
	ConnectivityManager cManager;
	NetworkInfo mobile;
	NetworkInfo wifi;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.slidingmenumain);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xffFF4444));
		getSupportActionBar().setIcon(R.drawable.abslogo);
		setSlidingActionBarEnabled(false);

		// ListView
		list = (ListView) findViewById(R.id.list);
		Array_Data = new ArrayList<MainMenu>();
		data = new MainMenu(R.drawable.main, getString(R.string.main),
				getString(R.string.main_sub));
		Array_Data.add(data);

		data = new MainMenu(R.drawable.legal, getString(R.string.legal_info),
				getString(R.string.legal_info_sub));
		Array_Data.add(data);

		data = new MainMenu(R.drawable.tip, getString(R.string.tip),
				getString(R.string.tip_sub));
		Array_Data.add(data);

		data = new MainMenu(R.drawable.counseling,
				getString(R.string.counseling),
				getString(R.string.counseling_sub));
		Array_Data.add(data);

		data = new MainMenu(R.drawable.tell, getString(R.string.app_notify),
				getString(R.string.app_notify_sub));
		Array_Data.add(data);

		adapter = new ListAdapter(this, R.layout.listviewlayout, Array_Data);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Class<?> cls = null;
				if (position == 0) {
					cls = MainActivity.class;
					Intent intent = new Intent(SlidingMenu.this, cls);
					startActivity(intent);
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					finish();
				} else if (position == 1) {
					cls = legal_information.class;
					Intent intent = new Intent(SlidingMenu.this, cls);
					startActivity(intent);
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					finish();
				} else if (position == 2) {
					cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					mobile = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
					wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
					if (mobile.isConnected() || wifi.isConnected()) {
						cls = tip.class;
						Intent intent = new Intent(SlidingMenu.this, cls);
						startActivity(intent);
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						finish();
					} else {
						new AlertDialog.Builder(SlidingMenu.this)
					    // �޽����� �����Ѵ�.
					    .setMessage("��Ʈ��ũ ���¸� Ȯ���� �ּ���")
					    // positive ��ư�� �����Ѵ�.
					    .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener()
					    {
					        // positive ��ư�� ���� Ŭ�� �̺�Ʈ ó���� �����Ѵ�.
					        @Override
					        public void onClick(DialogInterface dialog, int which)
					        {
					          
					            dialog.dismiss();
					        }
					    })
					    // ������ ���� ���� �˷� ���̾�α׸� ȭ�鿡 �����ش�.
					    .show();

					}
					
				} else if (position == 3) {
					cls = counseling_center.class;
					Intent intent = new Intent(SlidingMenu.this, cls);
					startActivity(intent);
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
					finish();
				} else if (position == 4) {
					Intent msg = new Intent(Intent.ACTION_SEND);
					msg.addCategory(Intent.CATEGORY_DEFAULT);
					msg.putExtra(Intent.EXTRA_SUBJECT, "School SOS");
					msg.putExtra(Intent.EXTRA_TEXT,
							"�б����� ������ ���ؼ� �ֺ�ģ������ ������ �ʿ��մϴ�. School SOS �� �б������� �����ϼ���!");
					msg.putExtra(Intent.EXTRA_TITLE, "����");
					msg.setType("text/plain");
					startActivity(Intent.createChooser(msg, "�� ���� �˸���"));
					overridePendingTransition(R.anim.leftin, R.anim.leftout);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			toggle();
		} else if (id == R.id.sms_sos) {
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

		} else if (id == R.id.setting) {
			startActivity(new Intent(SlidingMenu.this, setting.class));
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(SlidingMenu.this);
		alt_bld.setMessage("�������� �̵��Ͻðڽ��ϱ�?")
				.setCancelable(false)
				.setPositiveButton("��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(SlidingMenu.this, MainActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						finish();
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