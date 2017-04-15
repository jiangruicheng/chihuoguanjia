package com.cndll.chgj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kongqing on 2017/4/14.
 */

public class MendianListAdpater extends BaseAdapter {
    public List<ResponseMendianHomeList.DataBean> getList() {
        return list;
    }

    public void setList(List<ResponseMendianHomeList.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    List<ResponseMendianHomeList.DataBean> list;


    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mendian_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        if (list != null) {
            viewHolder.name.setText(list.get(position).getName());
            viewHolder.number.setText(list.get(position).getCode());
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.number)
        TextView number;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
