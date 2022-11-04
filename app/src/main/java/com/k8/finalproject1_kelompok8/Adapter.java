package com.k8.finalproject1_kelompok8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    ArrayList activity_id;
    ArrayList activity_task;
    Context context;

    Activity activity;

    Adapter(Activity activity, Context context, ArrayList activity_id, ArrayList task){
        this.activity = activity;
        this.context = context;
        this.activity_id = activity_id;
        this.activity_task = task;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.rv_task_txt.setText(String.valueOf(activity_task.get(position)));

        holder.rv_task_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "ID NYA DISINI GAN " + activity_id.get(position) , Toast.LENGTH_SHORT).show();
                String activity = String.valueOf(activity_task.get(position));
                String positionNew = String.valueOf(activity_id.get(position));
                confirmDeleteDialog(activity, positionNew);
            }
        });
    }

    public void confirmDeleteDialog(String activity, String position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("TASK COMPLETED " + activity + "?");
        builder.setMessage("Are you sure task completed " + activity + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbHelper myDB = new DbHelper(context);
                myDB.deleteOneRow(position);
                refresh();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void refresh() {
        Intent intent = new Intent(context, MainActivity.class);
        activity.startActivityForResult(intent, 1);
    }

    @Override
    public int getItemCount() {
        return activity_task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rv_task_txt;
        Button rv_task_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_task_txt = itemView.findViewById(R.id.txtTask);
            rv_task_btn = itemView.findViewById(R.id.rv_task_btn);

        }
    }
}