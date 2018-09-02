package com.example.user.ddoyak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity{

    RecyclerView scheduleList;
    RecyclerView.LayoutManager layoutManager;
    ImageView title, yellowbar;
    ImageButton addButton;
    String startTemp;
    String endTemp;
    String dateTemp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("OUTING");;

    public static ArrayList<ScheduleInfo> scheduleitems = new ArrayList<>();

    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.E");
    public SimpleDateFormat timeFormat = new SimpleDateFormat("aa hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Title 생성
        title = (ImageView)findViewById(R.id.title);
        yellowbar=(ImageView)findViewById(R.id.yellowbar);

        //스케줄리스트_RecyclerView 선언
        scheduleList = (RecyclerView)findViewById(R.id.schedule_list);
        scheduleList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scheduleList.setLayoutManager(layoutManager);

        //리사이클러뷰 어댑터와 어레이리스트 연결
        final ListAdapter myAdapter = new ListAdapter(getApplicationContext(), scheduleitems);
        scheduleList.setAdapter(myAdapter);

        scheduleitems.clear();          //어레이리스트 초기화
        //firebase data 가져오기
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot outingData : dataSnapshot.getChildren()){                  //블럭 반복
                    String titleData = outingData.getValue().toString();                     //한 블럭 전체 데이터를 한 문자열로 가져옴. ','를 토큰으로 나눠서 저장해야함.
                    scheduleitems.add(new ScheduleInfo(dateTemp, startTemp, endTemp, titleData));       //어레이리스트에 저장
                }
                myAdapter.notifyDataSetChanged();                                      //어레이리스트에 저장한 items >> 어댑터 업데이트
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //일정 추가 버튼
        addButton = (ImageButton)findViewById(R.id.addButton);
        addButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AddSchedule_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}