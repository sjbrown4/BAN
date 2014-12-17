package com.example.testg;

import  android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		/*Sheena Brown
		 * 
		 * page that mainactivity button goes to, is the page that will display the 'home' area 
		 * as well as display some of the information to the user (such as name)
		 * 
		 * TODO: Get the information saved in the settings part
		 * TODO: Add swipe function
		 * TODO: get the various screens set up for button click e.g. settings
		 * */
        setContentView(R.layout.activity_main_view);
		 // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	
	    //put name in menu
	    TextView msg =(TextView)findViewById(R.id.textView7);
	
	    msg.setTextSize(12);
	    msg.setText(message);
/*
 * Sheena Brown
 * 
 * Handling button clicks
 * 
 */
	    
	    ImageButton help = (ImageButton)findViewById(R.id.helpButton);
	    help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainViewActivity.this, HelpActivity.class);
            	startActivity(intent);
				
			}
		});
	    
	    ImageButton setti = (ImageButton)findViewById(R.id.editButton);
	    setti.setOnClickListener(new View.OnClickListener(){
	    	@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(MainViewActivity.this,SettingActivity.class);
				startActivity(intent1);
				
			}
	    });
	    
	    ImageButton view = (ImageButton)findViewById(R.id.viewButton);
	    view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainViewActivity.this,ViewActivity.class);
				startActivity(intent2);
			}
		});	
	    
	    ImageButton download = (ImageButton)findViewById(R.id.downloadButton);
	    download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent(MainViewActivity.this, DownloadActivity.class);
				startActivity(intent3);
				
			}
		});
	    
	    ImageButton startit = (ImageButton)findViewById(R.id.startButton);
	    startit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent4 = new Intent(MainViewActivity.this, StartitActivity.class);
				startActivity(intent4);
			}
		});
	    
	    ImageButton bluetooth = (ImageButton)findViewById(R.id.bluetoothButton);
	    bluetooth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
					//Intent intent5 = new Intent(MainViewActivity.this, theclassofyourbluetooth.class);
					//startActivity(intent5);
				
			}
		});
	   
		 
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}
	 
}
