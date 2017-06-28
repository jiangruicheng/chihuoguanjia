package com.cndll.chgj.mvp.mode.bean.info;

import android.support.v4.util.ArrayMap;

import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.util.StringHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongqing on 2017/6/27.
 */

public class Orders {
    public boolean isAdd = false;
    public boolean isChange = false;

    public ArrayMap<String, Order> getOrders() {
        return orders;
    }

    public List<RequestOrder.WriteDishBean> getWriteDish() {
        List<RequestOrder.WriteDishBean> w = new ArrayList<>();
        if (writeDish != null) {
            List<Write> s = new ArrayList<>(writeDish.values());
            for (int i = 0; i < s.size(); i++) {
                w.add(s.get(i).getItemsBean());
            }
            return w;
        } else {
            return null;
        }

    }

    private boolean isWritDesh(String s) {
        return orders.containsKey(s);
    }

    public void backDesh(String id) {
        if (isWritDesh(id)) {
            writeDish.get(id).backDesh();
        } else {
            orders.get(id).backDesh();
        }
    }

    public void numbEdit(String id, String count) {
        if (isWritDesh(id)) {
            writeDish.get(id).setCount(Float.valueOf(count));
        } else {
            orders.get(id).setCount(Float.valueOf(count));
        }
    }

    public void giveDesh(String id) {

    }

    public void cancelGive(String id) {

    }

    public ArrayMap<String, Write> writeDish;

    public void addWriteDish(String id, Write writeDishBean) {
        if (writeDish == null) {
            writeDish = new ArrayMap<>();
        }
        writeDish.put(id, writeDishBean);
        writeDishBean.orders = this;
        isChange = true;
        isAdd = true;
    }

    public void removeWriteDish(String id) {
        if (writeDish != null) {
            writeDish.remove(id);
        }
        isChange = true;
    }

    public ArrayMap<String, Order> orders = new ArrayMap<>();

    public float getDisconut() {
        if (disconut == 1) {
            return 0;
        }
        return disconut;
    }

    public void setDisconut(float disconut) {
        if (disconut == 0) {
            this.disconut = 1;
            return;
        }
        this.disconut = disconut;
    }

    List<ResponseGetCaipinList.DataBean.RemarkBean> allRemarklist;
    private float disconut = 1;

    public List<ResponseGetCaipinList.DataBean.RemarkBean> getAllMethod() {
        if (allRemarklist == null) {
            allRemarklist = new ArrayList<>();
        }
        for (int i = 0; i < orders.size(); i++) {
            allRemarklist.add(orders.get(orders.keyAt(i)).getItemsBean().getRemark());
        }

        return allRemarklist;
    }

    public String getCurrPosition() {

        return currPosition;
    }

    public void setCurrPosition(String currPosition) {
        this.currPosition = currPosition;
    }

    String currPosition;

    public void setOrders(String id, Order order) {
        orders.put(id, order);
        order.orders = this;
        isChange = true;
        isAdd = true;
    }

    public void removeOrders(String id) {
        orders.remove(id);
        isChange = true;
    }

    public boolean Iscontan(String id) {
        return orders.containsKey(id);
    }

    public Order getOrder(String id) {
        return orders.get(id);
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    public List<ResponseGetCaipinList.DataBean> getItems() {
        List<ResponseGetCaipinList.DataBean> itemsBeen = new ArrayList<>();
        List<Order> orderList = new ArrayList<>(orders.values());
        boolean isADD = false;
        for (int i = 0; i < orderList.size(); i++) {
            isADD = false;
            if (!orderList.get(i).isSend) {
                orderList.get(i).getItemsBean().setAddCount(orderList.get(i).getCount());
            }
            for (int j = 0; j < itemsBeen.size(); j++) {
                if (itemsBeen.get(j).getId().equals(orderList.get(i).getItemsBean().getId())) {
                    float back1 = orderList.get(i).getItemsBean().getBackCount() == null ? 0 : Float.valueOf(orderList.get(i).getItemsBean().getBackCount());
                    float back2 = itemsBeen.get(j).getBackCount() == null ? 0 : Float.valueOf(itemsBeen.get(j).getBackCount());
                    float back3 = back1 + back2;
                    itemsBeen.get(j).setCount(Float.valueOf(orderList.get(i).getItemsBean().getCount()) + Float.valueOf(itemsBeen.get(j).getCount()) + "").
                            setGiveCount(orderList.get(i).getItemsBean().getGiveCount() + itemsBeen.get(j).getGiveCount()).
                            setAddCount(orderList.get(i).getItemsBean().getAddCount() + itemsBeen.get(j).getAddCount()).
                            setBackCount(back3 + "");
                    isADD = true;
                }
            }
            if (isADD) {
                continue;
            }
            itemsBeen.add(orderList.get(i).getItemsBean());

        }
        return itemsBeen;
    }

    public static class Write {
        public Orders getOrders() {
            return orders;
        }

        public Write setOrders(Orders orders) {
            this.orders = orders;
            return this;
        }

        Orders orders;

        public Write setCount(float count) {
            this.count = count;
            itemsBean.setCount((this.count) + "");
            orders.isChange = true;

            return this;
        }


        public float getCount() {
            return count;
        }

        public boolean isSend() {
            return isSend;
        }

        public Write setSend(boolean send) {
            isSend = send;
            return this;
        }

        public float getBackCount() {
            return backCount;
        }

        public void setBackCount(float a) {
            backCount = Float.valueOf(backCount) + a;
            itemsBean.setBackCount(backCount + "");

        }

        float backCount;
        public boolean isSend = false;
        float count = 1;

        public float getGiveCount() {
            return giveCount;
        }

        public Write setBackCount(String backCount) {
            if (backCount == null) {
                return this;
            } else {
                this.backCount = Float.valueOf(backCount);
            }
            return this;
        }

        public float giveCount;

        public Write setItemsBean(RequestOrder.WriteDishBean itemsBean) {
            this.itemsBean = itemsBean;
            return this;
        }

        public RequestOrder.WriteDishBean getItemsBean() {
            return itemsBean;
        }

        public RequestOrder.WriteDishBean itemsBean;/*= new RequestOrder.ItemsBean().setDish_num(1);*/

        public Write setGiveCount(float count) {
            this.giveCount = count;
            itemsBean.setGiveCount((int) giveCount + "");
            orders.isChange = true;
            return this;
        }

        public float backCountOnce = 0;

        public void backDesh() {

            if (count - giveCount > 0) {
                if (count - giveCount > 0 && count - giveCount < 1) {
                    backCountOnce = count - (int) count;
                    count = 0;
                } else {
                    count = count - 1;
                    backCountOnce = 1;
                }
            } else if (giveCount > 0) {
                giveCount = giveCount - 1;
                count = count - 1;
                backCountOnce = 1;
            }
            setBackCount(backCountOnce);
            itemsBean.setGiveCount((int) giveCount + "");
            itemsBean.setCount(count + "");
            orders.isChange = true;
        }

        public Write cancelGive() {
            // count = count + giveCount;
            giveCount = 0;
            itemsBean.setGiveCount((int) giveCount + "");
            //itemsBean.setCount((int) count + "");
            orders.isChange = true;
            return this;
        }

        public Write addGiveCount() {
            if (count - giveCount >= 1) {
                giveCount = giveCount + 1;
                // count = count - 1;
            }
            //itemsBean.setCount(count + "");
            itemsBean.setGiveCount((int) (this.giveCount) + "");
            orders.isChange = true;
            return this;
        }

        public String getDeshName() {
            return itemsBean.getName();
        }

        public float getDeshPrice() {
            return count * Float.valueOf(itemsBean.getPrice());
        }

        public float getOnePrice() {
            return Float.valueOf(itemsBean.getPrice());
        }

        public String getMethodName() {
            if (itemsBean.getRemarks() != null && itemsBean.getRemarks().size() > 0) {
                return itemsBean.getRemarks().get(0).getName();
            }
            return "";
        }

        public String getMethodPrice() {
            if (itemsBean.getRemarks() != null && itemsBean.getRemarks().size() > 0) {
                return itemsBean.getRemarks().get(0).getPrice();
            }
            return "";
        }

        public float getGivePrice() {
            return giveCount * Float.valueOf(itemsBean.getPrice());
        }

        public float getAllPrice() {
            float price = 0;
            if (itemsBean.getRemarks() != null && itemsBean.getRemarks() != null)
                for (int i = 0; i < itemsBean.getRemarks().size(); i++) {
                    if (itemsBean.getRemarks().get(i).getPrice() != null && StringHelp.isFloat(itemsBean.getRemarks().get(i).getPrice()))
                        price = price + Float.valueOf(itemsBean.getRemarks().get(i).getPrice());
                }
            return price + Float.valueOf(itemsBean.getPrice()) * count;

        }
    }

    public static class Order {
        public Order() {

        }

        public float getBackCount() {
            return backCount;
        }

        public void setBackCount(float a) {
            backCount = Float.valueOf(backCount) + a;
            itemsBean.setBackCount(backCount + "");
        }

        public Order setBackCount(String backCount) {
            if (backCount == null) {
                return this;
            }
            this.backCount = Float.valueOf(backCount);
            return this;
        }

        float backCount;

        public Orders getOrders() {
            return orders;
        }

        public Order setOrders(Orders orders) {
            this.orders = orders;
            return this;
        }

        Orders orders;

        public Order setCount(float count) {
            this.count = count;
            itemsBean.setCount((this.count) + "");
            orders.isChange = true;
            return this;
        }

        public float backCountOnce = 0;

        public void backDesh() {
            backCountOnce = 0;
            if (count - giveCount > 0) {
                if (count - giveCount > 0 && count - giveCount < 1) {
                    backCountOnce = count - (int) count;
                    count = 0;
                } else {
                    count = count - 1;
                    backCountOnce = 1;
                }

            } else if (giveCount > 0) {
                giveCount = giveCount - 1;
                count = count - 1;
                backCountOnce = 1;
            }
            setBackCount(backCountOnce);
            itemsBean.setGiveCount((int) giveCount);
            itemsBean.setCount(count + "");
            orders.isChange = true;
        }

        public float getCount() {
            return count;
        }

        public boolean isSend() {
            return isSend;
        }

        public Order setSend(boolean send) {
            isSend = send;
            return this;
        }

        public boolean isSend = false;
        float count = 1;

        public float getGiveCount() {
            return giveCount;
        }

        public float giveCount;

        public Order setItemsBean(ResponseGetCaipinList.DataBean itemsBean) {
            this.itemsBean = itemsBean;
            return this;
        }

        public ResponseGetCaipinList.DataBean getItemsBean() {
            return itemsBean;
        }

        public ResponseGetCaipinList.DataBean itemsBean;/*= new RequestOrder.ItemsBean().setDish_num(1);*/

        public Order setGiveCount(float count) {
            this.giveCount = count;
            itemsBean.setGiveCount((int) giveCount);
            orders.isChange = true;
            itemsBean.setSmoney(giveCount * Float.valueOf(itemsBean.getPrice()) + "");
            return this;
        }

        public Order cancelGive() {
            // count = count + giveCount;
            giveCount = 0;
            itemsBean.setGiveCount((int) giveCount);
            // itemsBean.setCount((int) count + "");
            itemsBean.setSmoney(giveCount * Float.valueOf(itemsBean.getPrice()) + "");
            orders.isChange = true;
            return this;
        }

        public Order addGiveCount() {
            if (count - giveCount >= 1) {
                giveCount = giveCount + 1;
                //count = count - 1;
            }
            // itemsBean.setCount(count + "");
            itemsBean.setGiveCount((int) (this.giveCount));
            orders.isChange = true;
            return this;
        }

        public String getDeshName() {
            return itemsBean.getName();
        }

        public float getDeshPrice() {
            return count * Float.valueOf(itemsBean.getPrice());
        }

        public float getOnePrice() {
            return Float.valueOf(itemsBean.getPrice());
        }

        public String getMethodName() {
            if (itemsBean.getRemark() != null && itemsBean.getRemark().getRemarks().size() > 0) {
                return itemsBean.getRemark().getRemarks().get(0).getName();
            }
            return "";
        }

        public String getMethodPrice() {
            if (itemsBean.getRemark() != null && itemsBean.getRemark().getRemarks().size() > 0) {
                return itemsBean.getRemark().getRemarks().get(0).getPrice();
            }
            return "";
        }

        public float getGivePrice() {
            return giveCount * Float.valueOf(itemsBean.getPrice());
        }

        public float getAllPrice() {
            float price = 0;
            if (itemsBean.getRemark() != null && itemsBean.getRemark().getRemarks() != null)
                for (int i = 0; i < itemsBean.getRemark().getRemarks().size(); i++) {
                    if (itemsBean.getRemark().getRemarks().get(i).getPrice() != null && StringHelp.isFloat(itemsBean.getRemark().getRemarks().get(i).getPrice()))
                        price = price + Float.valueOf(itemsBean.getRemark().getRemarks().get(i).getPrice());
                }
            //return (price + Float.valueOf(itemsBean.getPrice())) * count;
            return (price + Float.valueOf(itemsBean.getPrice())) * Float.valueOf(itemsBean.getCount());

        }
    }
}
