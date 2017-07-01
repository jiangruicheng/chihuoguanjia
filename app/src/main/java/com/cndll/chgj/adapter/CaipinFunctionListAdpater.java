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
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;

/**
 * Created by kongqing on 2017/4/1.
 */

public class CaipinFunctionListAdpater extends ListAdapter<ResponseMethod.DataBean> {
    public CaipinFunctionListAdpater(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caipin_function, parent, false);
        return new CaipinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        ((CaipinViewHolder) holder).handler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });
        ((CaipinViewHolder) holder).handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "拖动可以排序", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ((CaipinViewHolder) holder).name.setText(mitems.get(position).getName());
        if (mitems.get(position).getPrice() != null && Float.valueOf(mitems.get(position).getPrice()) != 0) {
            ((CaipinViewHolder) holder).info.setText("附加价格" + mitems.get(position).getPrice());
        }
        ((CaipinViewHolder) holder).revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onReEidetClick(v, mitems.indexOf(mitemscopy.get(position)));
                }
            }
        });
    }


    @Override
    public void onItemDismiss(int position) {
        super.onItemDismiss(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //  Toast.makeText(context, "" + toPosition, Toast.LENGTH_SHORT).show();

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
