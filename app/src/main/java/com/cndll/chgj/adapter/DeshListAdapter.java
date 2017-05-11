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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desh, parent, false);
        return new ItemViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        if ((position / 3) % 5 == 0) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
        }
        if ((position / 3) % 5 == 1) {
            holder.parent.setBackgroundResource(R.drawable.shape_verify_button);
        }
        if ((position / 3) % 5 == 2) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid_green);
        }
        if ((position / 3) % 5 == 3) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid_red);
        }
        if ((position / 3) % 5 == 4) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid_blue);
        }
        // holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null) {
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
