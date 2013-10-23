package ysw.schoolsos;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;



public class emergency_contact extends SlidingMenu {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		getSupportActionBar().setTitle(R.string.counseling);
		
		}
}
