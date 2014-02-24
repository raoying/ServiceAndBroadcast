package com.example.lesson2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SettingsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Log.d("SettingsActivity", "onCreate");
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
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("SettingsActivity", "onPause"); 
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
}
