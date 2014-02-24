package com.example.lesson2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("MainActivity", "onCreate");
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
	}
	
	@Override
	protected void onPause() {
		super.onPause();
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
		launchSettingsScreen();
	}

	private void launchSettingsScreen() {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

}
