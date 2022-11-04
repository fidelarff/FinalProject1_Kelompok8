package com.k8.finalproject1_kelompok8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddTask extends AppCompatActivity {

    TextView show;
    DbHelper DbHelp;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText input_task_popup;
    TextView txt_add_popup, txt_cancel_popup;

    ArrayList<String> activity_id;
    ArrayList<String> activity_task;

    RecyclerView recyclerView;
    Adapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_add_task, null);
        input_task_popup = contactPopupView.findViewById(R.id.input_task_popup);

        txt_add_popup = contactPopupView.findViewById(R.id.txt_add_task);
        txt_cancel_popup = contactPopupView.findViewById(R.id.txt_cancel_task);


        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        txt_add_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = input_task_popup.getText().toString().trim();
                DbHelper myDB = new DbHelper(AddTask.this);
                myDB.inputActivity(temp);
                refreshActivity();
            }
        });

        txt_cancel_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                Intent intent = new Intent(AddTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void refreshActivity() {
        Intent intent = new Intent(AddTask.this, MainActivity.class);
        startActivity(intent);
    }

}