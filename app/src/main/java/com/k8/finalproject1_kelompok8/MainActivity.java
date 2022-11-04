package com.k8.finalproject1_kelompok8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DbHelper db;
    TextView tampil;
    ArrayList<String> id_task;
    ArrayList<String> activity_task;
    Adapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tampil = findViewById(R.id.txt_show);
        recyclerView = findViewById(R.id.recyclerView);

        db = new DbHelper(MainActivity.this);
        id_task = new ArrayList<>();
        activity_task = new ArrayList<>();

        storeDataInArray();

        // pemanggilan adapter
        Adapter = new Adapter(MainActivity.this, this, id_task, activity_task);

        // memanggil recyclerview dan juga menampilkan semua data di SQLite3
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void storeDataInArray() {
        Cursor data = db.readAllData();
        if (data.getCount() == 0) {
            tampil.setVisibility(View.VISIBLE);
        }else{
            tampil.setVisibility(View.GONE);
            while (data.moveToNext()){
                id_task.add(data.getString(0));
                activity_task.add(data.getString(1));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                startActivity(new Intent(MainActivity.this, AddTask.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}