package com.example.wordfindertwo;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * Main Timer for Game class
 */
public enum GameTimer {

	Instance;

	private CustomCountDownTimer timer;
	private long initialTimerValue;

	/**
	 * sets up the timer with the given time. Tick period is 1000ms
	 * 
	 * @param initialTimerValue
	 *            the timer duration. Given in milliseconds.
	 */
	public void setup(long initialTimerValue) {
		this.initialTimerValue = initialTimerValue;
		Log.d("GameTimer", "Timer setting up. initial value is "
				+ this.initialTimerValue);
		this.timer = new CustomCountDownTimer(this.initialTimerValue, 1000) {

			@Override
			public void tick(long millisUntilFinished) {
				Log.d("GameTimer",
						"Timer value is   " + this.getMillisUntilFinished());
				Game.game.timerUpdated(millisUntilFinished);
			}

			@Override
			public void finish() {
				Log.d("GameTimer",
						"Timer stopped at " + this.getMillisUntilFinished());
				Game.game.timerFinished();
			}
		};
	}

	public void start() {
		Log.d("GameTimer",
				"Timer starting. value is "
						+ this.timer.getMillisUntilFinished());
		this.timer.start();
	}

	public void stop() {
		Log.d("GameTimer",
				"Timer stopping. value is "
						+ this.timer.getMillisUntilFinished());
		this.timer.cancel();
	}

	public void pause() {
		this.timer.cancel();
		Log.d("GameTimer",
				"Timer pausing. value is "
						+ this.timer.getMillisUntilFinished());
		this.setup(this.timer.getMillisUntilFinished());
	}

	public void resume() {
		Log.d("GameTimer",
				"Timer resuming. value is "
						+ this.timer.getMillisUntilFinished());
		this.timer.start();
	}

	/**
	 * read out the remaining time.
	 * 
	 * @return the remaining time in milliseconds. Note: the value that is read
	 *         out from is updated every 1000 milliseconds.
	 */
	public long getTimeRemaining() {
		return this.timer.getMillisUntilFinished();
	}

	private abstract class CustomCountDownTimer extends CountDownTimer {

		private long duration;

		CustomCountDownTimer(long duration, long period) {
			super(duration, period);
			this.duration = duration;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			this.duration = millisUntilFinished;
			this.tick(millisUntilFinished);
		}

		@Override
		public void onFinish() {
			this.onTick(0);
			this.finish();
		}

		abstract void tick(long millisUntilFinished);

		abstract void finish();

		long getMillisUntilFinished() {
			return this.duration;
		}

	}

}
