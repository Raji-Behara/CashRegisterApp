package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.myViewHolder> {

    interface ItemListener {
        void onItemClicked(int pos);
    }

    ArrayList<History> historylist;
    Context context;
    ItemListener listener;

    public HistoryRecyclerAdapter(ArrayList<History> historylist, Context context) {
        this.historylist = historylist;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historylist_row, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.historyType.setText(historylist.get(position).getType());
        holder.historyPrice.setText(String.valueOf(historylist.get(position).getPrice()));
        holder.historyQuantity.setText(String.valueOf(historylist.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return historylist.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView historyType;
        TextView historyPrice;
        TextView historyQuantity;

        public myViewHolder(@NonNull View itemView) { //mandatory vs optional -- NonNull input
            super(itemView);

            historyType = itemView.findViewById(R.id.historylist_type);
            historyPrice = itemView.findViewById(R.id.historylist_price);
            historyQuantity = itemView.findViewById(R.id.historylist_quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}