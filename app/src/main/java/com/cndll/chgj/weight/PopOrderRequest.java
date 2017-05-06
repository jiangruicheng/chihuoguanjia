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
    TextView method, give, delete;

    public interface onItemClick {
        void onMethod();

        void onGive();

        void onDelete();
    }

    public PopOrderRequest.onItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(PopOrderRequest.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    onItemClick onItemClick;

    public void init(Context context, View location) {
        popUpViewUtil = PopUpViewUtil.getInstance();
        View view = LayoutInflater.from(context).inflate(R.layout.popview_order_request, null, false);
        method = (TextView) view.findViewById(R.id.method);
        give = (TextView) view.findViewById(R.id.give);
        delete = (TextView) view.findViewById(R.id.delete);

        method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onMethod();
                }
            }
        });
        give.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onGive();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onDelete();
                }
            }
        });
        int[] locations = new int[2];
        location.getLocationOnScreen(locations);
        locations[0] = locations[0] - popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth() / 3;
        locations[1] = locations[1] - popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 4 + location.getHeight();
        popUpViewUtil.popListWindow(location, view,
                popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth() / 3,
                popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 4,
                Gravity.NO_GRAVITY, locations);
    }
}
