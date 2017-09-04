package com.cndll.chgj.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;

/**
 * Created by kongqing on 2017/4/1.
 */

public class DeskListAdapter extends ListAdapter<ResponseGetDeskList.DataBean> {


    public DeskListAdapter(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desk, parent, false);
        return new DeskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        ((DeskViewHolder) holder).id.setText(mitems.get(position).getName());
        ((DeskViewHolder) holder).handler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isToast){
                    Toast toast = Toast.makeText(v.getContext(), "拖动可以排序", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    isToast = false;
                }
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });
        ((DeskViewHolder) holder).handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "拖动可以排序", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ((DeskViewHolder) holder).revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onReEidetClick(v, mitems.indexOf(mitemscopy.get(position)));
                }
            }
        });
    }

    /*@Override
    public int getItemCount() {
        return super.getItemCount();
    }*/

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
