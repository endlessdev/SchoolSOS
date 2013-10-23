package ysw.schoolsos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class FastSOS extends Activity{
	private gridview_adapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;

	private GridView gridView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview1);
        setTitle("Fast SOS");
        prepareList();
        mAdapter = new gridview_adapter(this,listCountry, listFlag);
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);
        gridView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)  {
    			if(position==0){
    				Intent callsos = new Intent(Intent.ACTION_CALL,
    						Uri.parse("tel:" + 117));
    				startActivity(callsos);
    	    	}else if(position==1){
    	    		 AlertDialog.Builder alt_bld = new AlertDialog.Builder(FastSOS.this);
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
    	    		}
            }
        });        
	}

	public void prepareList() {
		listCountry = new ArrayList<String>();

		listCountry.add("전화신고");
		listCountry.add("문자신고");

		listFlag = new ArrayList<Integer>();
		listFlag.add(R.drawable.fsos_call);
		listFlag.add(R.drawable.fsos_message);
	}
	}
