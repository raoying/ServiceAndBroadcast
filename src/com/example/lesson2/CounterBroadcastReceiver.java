package com.example.lesson2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CounterBroadcastReceiver extends BroadcastReceiver {
	public interface CounterBroadcastReceiverListener {
		public void counterReceived(int counter); 
	}
	
	@Override
	public void onReceive(Context ctx, Intent intent) {
		if(ctx instanceof CounterBroadcastReceiverListener) {
			int counter = intent.getIntExtra(CounterService.COUNTER_FILTER, -1);
			((CounterBroadcastReceiverListener)ctx).counterReceived(counter);
		}
	}

}
