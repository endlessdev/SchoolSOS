package ysw.schoolsos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class counseling_center extends SlidingMenu {
	/** Called when the activity is first created. */

	private gridview_adapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;
	PackageInfo pi;
	PackageManager pm;
	Context a;
	private GridView gridView;
	private String encoding = "UTF-8";
	private static final String TAG = "TestInstallPackageActivity";

	// 확인하고 싶은 패키지명 String
	private static final String CHECK_PACKAGE_NAME = "net.daum.android.air";
	// 확인하고 싶은 패키지명의 이름 Name
	private static final String CHECK_APP_NAME = "APP_NAME";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		getSupportActionBar().setTitle(R.string.counseling);
		prepareList();
		// prepared arraylist and passed it to the Adapter class
		mAdapter = new gridview_adapter(this, listCountry, listFlag);
		// Set custom adapter to gridview
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position == 0) {
					final CharSequence[] items = { "학교폭력신고센터(117)",
							"헬프콜청소년전화(1388)", "학교폭력SOS지원단(1588-9128)" };

					AlertDialog.Builder builder = new AlertDialog.Builder(
							counseling_center.this);
					builder.setTitle("전화상담");
					builder.setItems(items,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int item) {
									if (item == 0) {
										Intent intent = new Intent(
												Intent.ACTION_CALL, Uri
														.parse("tel:" + 117));
										startActivity(intent);
									}
										else if (item == 1) {
											Intent intent1 = new Intent(
													Intent.ACTION_CALL,
													Uri.parse("tel:" + 1388));
											startActivity(intent1);
										}
										else if (item == 2) {
											Intent intent1 = new Intent(
													Intent.ACTION_CALL,
													Uri.parse("tel:" + 15889128));
											startActivity(intent1);
										}
									
								}

							});
					AlertDialog alert = builder.create();

					alert.show();
				} else if (position == 1) {
					final CharSequence[] items = { "학교폭력신고센터(#0117)",
							"헬프콜청소년전화(#1388)" };

					AlertDialog.Builder builder = new AlertDialog.Builder(
							counseling_center.this);
					builder.setTitle("문자상담");
					builder.setItems(items,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int item) {
									if (item == 0) {
										Intent sendIntent = new Intent(
												Intent.ACTION_VIEW);
										sendIntent.putExtra("sms_body", "");
										sendIntent.putExtra("address", "#0117");
										sendIntent
												.setType("vnd.android-dir/mms-sms");
										startActivity(sendIntent);
										overridePendingTransition(
												R.anim.leftin, R.anim.leftout);
										Log.d("0117아이템이름",
												Integer.toString(item));
									}
										else if (item == 1) {
								
											Intent sendIntent1 = new Intent(
													Intent.ACTION_VIEW);
											sendIntent1
													.putExtra("sms_body", "");
											sendIntent1.putExtra("address",
													"#1388");
											sendIntent1
													.setType("vnd.android-dir/mms-sms");
											startActivity(sendIntent1);
											overridePendingTransition(
													R.anim.leftin,
													R.anim.leftout);
											Log.d("1388아이템이름",
													Integer.toString(item));
										}
									}
								

							});
					AlertDialog alert = builder.create();

					alert.show();
					/*
					 * Intent sendIntent = new Intent(Intent.ACTION_VIEW);
					 * sendIntent.putExtra("sms_body", "");
					 * sendIntent.putExtra("address", "#0117");
					 * sendIntent.setType("vnd.android-dir/mms-sms");
					 * startActivity(sendIntent);
					 * overridePendingTransition(R.anim.leftin, R.anim.leftout);
					 */
				} else if (position == 2) {
					try {

						PackageManager pm = getPackageManager();
						PackageInfo pi = pm.getPackageInfo(
								CHECK_PACKAGE_NAME.trim(),
								PackageManager.GET_META_DATA);
						ApplicationInfo appInfo = pi.applicationInfo;
						AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
								counseling_center.this);
						alert_confirm
						.setTitle("상다미쌤")
								.setMessage(
										"상다미쌤에게 상담받는법\n1.친구추천에서 상다미쌤 선택\n2.친구추가 후 '상담신청' 전송 \n3.상담내용 전송 후 상담시작")
								.setCancelable(false)
								.setPositiveButton("상담",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												Intent intent = getPackageManager()
														.getLaunchIntentForPackage(
																"net.daum.android.air");
												startActivity(intent);
												overridePendingTransition(
														R.anim.leftin,
														R.anim.leftout);
											}
										})
								.setNegativeButton("취소",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// 'No'
												return;
											}
										});
						AlertDialog alert = alert_confirm.create();
						alert.show();
						// 패키지가 있을 경우.
						/*
						 * Log.d(TAG, "패키지가 설치 되어 있습니다."); Intent intent =
						 * getPackageManager
						 * ().getLaunchIntentForPackage("net.daum.android.air");
						 * startActivity(intent);
						 */

					}

					catch (NameNotFoundException e) {
						AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
								counseling_center.this);
						alert_confirm
								.setMessage("마이피플이 설치되지 않았습니다. 마켓으로 이동하시겠습니까?")
								.setCancelable(false)
								.setPositiveButton("확인",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												Uri uri = Uri
														.parse("market://details?id=net.daum.android.air");
												Intent it = new Intent(
														Intent.ACTION_VIEW, uri);
												startActivity(it);

											}
										})
								.setNegativeButton("취소",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// 'No'
												return;
											}
										});
						AlertDialog alert = alert_confirm.create();
						alert.show();
						Log.e(TAG, "패키지가 설치 되어 있지 않습니다.");
					}
				} else if (position == 3) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.wee.go.kr//home/consult/cons01001w.php"));
					startActivity(intent);
				}
			}
		});
	}

	public void prepareList() {
		listCountry = new ArrayList<String>();

		listCountry.add("전화상담");
		listCountry.add("문자상담");
		listCountry.add("상다미쌤");
		listCountry.add("Wee 상담센터");

		listFlag = new ArrayList<Integer>();
		listFlag.add(R.drawable.fsos_call);
		listFlag.add(R.drawable.fsos_message);
		listFlag.add(R.drawable.mypeople);
		listFlag.add(R.drawable.wee);

	}
}