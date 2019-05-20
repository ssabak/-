package com.example.refrigerator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Calendar;

public class Note_Service extends Service {

	final static String DB_ALARM = "alarmlistdb";
	BackThread_DB_check mThread_DB_check;

	private NotificationManager mNotification;
	Notification notification;
	static final int NOTI_DBNOTE_ID = 2;
	String message_title = "";
	int alarm_id_backup = 0;
	private WordDBHelper mWordDBHelper;
	protected static SQLiteDatabase alarmdb;

	public void onCreate() {
		super.onCreate();

		mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		mThread_DB_check = new BackThread_DB_check(mHandler_DB_check);
		mThread_DB_check.setDaemon(true);
		mThread_DB_check.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// mNotification.cancel(chakamain_service.NOTI_ID);

	}

	public int osStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void check_DB_for_setup() {
		int setting_time_founded = 0;
		int curYear;
		int curMonth;
		int curDate;

		int curNoon;
		int curHour;
		int curMin;

		Calendar cal = Calendar.getInstance();

		curYear = cal.get(Calendar.YEAR);
		curMonth = cal.get(Calendar.MONTH);
		curDate = cal.get(Calendar.DATE);

		curHour = cal.get(Calendar.HOUR_OF_DAY);
		curMin = cal.get(Calendar.MINUTE);

		Cursor cursor_alarm_search;
		mWordDBHelper = new WordDBHelper(this);

		String[] columns_alarm_search = { "memo_title", "alarm_con",
				"alarm_year", "alarm_month", "alarm_day", "alarm_hour",
				"alarm_minute", "alarm_id" };

		 alarmdb = mWordDBHelper.getReadableDatabase();

		cursor_alarm_search = alarmdb.query(false, DB_ALARM,
				columns_alarm_search, null, null, null, null, null, null);

		cursor_alarm_search.moveToFirst();
		while (setting_time_founded == 0 && !cursor_alarm_search.isAfterLast()) {
			if (cursor_alarm_search.getInt(4) == curDate) {
				if (cursor_alarm_search.getInt(3) == curMonth) {
					if (cursor_alarm_search.getInt(2) == curYear) {
						if (cursor_alarm_search.getInt(6) == curMin) {
							if (cursor_alarm_search.getInt(5) == curHour) {
								String cursor_message_title = cursor_alarm_search
										.getString(1);
								if ((!message_title.equals(cursor_message_title))
										&& (alarm_id_backup != cursor_alarm_search
												.getInt(7)))
									setting_time_founded++;
								message_title = cursor_alarm_search
										.getString(1);
								alarm_id_backup = cursor_alarm_search.getInt(6);
							}
						}
					}
				}
			}
			cursor_alarm_search.moveToNext();
		}

		if (setting_time_founded != 0) {
			alarm_enrol();

			cursor_alarm_search.close();
			alarmdb.close();

		} else {
			cursor_alarm_search.close();
			alarmdb.close();
		}
	}

	public void alarm_enrol() {

		notification = new Notification(R.drawable.icon, "알람 입니다!",
				System.currentTimeMillis());

		PendingIntent content = PendingIntent.getActivity(this, 0, new Intent(
				this, Activity5.class), 0);

		notification.setLatestEventInfo(this, message_title, "알겠지?", content);

		mNotification.notify(Note_Service.NOTI_DBNOTE_ID, notification);
	}

	Handler mHandler_DB_check = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				check_DB_for_setup();
			}
		}
	};
}

class BackThread_DB_check extends Thread {

	Handler mHandler;

	BackThread_DB_check(Handler handler) {
		mHandler = handler;
	}

	public void run() {
		while (true) {
			mHandler.sendEmptyMessage(1);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				;
			}
		}
	}
}

