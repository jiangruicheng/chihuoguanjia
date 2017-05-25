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

        editor.commit();
    }

    public interface BackPressEvent {
        void onBackPress();
    }
}
