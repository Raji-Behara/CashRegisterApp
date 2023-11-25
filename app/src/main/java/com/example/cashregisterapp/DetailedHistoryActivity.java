package com.example.cashregisterapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedHistoryActivity  extends AppCompatActivity {

    TextView type, price,  date;

    int index;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        type = findViewById(R.id.type_details);
        price = findViewById(R.id.price_details);
        date= findViewById(R.id.date_details);
        index= getIntent().getIntExtra("Index",0);

        //creating history object
        History h1=((MyApp) getApplication()).appHistorylist.get(index);
        //getting the list of items from history
        type.setText(String.format("%s%s",type.getText().toString(),h1.getType()));
        price.setText((String.format("%s%s",price.getText().toString(),h1.getPrice())));
        date.setText(String.format("%s%s",date.getText().toString(),h1.getDate()));


    }
}
