package com.pearlhacks.therightfit;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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
				Intent myintent =new Intent(MainActivity.this,GetSizeActivity.this);
				
				//here add code to pass data to the intent
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
