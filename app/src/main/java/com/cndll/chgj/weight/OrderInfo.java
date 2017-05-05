package com.cndll.chgj.weight;

import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;

/**
 * Created by kongqing on 2017/5/5.
 */

public class OrderInfo {
    private View view;
    private TextView count, allMoney, discount, give, lastMoney;

    public void init(View view) {
        this.view = view;
        count = (TextView) view.findViewById(R.id.number);
        allMoney = (TextView) view.findViewById(R.id.allcount);
        discount = (TextView) view.findViewById(R.id.zhekou);
        give = (TextView) view.findViewById(R.id.zengsong);
        lastMoney = (TextView) view.findViewById(R.id.lastprice);
    }

    private OrderInfo setCount(String count) {
        this.count.setText(count);
        return this;
    }

    private OrderInfo setAllMoney(String count) {
        this.allMoney.setText(count);
        return this;
    }

    private OrderInfo setDiscount(String count) {
        this.discount.setText(count);
        return this;
    }

    private OrderInfo setGive(String count) {
        this.give.setText(count);
        return this;
    }

    private OrderInfo setLastMoney(String count) {
        this.lastMoney.setText(count);
        return this;
    }


}
