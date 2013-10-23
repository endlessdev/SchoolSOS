package ysw.schoolsos;


import java.io.IOException;
import java.io.InputStream;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;



public class legal_information extends SlidingMenu {
	TextView li;
	 private String litxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.legal_information);
		getSupportActionBar().setTitle(R.string.legal_info);
		  li = (TextView)findViewById(R.id.li);
		  
		  try {
		   litxt = readText("legal_information.txt");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  li.setText(litxt);
		 }

		 private String readText(String file) throws IOException {
		  InputStream is = getAssets().open(file);

		  int size = is.available();
		  byte[] buffer = new byte[size];
		  is.read(buffer);
		  is.close();

		  String text = new String(buffer);

		  return text;
		 }
		}
