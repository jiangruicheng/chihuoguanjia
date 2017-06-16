package com.cndll.chgj.weight;

import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.StringHelp;

/**
 * Created by kongqing on 2017/5/5.
 */

public class OrderItemMesg {
    private TextView name, method, price, countEdit, rquest;


    public void init(View view) {
        name = (TextView) view.findViewById(R.id.desh_name);
        method = (TextView) view.findViewById(R.id.desh_method);
        price = (TextView) view.findViewById(R.id.all_price);
        countEdit = (TextView) view.findViewById(R.id.number_edit);
        rquest = (TextView) view.findViewById(R.id.yaoqiu);
    }

    public OrderItemMesg setName(String name) {
        this.name.setText(name);
        return this;
    }

    public TextView getMethod() {
        return method;
    }

    public OrderItemMesg setMethod(String name) {
        this.method.setText(name);
        return this;
    }

    public OrderItemMesg setPrice(String name) {
        this.price.setText(name);
        return this;
    }

    public OrderItemMesg setCount(String name) {
        this.countEdit.setText(name);
        if ((StringHelp.isFloat(name) && Float.valueOf(name) == 0) || name.equals("0.0")) {
            countEdit.setBackgroundResource(R.color.yinse);
            rquest.setBackgroundResource(R.color.yinse);
            countEdit.setEnabled(false);
            rquest.setEnabled(false);
        } else {
            countEdit.setBackgroundResource(R.color.white);
            rquest.setBackgroundResource(R.color.white);
            countEdit.setEnabled(true);
            rquest.setEnabled(true);
        }
        return this;
    }

    public OrderItemMesg setRquestOnclick(View.OnClickListener onclick) {
        this.rquest.setOnClickListener(onclick);
        return this;
    }

    public OrderItemMesg setCountEditOnclick(View.OnClickListener onclick) {
        this.countEdit.setOnClickListener(onclick);
        return this;
    }
}
