package com.example.cashregisterapp;

import static java.lang.Double.parseDouble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
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
            button8, button9, button0, buttonclear,butonbuy;

    TextView productText, totalText, quantityText;

    ListView listview;

    ListBaseAdapter adapter;
    Double total = 0.0;
    int index = -1;
    ArrayList<Product> stockList = new ArrayList<>();

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

                   showAlert(); //showalert is the buy dialog
               } else {
                   Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
               }
               break;

           /*case R.id.buttonManager: //Button Manager
               Intent intentManager = new Intent(MainActivity.this, ManagerActivity.class);
               startActivity(intentManager);
               break;*/
           default: //the exception default buttons 1,2,3,4,5,6,7,8,9,0
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

               if (Integer.parseInt(quantityText.getText().toString()) > stockList.get(index).getQuantity()) {
                   Toast.makeText(this, "No enough quantity in the stock", Toast.LENGTH_SHORT).show();;
                   //resets quantity, productType and total textViews to blank
                   quantityText.setText("");
                   quantityText.setHint("Quantity");
                   totalText.setText("");
                   totalText.setHint("Total");
                   productText.setText("");
                   productText.setHint("Product Type");
               }
       }

   }

    @Override
    //i is index value of item clicked
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        productText.setText(stockList.get(i).getProductName());
        index = i; //save index value because we reuse it
        if (!quantityText.getText().toString().equals("")) { //check if user clicked any number button before
            //then count the quantity and multiplied the productList (data price) resulting in type casting to type double total
            total = parseDouble(quantityText.getText().toString()) * stockList.get(i).getPrice();
            //shorten the textview price in string using String.format and 2 decimal places
            totalText.setText(String.format("%.2f", total));
        }

    }

    private void showAlert() { //Buy confirmation Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //show/notify the user you have bought the item
        builder.setMessage("Your purchase is " + quantityText.getText().toString() + " " + stockList.get(index).getProductName() + " for " + totalText.getText().toString())
                .setTitle("Thank You for your purchase");
        //OK Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            //setonClikcListener to Ok Button
            public void onClick(DialogInterface dialog, int id) {
                // open a report activity
                //reset the textfields/textViews
                quantityText.setText("");
                quantityText.setHint("Quantity");
                totalText.setText("");
                totalText.setHint("Total");
                productText.setText("");
                productText.setHint("Product Type");
            }
        });
        //create is to create the dialog and .show is to display the dialog to the user
        builder.create().show();
        Product obj = ((MyApp) getApplication()).appProductlist.get(index);
        //Update the quantity value of objects available in stock
        //get quantity - integer type cast of quantityTextview chosen by user
        //for example 10 - 2
        obj.setQuantity(obj.getQuantity() - Integer.parseInt(quantityText.getText().toString()));
        //Update display quantity now in the list, List.set( the row number of the List, and overwrite obj quantity)
        ((MyApp) getApplication()).appProductlist.set(index, obj);

        //Display changes to the user **IMPORTANT**
        //Every time we modify the list, we have to notify the adapter the list has changed with the new list
        adapter.notifyDataSetChanged(); //it will run the adapter again and update its new list values

        //Part 2 starts
       /* Date d1 = new Date();
        History h1 = new History(obj.getType(), parseDouble(totalTV.getText().toString()), Integer.parseInt(quantityTV.getText().toString()), d1);
        ((MyApp) getApplication()).historyList.add(h1);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
