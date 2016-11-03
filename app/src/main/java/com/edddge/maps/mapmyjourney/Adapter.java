package com.edddge.maps.mapmyjourney;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by vicky on 10/24/2016.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context context;
    private LayoutInflater inflater;

    List<DetailsModel> data;
    private final String LOG_TAG = "infovision_log";
int[] t;
    DetailsModel current;
    int currentPos=0;
    private static ClickListener clickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
    // create constructor to innitilize context and data sent from SecondScreen
    public Adapter(Context context, List<DetailsModel> data,int[] t){
        Log.d(LOG_TAG, "Adapter - constructor : ");
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.t = t;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.details, parent,false);
        MyHolder holder=new MyHolder(view);
        Log.d(LOG_TAG, "Adapter - onCreateViewHolder : ");
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Log.d(LOG_TAG, "Adapter - onBindViewHolder : ");

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DetailsModel current=data.get(position);


        myHolder.transport.setText(current.getTransport());
        myHolder.dis.setText(current.getDistance());
        myHolder.time.setText(current.getTime());
        myHolder.route.setText(current.getRoute());
        myHolder.imageView.setImageResource(t[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FouthScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);            }
        });



    }

    // return total item from List
    @Override
    public int getItemCount() {
        Log.d(LOG_TAG, "size : " + data.size());
        return data.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {

        TextView transport;
        TextView dis;
        TextView time;
        TextView route;
ImageView imageView;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            transport = (TextView) itemView.findViewById(R.id.trans);
            time = (TextView) itemView.findViewById(R.id.time);
            dis = (TextView) itemView.findViewById(R.id.dis);
            route = (TextView) itemView.findViewById(R.id.route);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
