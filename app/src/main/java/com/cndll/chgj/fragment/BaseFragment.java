package com.cndll.chgj.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.activity.MainActivity;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.MesgShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment<T extends ListAdapter> extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    MainActivity.BackPressEvent event = new MainActivity.BackPressEvent() {
        @Override
        public boolean onBackPress() {
            return baseDisProg();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return null;
    }

    protected ItemTouchHelper mItemTouchHelper;
    T adapter;

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setBackPressEvent(event);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.removeBackPressEvent(event);
    }

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
        /*MainActivity.removeBackPressEvent(event);*/
    }

    public void reload() {
        /*MainActivity.setBackPressEvent(event);*/
    }

    protected void replaceFragment(Fragment fragment, BasePresenter presenter) {
        if (fragment instanceof BaseView && presenter != null) {
            ((BaseView) fragment).setPresenter(presenter);
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    protected void baseShowMesg(String mesg, View view) {
        MesgShow.showMesg("", mesg, view, null, null, false);
    }

    protected void baseShowMesg(String mesg, View view, MesgShow.OnButtonListener sure, MesgShow.OnButtonListener cancel) {
        MesgShow.showMesg("", mesg, view, sure, cancel, false);
    }

    protected void showToast(String s) {
        Toast toast;
        toast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    PopUpViewUtil prog;

    protected void showInput(final View view) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, 0);
            }

        }, 100);
    }

    protected void baseShowProg(View location) {
        if (prog == null) {
            prog = PopUpViewUtil.getInstance();
            prog.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
                @Override
                public void onDismiss() {
                    prog = null;
                }
            });
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.progress, null, false);
            prog.popListWindowNotOut(location, view, prog.getWindowManager(getActivity()).getDefaultDisplay().getWidth() / 7, prog.getWindowManager(getActivity()).getDefaultDisplay().getHeight() / 10, Gravity.CENTER, null);
        }

    }

    protected boolean baseDisProg() {
        if (prog != null) {
            prog.dismiss();
            return true;
        }
        return false;
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
