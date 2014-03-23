package com.pearlhacks.therightfit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class GetSizeActivity extends Activity {

	private String txt_tops_size;
	private String txt_dress_size;
	private String txt_bottom_size;
	private String txt_user_name;
	private String txt_brand_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();		
		int bid = extras.getInt("BRAND_ID",1);
		int uid = extras.getInt("USER_ID",1);
		setContentView(R.layout.activity_get_size);
		
		//Use these when you have the string array with all the values to set
		TextView top =(TextView)findViewById(R.id.txt_top_size);
		TextView dress =(TextView)findViewById(R.id.txt_dress_size);
		TextView bottom =(TextView)findViewById(R.id.txt_bottom_size);
		TextView user_name =(TextView)findViewById(R.id.txt_usr_name);
		TextView brand_name =(TextView)findViewById(R.id.txt_brand_name);
		top.setText(txt_tops_size);
		dress.setText(txt_dress_size);
		bottom.setText(txt_bottom_size);
		user_name.setText(txt_user_name);
		brand_name.setText(txt_brand_name);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_size, menu);
		return true;
	}

}
