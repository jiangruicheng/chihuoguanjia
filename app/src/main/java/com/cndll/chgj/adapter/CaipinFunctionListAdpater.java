package com.cndll.chgj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;

/**
 * Created by kongqing on 2017/4/1.
 */

public class CaipinFunctionListAdpater extends ListAdapter {
    public CaipinFunctionListAdpater(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caipin_function, parent, false);
        return new CaipinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        ((CaipinViewHolder) holder).handler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    @Override
    public void onItemDismiss(int position) {
        super.onItemDismiss(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return super.onItemMove(fromPosition, toPosition);
    }

    public static class CaipinViewHolder extends ItemViewHolder {
        public TextView name;
        public TextView info;
        public TextView handler;
        public TextView revise;

        public CaipinViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            info = (TextView) itemView.findViewById(R.id.info);
            handler = (TextView) itemView.findViewById(R.id.move);
            revise = (TextView) itemView.findViewById(R.id.revise);
        }
    }
}
