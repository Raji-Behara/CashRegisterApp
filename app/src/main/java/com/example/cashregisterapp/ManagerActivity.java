package com.example.cashregisterapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity  extends AppCompatActivity implements View.OnClickListener {

    Button buttonHistory,buttonRestock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        buttonHistory= findViewById(R.id.btn_history);
        buttonRestock= findViewById(R.id.btn_restock);

        buttonHistory.setOnClickListener(this);
        buttonRestock.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btn_history)
        {
           Intent intentHistory = new Intent(ManagerActivity.this,ShowHistoryActivity.class);
           startActivity(intentHistory);

           // Toast.makeText(this,"After History button clicked",Toast.LENGTH_SHORT).show();

        }

        else
        {
        Intent intentRestock = new Intent(ManagerActivity.this,RestockActivity.class);
        startActivity(intentRestock);
        }

    }
}
