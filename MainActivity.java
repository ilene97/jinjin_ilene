package com.example.caucse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] items = {"child1", "child2","child3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        ListAdapter adapter = new ImageAdapter(this, items);

        listView.setAdapter(new ImageAdapter()Adapter<>()adapter);

        listView.setOnItemClickListener(onItemClickListener);


    }

    private AdapterView.onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //String item = String.valueOf(parent.getItemAtPosition(i));
            Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
            //intent.putExtra("patient_name",item);
            startActivity(intent);
        }
    };
}

