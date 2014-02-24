package com.example.lesson2;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SettingsActivity extends Activity implements CounterBroadcastReceiver.CounterBroadcastReceiverListener{
	private CounterBroadcastReceiver receiver;
	private IntentFilter filter;
	private TextView counterLabel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Log.d("SettingsActivity", "onCreate");
		counterLabel = (TextView)this.findViewById(R.id.textView1);
		receiver = new CounterBroadcastReceiver();
		filter = new IntentFilter();
		filter.addAction(CounterService.COUNTER_FILTER);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("SettingsActivity", "onStart");
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("SettingsActivity", "onResume");
		this.registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("SettingsActivity", "onPause"); 
		this.unregisterReceiver(receiver);
	}	

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("SettingsActivity", "onStop");
	}	

	@Override
	protected void onDestroy() {
		super.onStart();
		Log.d("SettingsActivity", "onDestroy");
	}

	@Override
	public void counterReceived(int counter) {
		counterLabel.setText(String.valueOf(counter));
		
	}	
}
