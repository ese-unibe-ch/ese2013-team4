package com.example.wordfindertwo.customs;

import android.os.CountDownTimer;

public abstract class DelayedOperation extends CountDownTimer {

	public DelayedOperation (long time) {
		super(time, time);
		this.start();
	}
	
	@Override
	public void onTick(long millisUntilFinished) {
		// NO-OP
	}
	
	@Override
	public void onFinish() {
		this.operation();
	}
	
	public abstract void operation();

}
