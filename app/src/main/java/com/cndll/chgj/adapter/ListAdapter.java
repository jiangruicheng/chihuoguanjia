package com.cndll.chgj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperViewHolder;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;

/**
 * Created by kongqing on 2017/4/1.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {
    protected final OnStartDragListener mDragStartListener;

    public ListAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public void onItemDismiss(int position) {
        //mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
