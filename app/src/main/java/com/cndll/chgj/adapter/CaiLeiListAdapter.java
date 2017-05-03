/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cndll.chgj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.ItemTouchHelperViewHolder;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;


/**
 * Simple RecyclerView.Adapter that implements {@link ItemTouchHelperAdapter} to respond to move and
 * dismiss events from a {@link android.support.v7.widget.helper.ItemTouchHelper}.
 *
 * @author Paul Burke (ipaulpro)
 */
public class CaiLeiListAdapter extends ListAdapter<ResponseGetCaileiList.DataBean> {
    /*private final List<String> mItems = new ArrayList<>();*/

    private final OnStartDragListener mDragStartListener;

    public CaiLeiListAdapter(Context context, OnStartDragListener dragStartListener) {
        super(context, dragStartListener);
        mDragStartListener = dragStartListener;
        // mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_editor, parent, false);
        ListItemViewHolder itemViewHolder = new ListItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        ((ListItemViewHolder) holder).info.setVisibility(View.GONE);
        ((ListItemViewHolder) holder).price.setVisibility(View.GONE);
        ((ListItemViewHolder) holder).name.setText(mitems.get(position).getName());
        ((ListItemViewHolder) holder).name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(v, position);
                }
            }
        });
        ((ListItemViewHolder) holder).revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onReEidetClick(v, position);
                }
            }
        });
        // Start a drag whenever the handle view it touched
        ((ListItemViewHolder) holder).handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mitems != null) {
            return mitems.size();
        }
        return 0;
    }

    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public static class ListItemViewHolder extends ItemViewHolder {

        public final TextView name;
        public final TextView handleView;
        public final Button revise;
        public final TextView price;
        public final TextView info;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.menu_name);
            info = (TextView) itemView.findViewById(R.id.info);
            handleView = (TextView) itemView.findViewById(R.id.move);
            revise = (Button) itemView.findViewById(R.id.revise);
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
