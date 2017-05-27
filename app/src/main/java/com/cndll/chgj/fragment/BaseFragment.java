package com.cndll.chgj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;

import java.util.ArrayList;
import java.util.List;

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
        view.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(view);
    }

    public static List<BaseFragment> fragmentList = new ArrayList<>();


    protected void replaceFragmentAddToBackStack(BaseFragment fragment, BasePresenter presenter) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragment instanceof BaseView && presenter != null) {
            ((BaseView) fragment).setPresenter(presenter);
        }
        if (fragmentList.size() > 0) {
            fragmentManager.beginTransaction().hide(fragmentList.get(fragmentList.size() - 1)).commit();
        }
        fragmentList.add(fragment);
        fragmentManager.beginTransaction().add(R.id.frame, fragment).addToBackStack(fragment.getTag()).commit();

    }

    public void reload() {

    }

    protected void replaceFragment(Fragment fragment, BasePresenter presenter) {
        if (fragment instanceof BaseView && presenter != null) {
            ((BaseView) fragment).setPresenter(presenter);
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    protected void baseShowMesg(String mesg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fragmentList.size() == 1) {
            fragmentList.clear();
        }

        if (fragmentList.size() > 1) {
            fragmentList.remove(fragmentList.get(fragmentList.size() - 1));
            fragmentList.get(fragmentList.size() - 1).reload();
            getActivity().getSupportFragmentManager().beginTransaction().show(fragmentList.get(fragmentList.size() - 1)).commit();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    protected void popBackFragment() {

        getActivity().
                getSupportFragmentManager().
                popBackStack();

        //fragmentTransaction.show(fragmentList.get(fragmentList.size() - 1));
    }
}
