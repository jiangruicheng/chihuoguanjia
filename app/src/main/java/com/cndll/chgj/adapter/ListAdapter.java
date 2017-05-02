package com.cndll.chgj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperViewHolder;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongqing on 2017/4/1.
 */

public class ListAdapter<T extends DataList> extends RecyclerView.Adapter<ListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {
    protected final OnStartDragListener mDragStartListener;
    protected Context context;

    public List<? extends DataList> getMitems() {
        return mitems;
    }

    public void setMitems(List<T> mitems) {
        this.mitems = mitems;
        mitemscopy = mitems;
        notifyDataSetChanged();
    }

    public void addMitems(List<T> dataLists) {
        if (this.mitems != null) {
            this.mitems.addAll(dataLists);
        } else {
            this.mitems = dataLists;
        }
        notifyDataSetChanged();
    }

    public interface OnReEidetClick {
        void onReEidetClick(View view, int position);
    }

    protected OnReEidetClick reEidetClick;

    public void setReEidetClick(OnReEidetClick onReEidetClick) {
        this.reEidetClick = onReEidetClick;
    }

    public void addMitems(T dataList) {
        if (mitems == null) {
            mitems = new ArrayList<>();
        }
        mitems.add(dataList);
        notifyDataSetChanged();
    }

    protected List<T> mitems;
    protected List<T> mitemscopy;

    public ListAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;
        this.context = context;
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

    public List<T> getOrdlist() {
        if (mitems != null) {
            for (int i = 0; i < mitems.size(); i++) {
                mitems.get(i).setOrder(i);
            }
            return mitems;
        }
        return null;
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

    protected class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}
