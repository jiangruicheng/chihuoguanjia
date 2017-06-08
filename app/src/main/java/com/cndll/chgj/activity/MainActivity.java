package com.cndll.chgj.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.cndll.chgj.R;
import com.cndll.chgj.fragment.BaseFragment;
import com.cndll.chgj.fragment.HomeFragment;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.presenter.impl.HomeImpl;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    private FragmentManager fragmentManager;

    @Override
    public void onBackPressed() {
        if (backPressEvents.size() != 0) {
            for (int i = 0; i < backPressEvents.size(); i++) {
                if (BaseFragment.fragmentList.get(BaseFragment.fragmentList.size() - 1).equals((backPressEvents.keyAt(i)))) {
                    backPressEvents.get(backPressEvents.keyAt(i)).onBackPress();
                    return;
                }
            }

        }
        super.onBackPressed();
    }

    public static Map<Fragment, BackPressEvent> getBackPressEvents() {
        return backPressEvents;
    }

    public static void removeBackPressEvent(BackPressEvent backPressEvent) {
        backPressEvents.remove(backPressEvent);
    }

    public static void setBackPressEvent(BackPressEvent backPressEvent, Fragment fragment) {
        backPressEvents.put(fragment, backPressEvent);
    }

    public static ArrayMap<Fragment, BackPressEvent> backPressEvents = new ArrayMap<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("CHGJ", Context.MODE_PRIVATE);
        AppMode.getInstance().setMid(sharedPreferences.getString("mid", "3"));
        AppMode.getInstance().setUid(sharedPreferences.getString("uid", "3"));
        AppMode.getInstance().setToken(sharedPreferences.getString("token", null));
        AppMode.getInstance().setLoading(sharedPreferences.getBoolean("isloding", false));
        AppMode.getInstance().setUsername(sharedPreferences.getString("username", ""));
        AppMode.getInstance().setBoss(sharedPreferences.getBoolean("isboss", false));
        AppMode.getInstance().setMcode(sharedPreferences.getString("mdcode", null));
        AppMode.getInstance().setDiscount(sharedPreferences.getBoolean("isdiscount",false));
        AppMode.getInstance().setExcel(sharedPreferences.getBoolean("isexcel",false));
        AppMode.getInstance().setOrder(sharedPreferences.getBoolean("isorder",false));
        AppMode.getInstance().setReturn(sharedPreferences.getBoolean("isreturn",false));
        AppMode.getInstance().setGive(sharedPreferences.getBoolean("isgive",false));
       /* editor.putBoolean("isdiscount", AppMode.getInstance().isDiscount());
        editor.putBoolean("isexcel", AppMode.getInstance().isExcel());
        editor.putBoolean("isorder", AppMode.getInstance().isOrder());
        editor.putBoolean("isreturn", AppMode.getInstance().isReturn());
        editor.putBoolean("isgive", AppMode.getInstance().isGive());*/
        fragmentManager = getSupportFragmentManager();
        HomeFragment fragment = HomeFragment.newInstance("", "");
        HomeImpl h = new HomeImpl();
        fragment.setPresenter(h);
        fragmentManager.beginTransaction().add(R.id.frame, fragment).commit();
        BaseFragment.fragmentList.add(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finish() {
        super.finish();
        SharedPreferences.Editor editor = getSharedPreferences("CHGJ", Context.MODE_PRIVATE).edit();
        editor.putString("mid", AppMode.getInstance().getMid());
        editor.putString("uid", AppMode.getInstance().getUid());
        editor.putString("token", AppMode.getInstance().getToken());
        editor.putString("username", AppMode.getInstance().getUsername());
        editor.putBoolean("isboss", AppMode.getInstance().isBoss());
        editor.putBoolean("isloding", AppMode.getInstance().isLoading());
        editor.commit();
        editor.commit();
    }

    public interface BackPressEvent {
        void onBackPress();
    }
}
