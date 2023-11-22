package com.example.cashregisterapp;

import static java.lang.Double.parseDouble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener,AdapterView.OnItemClickListener {
    Button button1, button2, button3, button4, button5, button6, button7,
            button8, button9, button0, buttonclear,butonbuy,buttonManager;

    TextView productText, totalText, quantityText;

    ListView listview;

    ListBaseAdapter adapter;
    Double total = 0.0;
    int index = -1;
    ArrayList<Product> stockList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = findViewById(R.id.btn_one);
        button2 = findViewById(R.id.btn_two);
        button3 = findViewById(R.id.btn_three);
        button4 = findViewById(R.id.btn_four);
        button5 = findViewById(R.id.btn_five);
        button6 = findViewById(R.id.btn_six);
        button7 = findViewById(R.id.btn_seven);
        button8 = findViewById(R.id.btn_eight);
        button9 = findViewById(R.id.btn_nine);
        button0 = findViewById(R.id.btn_zero);
        buttonclear = findViewById(R.id.btn_clear);
        butonbuy = findViewById(R.id.btn_buy);
        buttonManager = findViewById(R.id.btn_manager);

        listview = findViewById(R.id.listview_products);
        productText = findViewById(R.id.product_textview);
        totalText = findViewById(R.id.total_textview);
        quantityText = findViewById(R.id.quantity_textview);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonclear.setOnClickListener(this);
        butonbuy.setOnClickListener(this);
        productText.setOnClickListener(this);
        totalText.setOnClickListener(this);
        quantityText.setOnClickListener(this);
        buttonManager.setOnClickListener(this);


        listview.setOnItemClickListener(this);
        listview = findViewById(R.id.listview_products); // Adapter View
        stockList = ((MyApp) getApplication()).getAppProductlist(); // data
        adapter = new ListBaseAdapter(stockList, MainActivity.this);

        listview.setAdapter(adapter);


    }


   // @Override


   /* public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy: //Buy Button
                if (!totalText.getText().toString().equals("")) {
                   // showAlert();
                } else {

                    Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }
                break;

*/
   @Override //implementation of override mandatory function/method of onclick listener
   public void onClick(@NonNull View view) {
       switch (view.getId()) {
           //Clear Button
           case R.id.btn_clear:
               quantityText.setText("");
               quantityText.setHint("Quantity");
               totalText.setText("");
               totalText.setHint("Total");
               productText.setText("");
               productText.setHint("Product Type");
               break;

           //Buy Button

           case R.id.btn_buy:

               //check if total textview is not empty
               if (!totalText.getText().toString().isEmpty()) {

                   // check quantity is less than stock quantity



                   showAlert(); //showalert is the buy dialog

               } else {
                   Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
               }
               break;

           //Button Manager

           case R.id.btn_manager:


                             //Log.d("testing","managerbutton");

               Intent intentManager = new Intent(MainActivity.this, ManagerActivity.class);


               startActivity(intentManager);

               break;

           default:
               Button b = (Button) view;
               String num1 = quantityText.getText().toString(); // by default quantity text is empty

               //assign text from button to num2
               String num2 = b.getText().toString();
               //concatenate num1 "" and num2
               String num = num1 + num2;

               if (index != -1) {
                   Double price = stockList.get(index).getPrice();
                   total = price * parseDouble(num);
                   totalText.setText(String.format("%.2f", total));
               }
               quantityText.setText(String.format("%s%s", num1, num2));


       }

   }

    @Override
    //i is index value of item clicked
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        productText.setText(stockList.get(i).getProductName());
        index = i;

        //check if user clicked any number button before
        if (!quantityText.getText().toString().equals("")) {

            total = parseDouble(quantityText.getText().toString()) * stockList.get(i).getPrice();

            totalText.setText(String.format("%.2f", total));
        }

    }

    private void showAlert() { //Buy confirmation Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Your purchase is " + quantityText.getText().toString() + " " + stockList.get(index).getProductName() + " for " + totalText.getText().toString())
                .setTitle("Thank You for your purchase");
        //OK Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                quantityText.setText("");
                quantityText.setHint("Quantity");
                totalText.setText("");
                totalText.setHint("Total");
                productText.setText("");
                productText.setHint("Product Type");
            }
        });





        builder.create().show();
        Product obj = ((MyApp) getApplication()).appProductlist.get(index);

        obj.setQuantity(obj.getQuantity() - Integer.parseInt(quantityText.getText().toString()));

        ((MyApp) getApplication()).appProductlist.set(index, obj);


        //it will run the adapter again and update its new list values
        adapter.notifyDataSetChanged();


        if (Integer.parseInt(quantityText.getText().toString()) > stockList.get(index).getQuantity()) {
            Toast.makeText(this, "No enough quantity in the stock", Toast.LENGTH_SHORT).show();                   //resets quantity, productType and total textViews to blank
            quantityText.setText("");
            quantityText.setHint("Quantity");
            totalText.setText("");
            totalText.setHint("Total");
            productText.setText("");
            productText.setHint("Product Type");
        }
       //History Button
       Date d1 = new Date();
        History h1 = new History(obj.getProductName(), parseDouble(totalText.getText().toString()), Integer.parseInt(quantityText.getText().toString()), d1);
        ((MyApp) getApplication()).getAppHistorylist().add(h1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
