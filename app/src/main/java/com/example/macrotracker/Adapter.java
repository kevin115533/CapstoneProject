package com.example.macrotracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public ArrayList<FoodEntry> efoodList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;

        public ViewHolder(final View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.entryName);
            textView2 = itemView.findViewById(R.id.pAmount);
            textView3 = itemView.findViewById(R.id.cAmount);
            textView4 = itemView.findViewById(R.id.fAmount);
            textView5 = itemView.findViewById(R.id.calorieAmount);
            textView6 = itemView.findViewById(R.id.itemNum);
        }
    }

    public Adapter(ArrayList<FoodEntry> foodList) {
        efoodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        FoodEntry currentItem = efoodList.get(position);

        viewHolder.textView1.setText(currentItem.geteName());
        viewHolder.textView2.setText(Integer.toString(currentItem.getePro()));
        viewHolder.textView3.setText(Integer.toString(currentItem.geteCar()));
        viewHolder.textView4.setText(Integer.toString(currentItem.geteFat()));
        viewHolder.textView5.setText(Integer.toString(currentItem.geteTotal()));
        viewHolder.textView6.setText(Integer.toString(currentItem.geteID()));
    }

    @Override
    public int getItemCount() {
        return efoodList.size();
    }

}
