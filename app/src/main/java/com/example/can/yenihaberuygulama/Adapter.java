package com.example.can.yenihaberuygulama;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<HaberVerisi> haberVerisi;
    Context context;
    public Adapter(Context context,ArrayList<HaberVerisi> haberVerisi){
        this.context = context;
        this.haberVerisi = haberVerisi;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HaberVerisi current = haberVerisi.get(position);
        holder.haberBaslik.setText(current.getBaslik());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,HaberDetay.class);
                i.putExtra("Link",current.getLink());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return haberVerisi.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView haberBaslik;
        public MyViewHolder(View itemView) {
            super(itemView);
            haberBaslik = (TextView)itemView.findViewById(R.id.title_text);
        }
    }
}
