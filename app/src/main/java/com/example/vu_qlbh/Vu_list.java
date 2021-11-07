package com.example.vu_qlbh;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Vu_list extends AppCompatActivity {
    String DATABASE_NAME ="Minhvu_QLBaiHat.db";
    SQLiteDatabase database;
    ListView lstDSBH;
    ArrayList<Vu_Baihat>list;
    Adapter_vu adapter_vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vu_list);
        lstDSBH = (ListView) findViewById(R.id.listViewDSBaiHat);
        list = new ArrayList<>();
        adapter_vu = new Adapter_vu(Vu_list.this,list);
        lstDSBH.setAdapter(adapter_vu);

        database = Database.initDatabase(Vu_list.this,DATABASE_NAME);


        Cursor cursor = database.rawQuery("Select * from Vu_BaiHat",null);
       for (int i =0; i<cursor.getCount();i++){
           cursor.moveToPosition(i);
           byte[] anh = cursor.getBlob(1);
           String tenbh =cursor.getString(2);
           String tenCS = cursor.getString(3);
           list.add(new Vu_Baihat(anh,tenbh,tenCS));
       }
       adapter_vu.notifyDataSetChanged();
    }
}
