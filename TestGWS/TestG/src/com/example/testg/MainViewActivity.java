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
		 * page that mainacivity button goes to, is the page that will display the 'home' area 
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
	    TextView msg =(TextView)findViewById(R.id.textView1);
	
	    msg.setTextSize(12);
	    msg.setText(message);

	    
	    ImageButton help = (ImageButton)findViewById(R.id.helpButton);
	    help.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    ImageButton setti = (ImageButton)findViewById(R.id.editButton);
	    setti.setOnClickListener(new View.OnClickListener(){
	    	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
	    ImageButton view = (ImageButton)findViewById(R.id.viewButton);
	    view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});	
	    
	    ImageButton download = (ImageButton)findViewById(R.id.downloadButton);
	    download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    ImageButton startit = (ImageButton)findViewById(R.id.startButton);
	    startit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	   
		 
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	 
}
