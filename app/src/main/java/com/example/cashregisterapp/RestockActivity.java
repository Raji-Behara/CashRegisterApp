package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RestockActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    EditText quantityEdit;
    Button okBtn, cancelBtn;
    ListView restockListView;
    ListBaseAdapter adapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);


            quantityEdit = findViewById(R.id.editTextQuantity);
            okBtn = findViewById(R.id.okButtonR);
            cancelBtn = findViewById(R.id.cancelButtonR);
            restockListView = findViewById(R.id.restockListView);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        restockListView.setOnItemClickListener(this);


        adapter = new ListBaseAdapter(((MyApp) getApplication()).appProductlist, RestockActivity.this);
        restockListView.setAdapter(adapter);
    }




    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.okButtonR) {
            if (quantityEdit.getText().toString().equals("") || selectedIndex == -1){
                Toast.makeText(this, "All fields are required to be filled", Toast.LENGTH_SHORT).show();
            } else {
                Product p1 = ((MyApp)getApplication()).appProductlist.get(selectedIndex);
                p1.setQuantity(p1.getQuantity()+Integer.parseInt(quantityEdit.getText().toString()));
                ((MyApp)getApplication()).appProductlist.set(selectedIndex,p1);
                adapter.notifyDataSetChanged();
                selectedIndex=-1;
                quantityEdit.setText("");
            }
        }
        else {
            finish();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectedIndex = i ;
        Toast.makeText(this, ((MyApp) getApplication()).appProductlist.get(i).getProductName(), Toast.LENGTH_SHORT).show();
    }
}