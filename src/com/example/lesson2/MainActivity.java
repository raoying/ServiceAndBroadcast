package com.example.lesson2;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements CounterBroadcastReceiver.CounterBroadcastReceiverListener {
	private boolean serviceStarted;
	private Button button;
	private TextView counterLabel;
	private IntentFilter filter;
	private BroadcastReceiver receiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)this.findViewById(R.id.startButton);
		counterLabel = (TextView)this.findViewById(R.id.textView1);
		Log.d("MainActivity", "onCreate");
		receiver = new CounterBroadcastReceiver();
		filter = new IntentFilter();
		filter.addAction(CounterService.COUNTER_FILTER);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MainActivity", "onStart");
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MainActivity", "onResume");
		this.registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(receiver);
		Log.d("MainActivity", "onPause");
	}	

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("MainActivity", "onStop");
	}	

	@Override
	protected void onDestroy() {
		super.onStart();
		Log.d("MainActivity", "onDestroy");
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId())
	    {
	    case R.id.action_settings:
	    	launchSettingsScreen();
	        break;
	    case R.id.action_new_item:
	    	launchSettingsScreen();
	        break;
	    }
	    return true;
	}
	
	public void onClick(View view) {
		Intent i = new Intent(this, CounterService.class);
		if(!serviceStarted) {			
			this.startService(i);
			button.setText("Stop");
		}
		else {
			this.stopService(i);
			button.setText("Start");
		}
		serviceStarted = !serviceStarted;
	}

	private void launchSettingsScreen() {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

	@Override
	public void counterReceived(int counter) {
		counterLabel.setText(String.valueOf(counter));
		
	}

}
