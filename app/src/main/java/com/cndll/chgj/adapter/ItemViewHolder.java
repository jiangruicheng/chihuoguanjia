package com.cndll.chgj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;

/**
 * Created by kongqing on 2017/5/4.
 */

public  class ItemViewHolder extends RecyclerView.ViewHolder {
    LinearLayout parent;
    TextView name,number,price;

    public ItemViewHolder(View itemView, ViewGroup v) {
        super(itemView);
        parent = (LinearLayout) itemView.findViewById(R.id.parent);
        name = (TextView) itemView.findViewById(R.id.name);
        number = (TextView) itemView.findViewById(R.id.number);
        price = (TextView) itemView.findViewById(R.id.price);
    }
}