package com.pearlhacks.therightfit;

import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	 private OnClickListener btnAddUserClickListener =new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Start New Activity that allows user to add their info			
				startActivity(new Intent(MainActivity.this, AddUserInfoActivity.class));
			}
		};
	private OnClickListener btnGetSizeClickListener =new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Start New Activity that uses the info of the user selected and the brand 
				// and gives the size info of the user in a different activity
				// Call a function that does that and do it on a different thread
				getUserSize();
				
				//Create a new intent and pass user_id, and brand_name/brand_id through it to the next activity
				Intent myintent =new Intent(MainActivity.this,GetSizeActivity.class);
				
				//here add code to pass data to the intent
				
				
				//start activity
				startActivity(myintent);
			}
		};
		
	protected void getUserSize(){
		
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_add_usr=(Button)findViewById(R.id.btn_add_user);
        Button btn_get_size=(Button)findViewById(R.id.btn_get_size);
		if(btn_add_usr!=null)
			btn_add_usr.setOnClickListener(btnAddUserClickListener);
		if(btn_get_size!=null)
			btn_get_size.setOnClickListener(btnGetSizeClickListener);
		
		//add items to the brand spinner
		
		//PopulateBrands();
		
		//add items to the user spinner
		//PopulateUsers();
		
    }
    
    
    //this function adds items to the Brand spinner
    void PopulateBrands(){
    	new Thread(new Runnable(){
    		@Override
    		public void run(){
    				final Spinner brands=(Spinner)findViewById(R.id.spnr_select_brand);
    				//get all the data from the database and store it in some local variable or list
    				//that list needs to be declared final
    				Log.v("Populate Brands"," Inside the function for adding items to brands spinner!!!");
    		    	final SQLiteDatabase db = getReadDB();
    		    	Cursor c = db.rawQuery("SELECT * FROM brand ORDER BY name", null);
    				final SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_spinner_item, c, new String[] {"name"},new int[] {android.R.id.text1});
    		        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    			
    				brands.post(new Runnable() {
    					@Override
    					public void run() {
    				        brands.setAdapter(mAdapter);
    				        db.close();
    						//do something like spinner.add items here 
    						//here your spinner is 'brands'
    					}
    				});
    			
    		}
    		}).start();
    }
    
    void PopulateUsers(){
    	new Thread(new Runnable(){
    		@Override
    		public void run(){
    				final Spinner usrs=(Spinner)findViewById(R.id.spnr_select_usr);
    				//get all the data from the database and store it in some local variable or list
    				//that list needs to be declared final
    				Log.v("Populate Users"," Inside the function for adding items to User spinner!!!");
    				final SQLiteDatabase db = getReadDB();
    		    	Cursor c = db.rawQuery("SELECT * FROM user ORDER BY name", null);
    				final SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_spinner_item, c, new String[] {"name"},new int[] {android.R.id.text1});
    		        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	
    				usrs.post(new Runnable() {
    					@Override
    					public void run() {
    				        usrs.setAdapter(mAdapter);
    				        db.close();
    						//do something like spinner.add items here 
    						//here your spinner is 'brands'
    					}
    				});
    			
    		}
    		}).start();
    	
    }

    private SQLiteDatabase getReadDB(){
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
			db = myDbHelper.getReadableDatabase();
	
		}catch(SQLException sqle){
			throw sqle;
		}
		return db;
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
    
//**************Housekeeping functions**********************
    @Override
	protected void onStop(){
	      super.onStop();
	      Log.v("In onStop"," Ready to stop activity!!!");
	   }
	
	//Fires after the OnStop() state
	   @Override
	protected void onDestroy() {
	      super.onDestroy();
	      this.finish();
	      Log.v("In onDestroy"," Activity Will be Destroyed!!!");      
	   }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
