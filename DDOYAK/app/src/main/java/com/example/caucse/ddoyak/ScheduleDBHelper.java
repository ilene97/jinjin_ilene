package com.example.caucse.ddoyak;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.caucse.ddoyak.AlarmSetting.MediRef;
import static com.example.caucse.ddoyak.ScheduleActivity.scheduleitems;

public class ScheduleDBHelper extends SQLiteOpenHelper {

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_STARTTIME = "starttime";
    private static final String KEY_ENDdTIME = "endtime";
    private static final String KEY_SCHEDULE = "schedule";


    public ScheduleDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCHEDULES (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, starttime TEXT, endtime TEXT, schedule TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "SCHEDULES");

        // Create tables again
        onCreate(db);
    }

    public void insert(String date, String starttime, String endtime, String schedule ){
        Log.d("TAG", "insert: sqlite conncection start");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SCHEDULES VALUES(null,' " + date + "','" +starttime + "','"+endtime + "','"+ schedule + "');");      //작은 따옴표로 안감싸면 오류남, 감싸면 해당 data가 아니라 user_id, time이 들어감 update로 해야해서 그런가?
        Log.d("TAG", "insert: schedule data success");
        db.close();
    }

    public void update(String date, String starttime, String endtime, String schedule, int sqlite_id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE SCHEDULES SET date = '" + date + "', starttime = '" + starttime + "', endtime = '"+ endtime +"', schedule = '"+ schedule +"' WHERE id ='" + sqlite_id + "';");
        db.close();
    }

    public void delete(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM SCHEDULES;");
        db.execSQL("DELETE FROM 'sqlite_sequence' WHERE name = 'SCHEDULES';");
        db.close();
    }

    public void getAllData(){
        SQLiteDatabase db = getReadableDatabase();
        ScheduleInfo result;

        Cursor cursor = db.rawQuery("SELECT * FROM SCHEDULES", null);
        while(cursor.moveToNext()){
            result = new ScheduleInfo(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            scheduleitems.add(result);
        }
    }

    /*
    public void writeNewData(String info) {
        SQLiteDatabase db = this.getReadableDatabase();
        int i=0;
        Cursor cursor = db.rawQuery("SELECT * FROM SCHEDULES", null);
        while(cursor.moveToNext()){
            MediRef.child(info).child(String.valueOf(i++)).setValue(cursor.getString(2));
        }
    }
    */
}