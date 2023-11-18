package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListBaseAdapter extends BaseAdapter {

    ArrayList<Product> productList;
    Context context;

    public ListBaseAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewgroup) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //convert the layout into view object 'view'
        //select layout view, viewGroup, false (garbage collector)
        View view = inflater.inflate(R.layout.list_row,viewgroup,false);

        TextView productType = view.findViewById(R.id.list_type);
        TextView productPrice = view.findViewById(R.id.list_price);
        TextView productQty = view.findViewById(R.id.list_quantity);

        productType.setText(productList.get(i).productName);
        productPrice.setText(String.valueOf(productList.get(i).price));
        productQty.setText(String.valueOf(productList.get(i).quantity));

        return view;

    }
}

