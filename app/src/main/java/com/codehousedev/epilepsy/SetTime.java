package com.codehousedev.epilepsy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codehousedev.sqlite.InsertSQLite;

import java.util.ArrayList;
import java.util.Calendar;

public class SetTime extends Activity {
    EditText txttime, textdrugname ;
    Spinner category, textamount ;
    Button btninsert ;

    ArrayList<String> textcate, mamount ;

    String H, M ,datacate, dataamount;
    String datatime ;

    public static ArrayList<String> listValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        listValue = new ArrayList<String>();

        textcate  = new ArrayList<>();
        textcate.add("กรุณาเลือกช่วงเวลา");
        textcate.add("ก่อนอาหาร");
        textcate.add("หลังอาหาร");
        mamount = new ArrayList<>();
        mamount.add("0");
        mamount.add("1");
        mamount.add("2");
        mamount.add("3");
        mamount.add("4");
        mamount.add("5");
        mamount.add("6");
        mamount.add("7");
        mamount.add("8");
        mamount.add("9");
        mamount.add("10");

        init();
    }
    public void init()
    {
        txttime = findViewById(R.id.txttime);
        txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "");
            }
        });
        textdrugname = (EditText)findViewById(R.id.textdrugname);

        category = (Spinner)findViewById(R.id.category);
        ArrayAdapter setcate = new ArrayAdapter(this,android.R.layout.simple_spinner_item,textcate);
        category.setAdapter(setcate);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                datacate=  textcate.get(i) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textamount = (Spinner)findViewById(R.id.textamount);
        ArrayAdapter setamount = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mamount);
        textamount.setAdapter(setamount);
        textamount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataamount = mamount.get(i) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btninsert = (Button)findViewById(R.id.btninsert);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertSQLite insert = new InsertSQLite(SetTime.this);
                insert.insertsettimedrug(H,M,datacate, textdrugname.getText().toString(),dataamount,datatime);
                Intent back = new Intent(SetTime.this,MainSettime.class);
                startActivity(back);
            }
        });

    }
    class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        int callCount = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(callCount==0){
                // Do something with the time chosen by the user
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);

                setAlarm(cal);
            }
            callCount++;
        }
    }

    private void setAlarm(Calendar targetCal){

        listValue.add(targetCal.getTime()+"");
        datatime = String.valueOf(targetCal.getTime()) ;
        Log.d("Test",listValue.toString());
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
//        listAlarm.setAdapter(adapter);
        txttime.setText(targetCal.getTime().toString());
        final int _id = (int) System.currentTimeMillis();

        H = String.valueOf(targetCal.get(Calendar.HOUR_OF_DAY));
        M = String.valueOf(targetCal.get(Calendar.MINUTE));

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
//        listAlarm.setAdapter(adapter);
    }
}
