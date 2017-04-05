package com.cndll.chgj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;

/**
 * Created by kongqing on 2017/4/4.
 */

public class RegisterListAdpater extends ListAdapter {
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mendian, parent, false);
        return new RegistHolderView(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public RegisterListAdpater(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    public static class RegistHolderView extends ItemViewHolder {

        public RegistHolderView(View itemView) {
            super(itemView);
        }
    }
}
