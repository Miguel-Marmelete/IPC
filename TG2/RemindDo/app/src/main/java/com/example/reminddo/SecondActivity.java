package com.example.reminddo;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    EditText alarmNameInput;
    EditText dayInput;
    EditText monthInput;
    EditText yearInput;
    EditText description;
    EditText hourInput;
    EditText minutesInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

    }
    public void createAndSendAlarm(View view ){
        dayInput = findViewById(R.id.day_input);
        int dayInputValue = Integer.parseInt(dayInput.getText().toString());

        monthInput = findViewById(R.id.month_input);
        int monthInputValue = Integer.parseInt(monthInput.getText().toString());

        yearInput = findViewById(R.id.year_input);
        int yearInputValue = Integer.parseInt(yearInput.getText().toString());

        hourInput = findViewById(R.id.hour_input);
        int hourInputValue = Integer.parseInt(hourInput.getText().toString());

        minutesInput = findViewById(R.id.minutes_input);
        int minutesInputValue = Integer.parseInt(minutesInput.getText().toString());

        alarmNameInput = findViewById(R.id.alarme_name_input);
        String alarmNameValue = alarmNameInput.getText().toString();

        description = findViewById(R.id.description_input);
        String descriptionInputValue = description.getText().toString();

        if(verifyInput(dayInputValue,monthInputValue,yearInputValue,hourInputValue,minutesInputValue)){

        Calendar calendar = Calendar.getInstance();
        calendar.set(yearInputValue, monthInputValue - 1, dayInputValue);
        Date alarmDate = calendar.getTime();
        Alarm alarm = new Alarm(alarmNameValue,alarmDate,descriptionInputValue,hourInputValue,minutesInputValue);

        sendAlarmToMainActivity(alarm);
        }else{
            errorMessage();
        }

    }
    public void errorMessage(){
        Toast toast = Toast.makeText(this, "Dados errados, verifique a data e horas", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,10,10);
        toast.show();
    }

    public void sendAlarmToMainActivity(Alarm alarm){
        Intent intent = new Intent();
        intent.putExtra("alarm", alarm);
        setResult(RESULT_OK,intent);
        Log.d("Second Activity", "End SecondActivity");
        finish();
    }
    public boolean verifyInput(int dayInputValue,int monthInputValue,int yearInputValue,int hourInputValue,int minutesInputValue){
        return (dayInputValue<=31) &&(monthInputValue<=12)&&(yearInputValue>=2023)&&(hourInputValue<24) &&(minutesInputValue<=60);

    }

}