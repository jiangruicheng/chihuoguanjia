package com.cndll.chgj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;

/**
 * Created by kongqing on 2017/4/4.
 */

public class RegisterListAdpater extends ListAdapter {
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
                if (reEidetClick != null) {
                    reEidetClick.onReEidetClick(null, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public RegisterListAdpater(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
    }

    public static class RegistHolderView extends ItemViewHolder {
        Button reEidet;

        public RegistHolderView(View itemView) {
            super(itemView);
            reEidet = (Button) itemView.findViewById(R.id.revise);
        }
    }
}
