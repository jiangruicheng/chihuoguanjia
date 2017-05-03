package com.cndll.chgj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kongqing on 2017/4/14.
 */

public class PopCaileiListAdpater extends BaseAdapter {
    public List<ResponseGetCaileiList.DataBean> getList() {
        return list;
    }

    public void setList(List<ResponseGetCaileiList.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    List<ResponseGetCaileiList.DataBean> list;


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
            viewHolder.number.setVisibility(View.GONE);
            viewHolder.layout.setBackgroundResource(R.color.menuEditorItemTitle);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.layout)
        LinearLayout layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
