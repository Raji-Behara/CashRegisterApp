package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryListBaseAdapter extends BaseAdapter {

    ArrayList<History> historylist;
    Context context;

    public HistoryListBaseAdapter(ArrayList<History> historylist, Context context) {
        this.historylist = historylist;
        this.context = context;
    }
    @Override
    public int getCount() {

       return historylist.size();
    }

    @Override
    public Object getItem(int i) {
        return historylist.get(i);
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
        View view = inflater.inflate(R.layout.historylist_row,viewgroup,false);

        TextView productType = view.findViewById(R.id.historylist_type);
        TextView productPrice = view.findViewById(R.id.historylist_price);
        TextView productQty = view.findViewById(R.id.historylist_quantity);

        productType.setText(historylist.get(i).historyName);

        productPrice.setText(String.valueOf(historylist.get(i).price));
        productQty.setText(String.valueOf(historylist.get(i).quantity));



      /*  productType.setText("type");

        productPrice.setText("price");
        productQty.setText("qnty");*/

        return view;

    }
}
