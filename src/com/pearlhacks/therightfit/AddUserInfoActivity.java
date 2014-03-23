package com.pearlhacks.therightfit;

import java.io.IOException;

import com.pearlhacks.therightfit.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class AddUserInfoActivity extends Activity {
	private static String DB_PATH = "/data/data/com.example.myfirstapp/databases/"; 
	private static String DB_NAME ="theRightFit.sqlite";// Database name
	private OnClickListener btnAddUserClickListener =new OnClickListener(){
		@Override
		public void onClick(View v) {
			// Save User Info and add user name to the drop down list on the main activity
	        String name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
	        float bust = Float.parseFloat(((EditText)findViewById(R.id.edit_bust)).getText().toString());
	        float waist = Float.parseFloat(((EditText)findViewById(R.id.edit_waist)).getText().toString());
	        float hip = Float.parseFloat(((EditText)findViewById(R.id.edit_waist)).getText().toString());
	        float inseam = Float.parseFloat(((EditText)findViewById(R.id.edit_waist)).getText().toString());
			addUser(name, bust, waist, hip, inseam);
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

    private void addUser(String name, float bust, float waist, float hip, float inseam){
    	SQLiteDatabase db = getWriteDB();//SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
		String query = "INSERT INTO user VALUES(null,'%s', %f,%f,%f,%f)"; 
        db.execSQL(String.format(query, name, bust, waist, hip, inseam));
        db.close(); // Closing database connection
    }
    private SQLiteDatabase getWriteDB(){
    	SQLiteDatabase db;
    	DBHelper myDbHelper = new DBHelper(this);
	    try {
	
	    	myDbHelper.createDataBase();
	
		} catch (IOException ioe) {
	
			throw new Error("Unable to create database");
	
		}
		try {
	
			myDbHelper.openDataBase();
			myDbHelper.close();
			db = myDbHelper.getWritableDatabase();
	
		}catch(SQLException sqle){
			throw sqle;
		}
		return db;
    }
}
