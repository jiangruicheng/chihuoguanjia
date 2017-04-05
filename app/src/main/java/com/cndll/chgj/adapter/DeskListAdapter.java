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

public class DeskListAdapter extends ListAdapter {


    public DeskListAdapter(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desk, parent, false);
        return new DeskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        ((DeskViewHolder) holder).id.setText(position + 1 + "号桌台");
        ((DeskViewHolder) holder).handler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return super.onItemMove(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        super.onItemDismiss(position);
    }

    public static class DeskViewHolder extends ItemViewHolder {
        public TextView id;
        public TextView handler;
        public TextView revise;

        public DeskViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.desk_id);
            handler = (TextView) itemView.findViewById(R.id.move);
            revise = (TextView) itemView.findViewById(R.id.revise);

        }
    }
}
