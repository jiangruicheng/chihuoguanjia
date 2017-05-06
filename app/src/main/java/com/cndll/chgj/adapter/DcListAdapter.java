package com.cndll.chgj.adapter;

/**
 * Created by jiang_ruicheng on 17/5/4.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;

import java.util.List;

public class DcListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public OnItemClickLister getOnItemClickLister() {
        return onItemClickLister;
    }

    private int selecteItems = 0;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    private OnItemClickLister onItemClickLister;

    public List<ResponseGetCaileiList.DataBean> getMitems() {
        return mitems;
    }

    public void setMitems(List<ResponseGetCaileiList.DataBean> mitems) {
        this.mitems = mitems;
        notifyDataSetChanged();
    }

    List<ResponseGetCaileiList.DataBean> mitems;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desh, parent, false);
        return new ItemViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        if (position == selecteItems) {
            holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
        } else {
            holder.parent.setBackgroundResource(R.drawable.shape_button_yellow);
        }
        holder.price.setVisibility(View.GONE);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null) {
                    onItemClickLister.OnItemClick(v, position);
                }
                selecteItems = position;
                notifyDataSetChanged();

            }
        });
        holder.number.setVisibility(View.GONE);
        if (mitems != null) {
            holder.name.setText(mitems.get(position).getName());
        }
            /*GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.parent.getLayoutParams();
            params.height = w/5;
            holder.parent.setLayoutParams(params);*/
    }

    @Override
    public int getItemCount() {
        if (mitems != null) {
            return mitems.size();
        }
        return 0;
    }


}
