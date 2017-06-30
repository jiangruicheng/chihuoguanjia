package com.cndll.chgj.adapter;

/**
 * Created by jiang_ruicheng on 17/5/4.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;

import java.util.List;

public class DeshListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public OnItemClickLister getOnItemClickLister() {
        return onItemClickLister;
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    private OnItemClickLister onItemClickLister;

    public List<ResponseGetCaipinList.DataBean> getMitems() {
        return mitems;
    }

    public void setMitems(List<ResponseGetCaipinList.DataBean> mitems) {
        this.mitems = mitems;
        notifyDataSetChanged();
    }

    List<ResponseGetCaipinList.DataBean> mitems;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desh_order, parent, false);
        return new ItemViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        if (mitems.get(position).getIs_over().equals("1")) {
            holder.parent.setBackgroundResource(R.mipmap.orderovergray);
        } else {
            if ((position / 3) % 5 == 0) {
                holder.parent.setBackgroundResource(R.drawable.selector_order);
            }
            if ((position / 3) % 5 == 1) {
                holder.parent.setBackgroundResource(R.drawable.selector_order_tow);
            }
            if ((position / 3) % 5 == 2) {
                holder.parent.setBackgroundResource(R.drawable.selector_three);
            }
            if ((position / 3) % 5 == 3) {
                holder.parent.setBackgroundResource(R.drawable.selector_order_four);
            }
            if ((position / 3) % 5 == 4) {
                holder.parent.setBackgroundResource(R.drawable.selector_order_five);
            }
        }
        // holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null /*&& mitems.get(position).getIs_over().equals("0")*/) {
                    onItemClickLister.OnItemClick(v, position);
                }
            }
        });
        holder.number.setVisibility(View.GONE);

        if (mitems != null) {
            holder.name.setText(mitems.get(position).getName());
            holder.price.setText(mitems.get(position).getPrice());
        }


    }

    @Override
    public int getItemCount() {
        if (mitems != null) {
            return mitems.size();
        }
        return 0;
    }

}
