package com.example.testg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.os.Message;

public class SplashScreen extends Activity{

	private static int SPLASH_TIME_OUT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//This will handle the splash screen running for SPLASH_TIME_OUT
		new Handler().postDelayed(new Runnable(){
			
			@Override
			public void run(){
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
			
			
				startActivity(i);
			
				finish();
			}
		}, SPLASH_TIME_OUT);
		/*	
		 * Gonna try and check if its the first time they opened the app
		 * 	to ask for information
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if(!prefs.getBoolean("first_time",false)){
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("first_time", true);
			editor.commit();
			Intent i = new Intent(SplashScreen.this,MainActivity.class);
			startActivity(i);
			finish();
		}else{
			this.setContentView(R.layout.activity_splash);
			
			String m = "second time";
			Message msg = Message.obtain();
			msg.obj = m;
			msg.setTarget(handler);
			msg.sendToTarget();
			
		}*/
		
	
	}
}
