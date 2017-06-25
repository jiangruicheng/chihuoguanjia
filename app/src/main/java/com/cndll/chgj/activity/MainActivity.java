package com.cndll.chgj.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.RXbus.EventType;
import com.cndll.chgj.RXbus.RxBus;
import com.cndll.chgj.fragment.HomeFragment;
import com.cndll.chgj.fragment.LoginFragment;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.presenter.impl.HomeImpl;
import com.cndll.chgj.mvp.presenter.impl.LoginImpl;
import com.cndll.chgj.weight.MesgShow;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;

import static com.cndll.chgj.fragment.BaseFragment.fragmentList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    private FragmentManager fragmentManager;
    Subscription show;

    @Override
    public void onBackPressed() {
        boolean isOnStop = false;
        if (backPressEvents.size() != 0) {
            for (int i = 0; i < backPressEvents.size(); i++) {
                if (backPressEvents.get(i).onBackPress()) {
                    isOnStop = true;
                }
                if (isOnStop) {
                    return;
                }
                /*if (fragmentList.get(fragmentList.size() - 1).equals((backPressEvents.keyAt(i)))) {
                    backPressEvents.get(backPressEvents.keyAt(i)).onBackPress();
                    return;
                }*/
            }
        }
        super.onBackPressed();
    }

/*
    public static Map<Fragment, BackPressEvent> getBackPressEvents() {
        return backPressEvents;
    }
*/

    public static void removeBackPressEvent(BackPressEvent backPressEvent) {
        backPressEvents.remove(backPressEvent);
    }

    public static void setBackPressEvent(BackPressEvent backPressEvent) {
        backPressEvents.add(backPressEvent);
    }

    public static List<BackPressEvent> backPressEvents = new ArrayList<>();

    protected void showToast(String s) {
        Toast toast;
        toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    protected void baseShowMesg(String mesg) {
        MesgShow.showMesg("", mesg, frame, null, null, false);
    }

    private Subscription loginOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrashReport.initCrashReport(getApplicationContext(), "f7a529b1d4", true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        show = RxBus.getDefault().toObservable(EventType.class).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Observer<EventType>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(EventType eventType) {
                if (eventType.getType() == EventType.SHOW) {
                    //baseShowMesg(eventType.getExtra());
                    showToast(eventType.getExtra());
                }
            }
        });
        loginOther = RxBus.getDefault().toObservable(EventType.class).subscribe(new Observer<EventType>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(EventType eventType) {
                if (eventType.getType() == EventType.BACKKEY) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < fragmentList.size() - 1; i++) {
                                getSupportFragmentManager().popBackStack();
                            }
                            backPressEvents.clear();
                            LoginFragment loginFragment = LoginFragment.newInstance("", "");
                            getSupportFragmentManager().beginTransaction().add(R.id.frame, loginFragment).addToBackStack(loginFragment.getTag()).commit();
                            loginFragment.setPresenter(new LoginImpl());
                            getSupportFragmentManager().beginTransaction().hide(fragmentList.get(0));
                            fragmentList.add(loginFragment);
                        }
                    });

                }
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("CHGJ", Context.MODE_PRIVATE);
        AppMode.getInstance().setMid(sharedPreferences.getString("mid", "3"));
        AppMode.getInstance().setUid(sharedPreferences.getString("uid", "3"));
        AppMode.getInstance().setToken(sharedPreferences.getString("token", null));
        AppMode.getInstance().setLoading(sharedPreferences.getBoolean("isloding", false));
        AppMode.getInstance().setUsername(sharedPreferences.getString("username", ""));
        AppMode.getInstance().setMcode(sharedPreferences.getString("mdcode", null));
        AppMode.getInstance().setBoss(sharedPreferences.getBoolean("isboss", false));
        AppMode.getInstance().setDiscount(sharedPreferences.getBoolean("isdiscount", false));
        AppMode.getInstance().setExcel(sharedPreferences.getBoolean("isexcel", false));
        AppMode.getInstance().setOrder(sharedPreferences.getBoolean("isorder", false));
        AppMode.getInstance().setReturn(sharedPreferences.getBoolean("isreturn", false));
        AppMode.getInstance().setGive(sharedPreferences.getBoolean("isgive", false));
        AppMode.getInstance().setTel(sharedPreferences.getString("tel", null));
        AppMode.getInstance().setDeskMode(sharedPreferences.getBoolean("deskmode", true));
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
        fragmentList.add(fragment);
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
        editor.putBoolean("deskmode", AppMode.getInstance().isDeskMode());
        editor.commit();
        editor.commit();
    }

    public interface BackPressEvent {
        boolean onBackPress();
    }
}
