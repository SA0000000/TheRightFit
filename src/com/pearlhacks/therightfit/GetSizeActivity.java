package com.pearlhacks.therightfit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GetSizeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		int bid = extras.getInt("BRAND_ID",1);
		int uid = extras.getInt("USER_ID",1);
		
		setContentView(R.layout.activity_get_size);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_size, menu);
		return true;
	}

}
