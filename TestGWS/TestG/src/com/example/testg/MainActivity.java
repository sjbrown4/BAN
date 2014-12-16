package com.example.testg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.testg.MESSAGE";
    protected void onCreate(Bundle savedInstanceState) {
     
    	/*Sheena Brown
    	 * 
    	 * make the main activity run the text page, also set button listener to wait 
    	 * for button click
    	 * 
    	 * TODO: make the app recognize when the application has been run for the first time
    	 */
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Button switchit = (Button) findViewById(R.id.button1);
        
        switchit.setOnClickListener(new View.OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		/*Sheena Brown:
        		 * 
        		 * Make a new intent to send the information in the text fields to the MainViewActiviy
        		*/
        	
        		Intent intent = new Intent(MainActivity.this, MainViewActivity.class);
            	EditText first = (EditText)findViewById(R.id.editText1);
            	EditText last = (EditText)findViewById(R.id.editText2);
            //	EditText phone = (EditText)findViewById(R.id.editText3);
            	//EditText email = (EditText)findViewById(R.id.editText4);
            	String message = "Welcome, " + first.getText().toString() + " " + last.getText().toString() +"!";
            	intent.putExtra(EXTRA_MESSAGE, message);
            	startActivity(intent);
            
        	}
        });
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    
    }
    
}
