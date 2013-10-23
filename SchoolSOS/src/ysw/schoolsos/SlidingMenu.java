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
					    // 메시지를 설정한다.
					    .setMessage("네트워크 상태를 확인해 주세요")
					    // positive 버튼을 설정한다.
					    .setPositiveButton("확인", new DialogInterface.OnClickListener()
					    {
					        // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
					        @Override
					        public void onClick(DialogInterface dialog, int which)
					        {
					          
					            dialog.dismiss();
					        }
					    })
					    // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
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
							"학교폭력 예방을 위해선 주변친구들의 관심이 필요합니다. School SOS 로 학교폭력을 예방하세요!");
					msg.putExtra(Intent.EXTRA_TITLE, "제목");
					msg.setType("text/plain");
					startActivity(Intent.createChooser(msg, "이 앱을 알리기"));
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

		} else if (id == R.id.setting) {
			startActivity(new Intent(SlidingMenu.this, setting.class));
			overridePendingTransition(R.anim.leftin, R.anim.leftout);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(SlidingMenu.this);
		alt_bld.setMessage("메인으로 이동하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(SlidingMenu.this, MainActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.leftin, R.anim.leftout);
						finish();
					}
				})
				.setNegativeButton("아니오",
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