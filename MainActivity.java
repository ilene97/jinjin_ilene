package com.example.ilene.ddoyak_gardians;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"child1", "child2","child3"};
        ListAdapter adapter = new ImageAdapter(this, items);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String item = String.valueOf(parent.getItemAtPosition(position)).toString();
                        Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                        intent.putExtra("name",item);
                        startActivity(intent);

                    }
                }
        );

    }

}