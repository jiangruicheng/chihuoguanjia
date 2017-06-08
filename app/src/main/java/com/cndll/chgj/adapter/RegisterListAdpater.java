package com.cndll.chgj.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStoreList;

/**
 * Created by kongqing on 2017/4/4.
 */

public class RegisterListAdpater extends ListAdapter<ResponseGetStoreList.DataBean> {
    @Override
    public RegistHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mendian, parent, false);
        return new RegistHolderView(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        ((RegistHolderView) holder).reEidet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onReEidetClick(null, mitems.indexOf(mitemscopy.get(position)));
                }
            }
        });
        ((RegistHolderView)holder).move.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
        ((RegistHolderView) holder).name.setText(mitems.get(position).getName());
        ((RegistHolderView) holder).id.setText("门店编号："+mitems.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if (mitems != null)
            return mitems.size();
        return 0;
    }

    public RegisterListAdpater(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    public static class RegistHolderView extends ItemViewHolder {
        Button reEidet;
        TextView name, id, move;

        public RegistHolderView(View itemView) {
            super(itemView);
            move = (TextView) itemView.findViewById(R.id.move);
            reEidet = (Button) itemView.findViewById(R.id.revise);
            name = (TextView) itemView.findViewById(R.id.store_name);
            id = (TextView) itemView.findViewById(R.id.store_id);
        }
    }
}
