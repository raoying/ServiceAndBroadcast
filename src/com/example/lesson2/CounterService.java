package com.example.lesson2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class CounterService extends Service {
	public static final String COUNTER_FILTER = "counter";
	private Timer timer = new Timer();
	private int counter = 0;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
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
}
