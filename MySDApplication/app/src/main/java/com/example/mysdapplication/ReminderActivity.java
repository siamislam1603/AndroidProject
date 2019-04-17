package com.example.mysdapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener {
    String userGet;
    public int notificationId=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Intent i=getIntent();
        userGet=i.getStringExtra("userFound");
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);
        Intent intent = new Intent(ReminderActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(ReminderActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (v.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
