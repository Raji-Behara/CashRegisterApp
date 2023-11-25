package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class ShowHistoryActivity extends AppCompatActivity implements HistoryRecyclerAdapter.ItemListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhistory);

        //Recycler adapter
        RecyclerView recyclerView=findViewById(R.id.show_history_recycler);
        HistoryRecyclerAdapter adapter=new HistoryRecyclerAdapter(((MyApp) getApplication()).appHistorylist,this);
        adapter.listener=this;
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void onItemClicked(int pos) {
        Intent intent=new Intent(ShowHistoryActivity.this,DetailedHistoryActivity.class);
        intent.putExtra("Index",pos);
        startActivity(intent);
    }
}