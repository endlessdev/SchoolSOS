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

	// Ȯ���ϰ� ���� ��Ű���� String
	private static final String CHECK_PACKAGE_NAME = "net.daum.android.air";
	// Ȯ���ϰ� ���� ��Ű������ �̸� Name
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
					final CharSequence[] items = { "�б����½Ű���(117)",
							"������û�ҳ���ȭ(1388)", "�б�����SOS������(1588-9128)" };

					AlertDialog.Builder builder = new AlertDialog.Builder(
							counseling_center.this);
					builder.setTitle("��ȭ���");
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
					final CharSequence[] items = { "�б����½Ű���(#0117)",
							"������û�ҳ���ȭ(#1388)" };

					AlertDialog.Builder builder = new AlertDialog.Builder(
							counseling_center.this);
					builder.setTitle("���ڻ��");
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
										Log.d("0117�������̸�",
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
											Log.d("1388�������̸�",
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
						.setTitle("��ٹ̽�")
								.setMessage(
										"��ٹ̽ܿ��� ���޴¹�\n1.ģ����õ���� ��ٹ̽� ����\n2.ģ���߰� �� '����û' ���� \n3.��㳻�� ���� �� ������")
								.setCancelable(false)
								.setPositiveButton("���",
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
								.setNegativeButton("���",
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
						// ��Ű���� ���� ���.
						/*
						 * Log.d(TAG, "��Ű���� ��ġ �Ǿ� �ֽ��ϴ�."); Intent intent =
						 * getPackageManager
						 * ().getLaunchIntentForPackage("net.daum.android.air");
						 * startActivity(intent);
						 */

					}

					catch (NameNotFoundException e) {
						AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
								counseling_center.this);
						alert_confirm
								.setMessage("���������� ��ġ���� �ʾҽ��ϴ�. �������� �̵��Ͻðڽ��ϱ�?")
								.setCancelable(false)
								.setPositiveButton("Ȯ��",
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
								.setNegativeButton("���",
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
						Log.e(TAG, "��Ű���� ��ġ �Ǿ� ���� �ʽ��ϴ�.");
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

		listCountry.add("��ȭ���");
		listCountry.add("���ڻ��");
		listCountry.add("��ٹ̽�");
		listCountry.add("Wee ��㼾��");

		listFlag = new ArrayList<Integer>();
		listFlag.add(R.drawable.fsos_call);
		listFlag.add(R.drawable.fsos_message);
		listFlag.add(R.drawable.mypeople);
		listFlag.add(R.drawable.wee);

	}
}