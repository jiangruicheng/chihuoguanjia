package com.cndll.chgj.weight;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kongqing on 2017/5/8.
 */

public class TimePick {
    View view;
    LoopView Vyear;
    LoopView Vmoth;
    LoopView Vday;
    Activity context;
    private List<String> year, moth, day;
    private List<String> bigmoth;
    private int curryYear, curryMoth, curryDay;
    private Button cancel;
    private Button sure;

    public interface OnTimePickSlect {
        void onSelect(int year, int moth, int day);

        void onCancel();
    }

    public OnTimePickSlect getOnTimePickSlect() {
        return onTimePickSlect;
    }

    public void setOnTimePickSlect(OnTimePickSlect onTimePickSlect) {
        this.onTimePickSlect = onTimePickSlect;
    }

    private OnTimePickSlect onTimePickSlect;

    public TimePick(Activity context) {
        this.context = context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.time_pick, null, false);
        Vyear = (LoopView) view.findViewById(R.id.year);
        Vmoth = (LoopView) view.findViewById(R.id.moth);
        Vday = (LoopView) view.findViewById(R.id.day);
        cancel = (Button) view.findViewById(R.id.cancel);
        sure = (Button) view.findViewById(R.id.sure);
        Vyear.setTextSize(19);
        Vmoth.setTextSize(19);
        Vday.setTextSize(19);

        Vyear.setItemsVisibleCount(6);
        Vday.setItemsVisibleCount(6);
        Vmoth.setItemsVisibleCount(6);
        year = getNumb(Calendar.getInstance().get(Calendar.YEAR) - 1, Calendar.getInstance().get(Calendar.YEAR) + 1);
        bigmoth = new ArrayList<>();
        bigmoth.add("1");
        bigmoth.add("3");
        bigmoth.add("5");
        bigmoth.add("7");
        bigmoth.add("8");
        bigmoth.add("10");
        bigmoth.add("12");
        Vyear.setItems(year);
        Vyear.setNotLoop();
        Vmoth.setNotLoop();
        Vday.setNotLoop();
        Vmoth.setItems(getNumb(1, 12));
        Vday.setItems(getDay(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1));
        Vyear.setInitPosition(1);
        Vmoth.setInitPosition(Calendar.getInstance().get(Calendar.MONTH));
        Vday.setInitPosition(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
        curryYear = Calendar.getInstance().get(Calendar.YEAR);
        curryMoth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        curryDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpViewUtil != null) {
                    popUpViewUtil.dismiss();
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTimePickSlect != null) {
                    onTimePickSlect.onSelect(curryYear, curryMoth, curryDay);
                }
                if (popUpViewUtil != null) {
                    popUpViewUtil.dismiss();
                }
            }
        });
        Vyear.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                curryYear = Integer.valueOf(year.get(index));
                Vday.setItems(getDay(curryYear, curryMoth));
            }
        });
        Vmoth.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                curryMoth = index + 1;
                Vday.setItems(getDay(curryYear, curryMoth));
            }
        });
        Vday.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                curryDay = index + 1;
            }
        });
    }

    private List<String> getDay(int year, int moth) {
        if (moth != 2) {
            if (bigmoth.contains(moth + "")) {
                return getNumb(1, 31);
            } else {
                return getNumb(1, 30);
            }
        } else {
            if (year % 4 == 0 || (year % 4 == 0 && year % 100 == 0 && year % 400 == 0)) {
                return getNumb(1, 29);
            } else {
                return getNumb(1, 28);
            }
        }
    }

    private List<String> getNumb(int start, int end) {
        List<String> numb = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numb.add(i + "");
        }
        return numb;
    }

    private PopUpViewUtil popUpViewUtil;

    public void show() {
        if (view.getParent() != null) {
            ((ViewGroup) (view.getParent())).removeView(view);
        }
        popUpViewUtil = PopUpViewUtil.getInstance();
        popUpViewUtil.showDialog(context, view,
                0,
                popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getHeight() / 5 * 2,
                popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getWidth(), popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getHeight() / 7 * 3, 0);
    }

    public void showPopView(View location) {
        if (view.getParent() != null) {
            ((ViewGroup) (view.getParent())).removeView(view);
        }
        popUpViewUtil = PopUpViewUtil.getInstance();
        popUpViewUtil.popListWindow(location, view,
                popUpViewUtil.getWindowManager(location.getContext()).getDefaultDisplay().getWidth(),
                popUpViewUtil.getWindowManager(location.getContext()).getDefaultDisplay().getHeight() / 7 * 3,
                Gravity.BOTTOM, null);
    }
}
