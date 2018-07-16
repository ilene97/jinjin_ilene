package com.example.caucse.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by caucse on 2018-07-16.
 */

public class InfoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.patient_info);

        TextView name = (TextView) findViewById(R.id.name);

        Intent intent = getIntent();
        //name.setText(intent.getStringExtra("patient_name"));

        //TextView information = (TextView)findViewById(R.id.information);
    }



}
