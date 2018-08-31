package com.example.caucse.ddoyak;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity{

    RecyclerView scheduleList;
    RecyclerView.LayoutManager layoutManager;
    ImageView title, yellowbar;
    ImageButton addButton;
    Calendar calendar;
    String start, end;

    ScheduleDBHelper scddbHelper = new ScheduleDBHelper(getApplicationContext(), "SCHEDULE_DB.db",null,1);
    public static ArrayList<ScheduleInfo> scheduleitems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Title 생성
        title = (ImageView)findViewById(R.id.title);
        yellowbar=(ImageView)findViewById(R.id.yellowbar);

        //스케줄리스트_RecyclerView
        scheduleList = (RecyclerView)findViewById(R.id.schedule_list);
        scheduleList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scheduleList.setLayoutManager(layoutManager);

        scddbHelper.getAllData();

        final ListAdapter myAdapter = new ListAdapter(getApplicationContext(), scheduleitems);
        scheduleList.setAdapter(myAdapter);


        //일정 추가 버튼
        addButton = (ImageButton)findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(),AddSchedule_Activity.class);
                startActivity(intent);
                finish();
                //myAdapter.notifyDataSetChanged();
            }
        });

    }



}