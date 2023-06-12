package com.example.reminddo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    private Alarm alarm;
    private TextView name;
    private TextView date;
    private TextView hour;
    private TextView description;
    private static final int DELETE_ALARM_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        name = findViewById(R.id.alarm_details_name);
        date = findViewById(R.id.alarm_details_date);
        hour = findViewById(R.id.alarm_details_hour);
        description = findViewById(R.id.alarm_details_description);
        Intent intent = getIntent();
        alarm = (Alarm) intent.getSerializableExtra("alarm");
        name.setText(alarm.getName());
        date.setText(formatDate(alarm.getDate()));
        String horas = alarm.getHour() + ":" + alarm.getMinutes();
        hour.setText(horas);
        description.setText(alarm.getDescription());

    }
    public String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }


    public void deleteAlarm(View view){
        Intent intent = new Intent();
        int alarmId = this.getIntent().getIntExtra("alarmID",-1);

        intent.putExtra("alarmID",alarmId);
        setResult(RESULT_OK, intent);
        finish();
    }

}
