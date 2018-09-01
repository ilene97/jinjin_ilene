package com.example.user.ddoyak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity{

    RecyclerView scheduleList;
    RecyclerView.LayoutManager layoutManager;
    ImageView title, yellowbar;
    ImageButton addButton;
    String start, end;

    public static ArrayList<ScheduleInfo> scheduleitems = new ArrayList<>();
    //ScheduleDBHelper scddbHelper = new ScheduleDBHelper(getApplicationContext(), "SCHEDULE_DB.db",null,1);

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

        //scddbHelper.getAllData();
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