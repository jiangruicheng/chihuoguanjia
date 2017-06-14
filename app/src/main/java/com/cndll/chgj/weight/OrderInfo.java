package com.cndll.chgj.weight;

import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.fragment.OrderDishFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongqing on 2017/5/5.
 */

public class OrderInfo {
    private View view;
    private TextView vcount, allMoney, discount, give, lastMoney;

    public float getCount() {
        return count;
    }

    public float getAllPrice() {
        return allPrice;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public float getGivePrice() {
        return givePrice;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    float count = 0, allPrice = 0, discountPrice = 0, givePrice = 0, lastPrice = 0, allDiscountPrice = 0;

    public void setMesg(OrderDishFragment.Orders order) {
        count = 0;
        allPrice = 0;
        discountPrice = 0;
        givePrice = 0;
        lastPrice = 0;
        allDiscountPrice = 0;
        if (order == null) {
            return;
        }
        List<OrderDishFragment.Orders.Order> orders = order.getAll();
        if (orders == null) {
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            count = count + orders.get(i).getCount() /*+ orders.get(i).getGiveCount()*/;
            allPrice = allPrice + orders.get(i).getAllPrice();
            if (orders.get(i).getItemsBean().getIs_discount().equals("0")) {
                orders.get(i).getItemsBean().setZkmoney("0");
            } else {
                allDiscountPrice = allDiscountPrice + orders.get(i).getAllPrice();
                orders.get(i).getItemsBean().setZkmoney(orders.get(i).getAllPrice() - (orders.get(i).getCount() /*- orders.get(i).getGiveCount()*/) * Float.valueOf(orders.get(i).getItemsBean().getPrice()) * order.getDisconut() + "");
            }
            orders.get(i).getItemsBean().setSmoney(orders.get(i).getGivePrice() + "");
            givePrice = givePrice + orders.get(i).getGivePrice();
        }
        if (order.writeDish != null) {
            List<OrderDishFragment.Orders.Write> writes = new ArrayList<>(order.writeDish.values());
            if (order.writeDish != null && order.writeDish.size() > 0) {
                for (int i = 0; i < writes.size(); i++) {
                    count = count + Float.valueOf(writes.get(i).getCount()) /*+ Float.valueOf(writes.get(i).getGiveCount())*/;
                    allPrice = allPrice + Float.valueOf(writes.get(i).getAllPrice());
                    givePrice = givePrice + Float.valueOf(writes.get(i).getGivePrice());
                }
            }
        }
        discountPrice = allDiscountPrice - allDiscountPrice * order.getDisconut();
        /*lastPrice = allPrice * order.getDisconut();*/
        lastPrice = allPrice - discountPrice - givePrice;
        setCount(count + "").setAllMoney(allPrice + "").setDiscount(discountPrice + "").setLastMoney(lastPrice + "").setGive(givePrice + "");

    }

    public void init(View view) {
        this.view = view;
        vcount = (TextView) view.findViewById(R.id.number);
        allMoney = (TextView) view.findViewById(R.id.allcount);
        discount = (TextView) view.findViewById(R.id.zhekou);
        give = (TextView) view.findViewById(R.id.zengsong);
        lastMoney = (TextView) view.findViewById(R.id.lastprice);
    }

    public OrderInfo setCount(String count) {
        this.vcount.setText(count);
        return this;
    }

    public OrderInfo setAllMoney(String count) {
        this.allMoney.setText(count);
        return this;
    }

    public OrderInfo setDiscount(String count) {
        this.discount.setText(count);
        return this;
    }

    public OrderInfo setGive(String count) {
        this.give.setText(count);
        return this;
    }

    public OrderInfo setLastMoney(String count) {
        this.lastMoney.setText(count);
        return this;
    }


}
