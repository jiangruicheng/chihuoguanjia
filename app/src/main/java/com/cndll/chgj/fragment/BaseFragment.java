package com.cndll.chgj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment<T extends ListAdapter> extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return null;
    }

    protected ItemTouchHelper mItemTouchHelper;
    T adapter;


    protected void setListViewAdapter(RecyclerView view) {
        view.setHasFixedSize(true);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(view);
    }
}
