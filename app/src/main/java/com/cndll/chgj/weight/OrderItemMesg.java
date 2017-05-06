package com.cndll.chgj.weight;

import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;

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
