package com.sjz20200427.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerAdapter<H extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<H> {
    private List<T> myData = new ArrayList<>();
    public Context mContext;


    public BaseRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        myData = new ArrayList<>();
    }

    public void setMyData(List<T> myData) {
        this.myData.clear();
        this.myData.addAll(myData);
        notifyDataSetChanged();
    }

    public void reflushMyData(List<T> myData) {
        setMyData(myData);
    }

    public void loadMyData(List<T> myData) {
        this.myData.addAll(myData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(setLayout(), viewGroup, false);
        H viewHolder = initViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull H h, int position) {
        if (position <= (myData.size() - 1)) {
            initData(h, position, myData.get(position));
        } else {
            initData(h, position, null);
        }

    }

    public abstract int setLayout();

    public abstract void initData(H h, int position, T date);

    public abstract H initViewHolder(View view);

    @Override
    public int getItemCount() {
        return myData.size();
    }
}
