package com.Titan.healthmonitor;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

//TODO ANDREW combine UI application and Bluetooth Device components into single application 
//TODO ANDREW begin work on Real time graph display for visual output of Data
//TODO SHEENA begin work on data storage options 
//TODO SHEENA begin work on emergency alert message system 
//TODO SHEENA begin work on Swiping options for user interface
public class MainActivity extends ActionBarActivity {
	 Button On,Off,Visible,list, Scan;
	 private BluetoothAdapter BA;
	 private Set<BluetoothDevice>pairedDevices;
	 private ListView new_devices_list;
	 private ListView paired_device_list;
	 private ArrayAdapter<String> newDevicesArrayAdapter;		
	 private ArrayAdapter<String> pairedDevicesArrayAdapter;	
	 private BluetoothSocket socket;

	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Assign variables their respective User Interface counterparts
         */
        On = (Button)findViewById(R.id.button1);
        Off = (Button)findViewById(R.id.button2);
        Visible = (Button)findViewById(R.id.button3);
        list = (Button)findViewById(R.id.button4);
        new_devices_list = (ListView)findViewById(R.id.listView1);
        paired_device_list = (ListView)findViewById(R.id.listView1);
        Scan = (Button)findViewById(R.id.button5);
        
        //Get bluetooth adapter if one exists or else terminate APP
        BA = BluetoothAdapter.getDefaultAdapter();
        if(BA == null){
        	Toast.makeText(this, "No BlueTooth device detected", Toast.LENGTH_LONG).show();
        	finish(); //exit application
        	return;
        }
        
        //set arrayAdapter for listView
        //array adapters allow modifications to User interface objects like listviews, textboxes, etc
        newDevicesArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        new_devices_list.setOnItemClickListener(mDeviceClickListener);
        new_devices_list.setAdapter(newDevicesArrayAdapter);
        pairedDevicesArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        paired_device_list.setAdapter(pairedDevicesArrayAdapter);
        
        
        //register the Bluetooth BroadcastReciever
        this.registerReceiver(ActionFoundReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }
  
    //TODO ANDREW allow device to connect directly to sensor board with minimal user interaction 
    //turn on device's bluetooth adapter
    //update user with toast message	
    public void on(View view){
        if (!BA.isEnabled()) {
           Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
           startActivityForResult(turnOn, 0);
           Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();
        }
        else{
           Toast.makeText(getApplicationContext(),"Already on",Toast.LENGTH_LONG).show();
           }
     }
    
    //initiate bluetooth scan for available devices
    public void Scan(View view){
        setProgressBarIndeterminateVisibility(true);
        Toast.makeText(getApplicationContext(),"Starting BlueToothScan",Toast.LENGTH_LONG).show();
    	newDevicesArrayAdapter.clear();
    	BA.startDiscovery();
   
     }
    
    //display list of paired devices...probably limit to just the sensor board in final application
     public void list(View view){
    	 //retrieve list of paired devices
        pairedDevices = BA.getBondedDevices();
        
        ArrayList<String> list = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices){
           list.add(bt.getName());
        }

        //update user with toast message	
        Toast.makeText(getApplicationContext(),"Showing Paired Devices",
        Toast.LENGTH_SHORT).show();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        paired_device_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

     }
     
     //disable bluetooth adapter
     public void off(View view){
        BA.disable();
        Toast.makeText(getApplicationContext(),"Turned off" ,
        Toast.LENGTH_LONG).show();
     }
     
     public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter. ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
     }


    @Override
    //not used at this time
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // Handle action bar item clicks here specify parent activity inj AndroidManifest.xml
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    //receiver for bluetooth devices
    //TODO ANDREW connect to bitalino sensor
    //TODO ANDREW import bitalino Java SDK libraries to enable packet transfer
    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){
    	  @Override
    	  public void onReceive(Context context, Intent intent) {
    	   String action = intent.getAction();
    	   ArrayList<String> list = new ArrayList<String>();
    	   if(BluetoothDevice.ACTION_FOUND.equals(action)) {
    	             BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    	             if(device.getBondState() != BluetoothDevice.BOND_BONDED)
    	             newDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
    	             list.add(device.getName() + "\n" + device.getAddress());
    	             Toast.makeText(getApplicationContext(),"Found Device: " + device.getName(),
    	            	        Toast.LENGTH_LONG).show();
    	             final ArrayAdapter<String> adapter = new ArrayAdapter<String>
    	             (context,android.R.layout.simple_list_item_1, list);
    	             paired_device_list.setAdapter(adapter);
    	             newDevicesArrayAdapter.notifyDataSetChanged();
    	   } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
               setProgressBarIndeterminateVisibility(false);
               if (newDevicesArrayAdapter.getCount() == 0) {
  	             Toast.makeText(getApplicationContext(),"No Devices Found",
	            	        Toast.LENGTH_LONG).show();
               }
           }
    	  }
    };
    
    // The on-click listener for all devices in the ListViews
      private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Cancel discovery because it's costly and we're about to connect
            BA.cancelDiscovery();

            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            Toast.makeText(getApplicationContext(),"link to address: " + address,
        	        Toast.LENGTH_LONG).show();
            // Create the result Intent and include the MAC address
           // Intent intent = new Intent();
            //intent.putExtra(EXTRA_DEVICE_ADDRESS, address);

            // Set result and finish this Activity
           // setResult(Activity.RESULT_OK, intent);
          //  finish();
        }


    };
    
}
