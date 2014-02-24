package com.example.lesson2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity implements CounterBroadcastReceiver.CounterBroadcastReceiverListener{
	private CounterBroadcastReceiver receiver;
	private IntentFilter filter;
	private TextView counterLabel;

	private CounterService mBoundService;

	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        mBoundService = ((CounterService.LocalBinder)service).getService();
	        String serviceInfo = mBoundService.getInfo();
	        serviceInfoLabel.setText(serviceInfo);
	        // Tell the user about this for our demo.
	        Toast.makeText(SettingsActivity.this, "Counter service connected:" + serviceInfo,
	                Toast.LENGTH_SHORT).show();
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        mBoundService = null;
	        Toast.makeText(SettingsActivity.this, "Counter service disconnected",
	                Toast.LENGTH_SHORT).show();
	    }
	};
	private boolean mIsBound;
	private TextView serviceInfoLabel;

	void doBindService() {
	    // Establish a connection with the service.  We use an explicit
	    // class name because we want a specific service implementation that
	    // we know will be running in our own process (and thus won't be
	    // supporting component replacement by other applications).
	    bindService(new Intent(this, 
	            CounterService.class), mConnection, Context.BIND_AUTO_CREATE);
	    mIsBound = true;
	}

	void doUnbindService() {
	    if (mIsBound) {
	        // Detach our existing connection.
	        unbindService(mConnection);
	        mIsBound = false;
	    }
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Log.d("SettingsActivity", "onCreate");
		counterLabel = (TextView)this.findViewById(R.id.textView1);
		serviceInfoLabel = (TextView)findViewById(R.id.serviceInfoTextView);
		receiver = new CounterBroadcastReceiver();
		filter = new IntentFilter();
		filter.addAction(CounterService.COUNTER_FILTER);
	
		doBindService();
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
		super.onDestroy();
		Log.d("SettingsActivity", "onDestroy");
		doUnbindService();
	}

	@Override
	public void counterReceived(int counter) {
		counterLabel.setText(String.valueOf(counter));
		
	}	
}
