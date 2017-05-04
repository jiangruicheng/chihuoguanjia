package com.cndll.chgj.adapter;

/**
 * Created by jiang_ruicheng on 17/5/4.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;

public class OrderDeskListAdapter extends RecyclerView.Adapter<OrderDeskListAdapter.ItemViewHolder> {

    public interface OnItemClickLister {
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    private OnItemClickLister onItemClickLister;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desh, parent, false);
        return new ItemViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null) {
                    onItemClickLister.OnItemClick(v, position);
                }
            }
        });
        holder.name.setText(position + "");
            /*GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.parent.getLayoutParams();
            params.height = w/5;
            holder.parent.setLayoutParams(params);*/
    }

    @Override
    public int getItemCount() {
        return 46;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;
        TextView name;

        public ItemViewHolder(View itemView, ViewGroup v) {
            super(itemView);
            parent = (LinearLayout) itemView.findViewById(R.id.parent);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
