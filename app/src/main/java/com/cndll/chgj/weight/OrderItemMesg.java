package com.cndll.chgj.weight;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.StringHelp;

/**
 * Created by kongqing on 2017/5/5.
 */

public class OrderItemMesg {
    private TextView name, method, price, countEdit, rquest;

    public boolean isList() {
        return isList;
    }

    public void setList(boolean list) {
        isList = list;
    }

    private boolean isList;

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
        this.countEdit.setText(StringHelp.float2Int(name));
        if (name.equals(" ")) {
            this.countEdit.setVisibility(View.GONE);
            this.rquest.setVisibility(View.GONE);
        } else {
            this.countEdit.setVisibility(View.VISIBLE);
            this.rquest.setVisibility(View.VISIBLE);
        }
        if ((StringHelp.isFloat(name) && Float.valueOf(name) == 0) || name.equals("0.0")) {
            countEdit.setBackgroundResource(R.drawable.shape_button_miss);
            rquest.setBackgroundResource(R.drawable.shape_button_miss);
            countEdit.setEnabled(false);
            rquest.setEnabled(false);
        } else {
            if (isList) {
                countEdit.setBackgroundResource(R.drawable.shape_fillet_solid_blue);
                countEdit.setTextColor(Color.WHITE);
            } else {
                countEdit.setBackgroundResource(R.drawable.shape_dialog_fillet_solid);
            }
            rquest.setBackgroundResource(R.drawable.shape_dialog_fillet_solid);
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
