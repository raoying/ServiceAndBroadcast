package com.example.lesson2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class CounterService extends Service {
	public static final String COUNTER_FILTER = "counter";
	private Timer timer = new Timer();
	private int counter = 0;
	private String serviceInfo = "This is a local service";
	
	 // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();
    
    public class LocalBinder extends Binder {
    	CounterService getService() {
            return CounterService.this;
        }
    }
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
    }
    
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {
		timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
            	counter++;
            	Intent i = new Intent();
            	i.putExtra(COUNTER_FILTER, counter);
            	i.setAction(COUNTER_FILTER);
            	CounterService.this.sendBroadcast(i);
            }
        }, 0, 1000);
		return START_STICKY;
	}

	public String getInfo() {
		return serviceInfo;		
	}
}
