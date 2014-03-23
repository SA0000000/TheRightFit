package com.pearlhacks.therightfit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class GetSizeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();		
		int bid = extras.getInt("BRAND_ID",1);
		int uid = extras.getInt("USER_ID",1);
		setContentView(R.layout.activity_get_size);
		Person user = getMeasurements(uid);
		BrandMeasurements bm = getBrand(bid);
		String sizes[] = new String[3];
		sizes = callGetSize(user, bm);
		//Use these when you have the string array with all the values to set
		TextView top =(TextView)findViewById(R.id.txt_top_size);
		TextView dress =(TextView)findViewById(R.id.txt_dress_size);
		TextView bottom =(TextView)findViewById(R.id.txt_bottom_size);
		TextView user_name =(TextView)findViewById(R.id.txt_usr_name);
		TextView brand_name =(TextView)findViewById(R.id.txt_brand_name);
		top.setText(sizes[0]);
		dress.setText(sizes[2]);
		bottom.setText(sizes[1]);
		user_name.setText(user.Name);
		brand_name.setText(bm.Name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_size, menu);
		return true;
	}
	private String[] callGetSize(Person p, BrandMeasurements bm){
		String topSize = getSize.getTopSize(bm.tList, p.Bust);
		String botSize = getSize.getBottomSize(bm.bList, p.Hip, p.Waist, p.Inseam);
		String dressSize = getSize.getDressSize(bm.dList, p.Bust, p.Waist);
		String s[] = new String[3];
		s[0] = topSize;
		s[1] = botSize;
		s[2] = dressSize;
		return s;
	}
	private Person getMeasurements(int uid){
		SQLiteDatabase db = getReadDB();
    	Cursor c = db.rawQuery("SELECT * FROM user WHERE _id ="+Integer.toString(uid), null);
    	c.moveToFirst();
    	String name = c.getString(1);
    	float bust = c.getFloat(2);
    	float waist = c.getFloat(3);
    	float hip = c.getFloat(4);
    	float inseam = c.getFloat(5);
    	Person p = new Person(name, bust, waist, hip, inseam);
		return p;
	}
	
	private BrandMeasurements getBrand(int bid){
		SQLiteDatabase db = getReadDB();
    	Cursor cur = db.rawQuery("SELECT name FROM brand WHERE _id ="+Integer.toString(bid), null);
    	cur.moveToFirst();
    	String name = cur.getString(0);
		//tops
    	cur = db.rawQuery("SELECT * FROM brand_bottom WHERE bid ="+Integer.toString(bid), null);
    	List<Bottom> bList = new ArrayList<Bottom>();
    	cur.moveToFirst();
    	while (cur.isAfterLast() == false) 
    	{
    		String size = cur.getString(1);
    		char type = cur.getString(2).charAt(0);
    		float waist = cur.getFloat(3);
    		float hip = cur.getFloat(4);
    		float inseam = cur.getFloat(5);
    	    bList.add(new Bottom(size, type, waist, hip, inseam));
    	    cur.moveToNext();
    	}
    	cur = db.rawQuery("SELECT * FROM brand_top WHERE bid ="+Integer.toString(bid), null);
    	List<Top> tList = new ArrayList<Top>();
    	cur.moveToFirst();
    	while (cur.isAfterLast() == false) 
    	{
    		String size = cur.getString(1);
    		char type = cur.getString(2).charAt(0);
    		float bust = cur.getFloat(3);
    	    tList.add(new Top(size, type, bust));
    	    cur.moveToNext();
    	}
    	cur = db.rawQuery("SELECT * FROM brand_dress WHERE bid ="+Integer.toString(bid), null);
    	List<Dress> dList = new ArrayList<Dress>();
    	cur.moveToFirst();
    	while (cur.isAfterLast() == false) 
    	{
    		String size = cur.getString(1);
    		char type = cur.getString(2).charAt(0);
    		float bust = cur.getFloat(3);
    		float waist = cur.getFloat(4);
    	    dList.add(new Dress(size, type, bust, waist));
    	    cur.moveToNext();
    	}
    	BrandMeasurements bm = new BrandMeasurements(name, bList, tList, dList);
    	return bm;
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

}
