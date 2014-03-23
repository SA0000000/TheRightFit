package com.pearlhacks.therightfit;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class AddUserInfoActivity extends Activity {
	
	private OnClickListener btnAddUserClickListener =new OnClickListener(){
		@Override
		public void onClick(View v) {
			// Save User Info and add user name to the drop down list on the main activity
			Toast.makeText(AddUserInfoActivity.this, "Your Data Has Been Saved!!Proceed to get your fit!!", Toast.LENGTH_SHORT).show();
		}
	};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user_info);
		// Show the Up button in the action bar.
		setupActionBar();
		Button btn_add_usr=(Button)findViewById(R.id.btn_Add_user);
		if(btn_add_usr!=null)
			btn_add_usr.setOnClickListener(btnAddUserClickListener);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_user_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//**************Housekeeping functions**********************
    @Override
	protected void onStop(){
	      super.onStop();
	      Log.v("In onStop"," Ready to stop activity!!!");
	   }

}
