package com.example.user.ddoyak;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineInfo extends AppCompatActivity {

    private TextView oneday;
    private TextView day;
    private EditText info;

    private Button plusOneday;
    private Button minusOneday;
    private Button plusDay;
    private Button minusDay;
    private Button save;

    private int onedayNum;
    private int dayNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "DB.db",null,1);

        oneday = (TextView) findViewById(R.id.oneday);
        day = (TextView) findViewById(R.id.day);
        info = (EditText) findViewById(R.id.info);

        onedayNum = 0;
        dayNum = 0;

        plusOneday = (Button) findViewById(R.id.plus);
        plusOneday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onedayNum < 6)
                    onedayNum++;
                oneday.setText(String.valueOf(onedayNum));
            }
        });

        minusOneday = (Button) findViewById(R.id.minus);
        minusOneday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onedayNum > 0)
                    onedayNum--;
                oneday.setText(String.valueOf(onedayNum));
            }
        });

        plusDay = (Button) findViewById(R.id.plus2);
        plusDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayNum < 9)
                    dayNum++;
                day.setText(String.valueOf(dayNum));
            }
        });

        minusDay = (Button) findViewById(R.id.minus2);
        minusDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayNum > 0)
                    dayNum--;
                day.setText(String.valueOf(dayNum));
            }
        });

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "약 정보를 저장했습니다.", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MedicineInfo.this, AlarmSetting.class);
                intent.putExtra("info",info.getText().toString());
                intent.putExtra("oneday",String.valueOf(onedayNum));
                intent.putExtra("day",String.valueOf(dayNum));

                startActivity(intent);
                dbHelper.delete();

                for(int i = 0; i< onedayNum * dayNum; i++) {
                    dbHelper.insert("noname", "yyyy#MM#dd#hh#mm");
                }
                Log.d("DATA_INSERTION", "onClick: datainsertion success!");
            }
        });
    }
}
