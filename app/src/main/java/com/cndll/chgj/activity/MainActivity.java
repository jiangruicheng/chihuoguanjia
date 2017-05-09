package com.cndll.chgj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.cndll.chgj.R;
import com.cndll.chgj.fragment.HomeFragment;
import com.cndll.chgj.mvp.presenter.impl.HomeImpl;

import java.util.ArrayList;
import java.util.List;

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
                backPressEvents.get(i).onBackPress();
            }
            return;
        }
        super.onBackPressed();
    }

    public static List<BackPressEvent> getBackPressEvents() {
        return backPressEvents;
    }

    public static void removeBackPressEvent(BackPressEvent backPressEvent) {
        backPressEvents.remove(backPressEvent);
    }

    public static void setBackPressEvent(BackPressEvent backPressEvent) {
        backPressEvents.add(backPressEvent);
    }

    public static List<BackPressEvent> backPressEvents = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        HomeFragment fragment = HomeFragment.newInstance("", "");
        HomeImpl h = new HomeImpl();
        fragment.setPresenter(h);
        fragmentManager.beginTransaction().add(R.id.frame, fragment).commit();
    }

    public interface BackPressEvent {
        void onBackPress();
    }
}
