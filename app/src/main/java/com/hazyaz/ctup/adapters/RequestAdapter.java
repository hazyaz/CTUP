package com.hazyaz.ctup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hazyaz.ctup.R;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {

    ArrayList<ArrayList<String>> xData;
    Context context;

    public RequestAdapter() {

    }

    public RequestAdapter(ArrayList<ArrayList<String>> data, Context c) {
        this.xData = data;
        context = c;
        Log.v("BindViewHolder", "in onBindViewHolder");

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.v("BindViewHolder", "in onBindVsdsdr");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_friend_request, parent, false);
        Log.d("y3sdfds", "" + v);

        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.v("BindViewHolder", "in sdsdsdsd");


        Log.d("sdfdsgdf", xData.get(position).get(0));
        String n = xData.get(position).get(0);


        holder.name.setText(n);


    }

    @Override
    public int getItemCount() {
        return xData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.requestUserName);
            image = itemView.findViewById(R.id.userImageRequst);
        }


    }

}