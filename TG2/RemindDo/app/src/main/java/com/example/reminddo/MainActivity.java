package com.example.reminddo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TextView> listaAlarmesView;
    private ArrayList<Alarm> listaAlarmes;
    private LinearLayout linearLayout;


    private static final int CREATE_ALARM_REQUEST_CODE = 1;
    private static final int DELETE_ALARM_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaAlarmes = new ArrayList<>();
        listaAlarmesView = new ArrayList<>();
        linearLayout = findViewById(R.id.linear_layout);
    }
    public void createAlarm(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, CREATE_ALARM_REQUEST_CODE);
    }
    public void alarmDetails(View view){
        TextView clickedTextView = (TextView) view;
        int viewId = clickedTextView.getId();
        Alarm alarm = listaAlarmes.get(viewId);
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("alarm",alarm);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_ALARM_REQUEST_CODE && resultCode == RESULT_OK) {
            Alarm alarm = (Alarm) data.getSerializableExtra("alarm");
            if (alarm != null) {
                listaAlarmes.add(alarm);
                deleteAlarmsTextView();
                drawAlarms(listaAlarmes);
            }
        }
        if (requestCode == DELETE_ALARM_REQUEST_CODE && resultCode == RESULT_OK) {
            Alarm alarm = (Alarm) data.getSerializableExtra("alarmToDelete");
            if (alarm != null) {
                listaAlarmes.remove(listaAlarmes.indexOf(alarm));
                deleteAlarmsTextView();
                drawAlarms(listaAlarmes);
            }
        }
    }
    public void drawAlarms(ArrayList<Alarm> alarms){
        for(int i = 0; i<alarms.size(); i++) {
            Alarm alarm = alarms.get(i);
            TextView alarmTextView = new TextView(this);
            String text = alarm.getName() + " " + formatDate(alarm.getDate()) +" " + alarm.getHour() +":"+ alarm.getMinutes();
            alarmTextView.setText(text);
            alarmTextView.setTextSize(25);
            alarmTextView.setId(i);
            alarmTextView.setOnClickListener(this::alarmDetails);
            listaAlarmesView.add(alarmTextView);
            linearLayout.addView(alarmTextView);
        }
    }

    public String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    //Menos eficiente do que adicionar 1 a 1 mas mais fácil para quando for necessário apagar 1 especifico
    // pois é só apagar só na arraylist em vez de procurar pelo ID
    public void deleteAlarmsTextView(){
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof TextView) {
                linearLayout.removeView(childView);
            }
        }
    }




}









//Intent intent = new Intent(MainActivity.this, SecondActivity.class);

// Optional: Pass data to the second activity using extras
//intent.putExtra("key", value); // Replace "key" with your own key and value with the actual data

// Start the second activity
//startActivity(intent);