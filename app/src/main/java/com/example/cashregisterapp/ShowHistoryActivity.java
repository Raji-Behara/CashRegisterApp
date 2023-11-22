package com.example.cashregisterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowHistoryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView historyListview;

    ArrayList<History> historyitems = new ArrayList<>();
    BaseAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhistory);


        historyListview = findViewById(R.id.show_history_list);

        historyListview.setOnItemClickListener(this);



        // Base Adapter View

     historyitems = ((MyApp) getApplication()).getAppHistorylist(); // data
       adapter = new HistoryListBaseAdapter(historyitems, ShowHistoryActivity.this);
        //adapter = new HistoryListBaseAdapter(historyitems,ShowHistoryActivity.this);

      //  adapter=new HistoryListBaseAdapter(((MyApp) getApplication()).appHistorylist,ShowHistoryActivity.this);
       // adapter.listener=this;
       // recyclerView.setAdapter(adapter);
        historyListview.setAdapter(adapter);



    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
