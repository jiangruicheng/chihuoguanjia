package com.cndll.chgj.adapter;

/**
 * Created by jiang_ruicheng on 17/5/4.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;

import java.util.List;

public class OrderDeskListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public List<ResponseGetDeskList.DataBean> getItems() {
        return items;
    }

    public void setItems(List<ResponseGetDeskList.DataBean> items) {
        this.items = items;
         notifyDataSetChanged();
    }

    List<ResponseGetDeskList.DataBean> items;

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

        if (items.get(position).getIsoc().equals("1")) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
            holder.price.setText(items.get(position).getMoney());
            holder.number.setText(items.get(position).getNum());
        } else {
            holder.parent.setBackgroundResource(R.drawable.shape_button_yellow);
            holder.price.setText("");
            holder.number.setText("");
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null) {
                    onItemClickLister.OnItemClick(v, position);
                }
            }
        });
        holder.name.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }


}
