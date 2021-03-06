package com.cndll.chgj.weight;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class PopOrderRequest {
    PopUpViewUtil popUpViewUtil;
    TextView first, second, third;

    public void setFirstText(String s) {
        first.setText(s);
    }

    public void setFirstVisable(int visable) {
        first.setVisibility(visable);
    }

    public void setSecondText(String s) {
        second.setText(s);
    }

    public void setSecondVisble(int visable) {
        second.setVisibility(visable);
    }

    public void setThirdText(String s) {
        third.setText(s);
    }

    public void setThirdVisble(int visable) {
        third.setVisibility(visable);
    }

    public interface onItemClick {
        void onFirst(View view);

        void onSecond(View view);

        void onThird(View view);
    }

    public PopOrderRequest.onItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(PopOrderRequest.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    onItemClick onItemClick;
    View location;
    Context context;
    View view;
    public void init(Context context, View location) {
        this.context = context;
        this.location = location;
        popUpViewUtil = PopUpViewUtil.getInstance();
        view = LayoutInflater.from(context).inflate(R.layout.popview_order_request, null, false);
        first = (TextView) view.findViewById(R.id.method);
        second = (TextView) view.findViewById(R.id.give);
        third = (TextView) view.findViewById(R.id.delete);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onFirst(v);
                }
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onSecond(v);
                }
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onThird(v);
                }
            }
        });

    }

    public void show() {
        int[] locations = new int[2];
        location.getLocationOnScreen(locations);
        locations[0] = locations[0] - popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth() / 3;
        locations[1] = locations[1] - popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 4 + location.getHeight();
        popUpViewUtil.popListWindow(location, view,
                popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth() / 3,
                popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 4,
                Gravity.NO_GRAVITY, locations);
    }

    public void dismiss() {
        if (popUpViewUtil != null) {
            popUpViewUtil.dismiss();
        }
    }
}
