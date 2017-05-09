package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.activity.MainActivity;
import com.cndll.chgj.adapter.DcListAdapter;
import com.cndll.chgj.adapter.DeshListAdapter;
import com.cndll.chgj.adapter.OnItemClickLister;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.presenter.impl.OrderImpl;
import com.cndll.chgj.mvp.view.OrderView;
import com.cndll.chgj.util.LinearPagerLayoutManager;
import com.cndll.chgj.util.PagerLayoutManager;
import com.cndll.chgj.util.PagingScrollHelper;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.util.StringHelp;
import com.cndll.chgj.weight.KeyWeight;
import com.cndll.chgj.weight.MesgShow;
import com.cndll.chgj.weight.OrderInfo;
import com.cndll.chgj.weight.OrderItemMesg;
import com.cndll.chgj.weight.PopOrderRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderDishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDishFragment extends BaseFragment implements OrderView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_tow)
    LinearLayout titleTow;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.desh_menue_list)
    RecyclerView deshMenueList;
    @BindView(R.id.desh_list)
    RecyclerView deshList;
    @BindView(R.id.number_edit)
    TextView numberEdit;
    @BindView(R.id.desh_name)
    TextView deshName;
    @BindView(R.id.desh_method)
    TextView deshMethod;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.yaoqiu)
    TextView yaoqiu;

    @OnClick(R.id.yaoqiu)
    void onclick_yaoqiu() {
        if (orders != null) {
            if (orders.getOrder(orders.getCurrPosition()).isSend) {
                final PopOrderRequest popOrderRequest = new PopOrderRequest();
                popOrderRequest.init(getContext(), yaoqiu);
                popOrderRequest.setFirstText("赠送");
                popOrderRequest.setSecondText("退菜");
                if (orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0) {
                    popOrderRequest.setThirdVisble(View.GONE);
                } else {
                    popOrderRequest.setThirdText("取消赠送");

                }
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View view) {
                        orders.getOrder(orders.getCurrPosition()).addGiveCount();
                        if (orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0) {
                            popOrderRequest.setThirdVisble(View.GONE);
                        } else {
                            popOrderRequest.setThirdVisble(View.VISIBLE);
                            popOrderRequest.setThirdText("取消赠送");
                        }
                        setOrderInfolayout(orders.getCurrPosition());
                    }

                    @Override
                    public void onSecond(View view) {
                        orders.getOrders().remove(orders.getCurrPosition());
                        if (orders.getOrders().size() == 0) {
                            orders.setCurrPosition(null);
                        } else {
                            orders.setCurrPosition(orders.getOrders().keyAt(0));
                        }
                        setOrderInfolayout(orders.getCurrPosition());
                    }

                    @Override
                    public void onThird(View view) {
                        orders.getOrder(orders.getCurrPosition()).cancelGive();
                        setOrderInfolayout(orders.getCurrPosition());

                    }
                });
                popOrderRequest.show();
            } else {
                final PopOrderRequest popOrderRequest = new PopOrderRequest();
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View v) {

                    }

                    @Override
                    public void onSecond(View view) {
                        if (orders != null) {
                            orders.getOrder(orders.getCurrPosition()).addGiveCount();
                            setOrderInfolayout(orders.getCurrPosition());
                            if (orders.getOrder(orders.getCurrPosition()).getCount() == 0) {
                                view.setBackgroundColor(Color.GRAY);
                            }
                        }
                    }

                    @Override
                    public void onThird(View v) {
                        orders.removeOrders(orders.getCurrPosition());
                        if (orders.getOrders().size() != 0) {
                            orders.setCurrPosition(orders.getOrders().keyAt(0));
                            setOrderInfolayout(orders.getCurrPosition());
                        } else {
                            orderItemMesglayout.setMethod("").setCount("1").setName("").setPrice("");
                            orderInfolayout.setAllMoney("0").setCount("0").setDiscount("0").setGive("0").setLastMoney("0");
                            orders = null;
                            popOrderRequest.dismiss();
                        }
                    }
                });
                popOrderRequest.init(getContext(), yaoqiu);
                popOrderRequest.show();
            }
        }
    }


    @BindView(R.id.item_mesg)
    LinearLayout itemMesg;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.allcount)
    TextView allcount;
    @BindView(R.id.zhekou)
    TextView zhekou;
    @BindView(R.id.zengsong)
    TextView zengsong;
    @BindView(R.id.lastprice)
    TextView lastprice;
    @BindView(R.id.orderInfo)
    LinearLayout orderInfo;

    @BindView(R.id.query)
    Button query;
    @BindView(R.id.send)
    Button send;
    private PopUpViewUtil sendPopview;

    @OnClick(R.id.send)
    void onclick_send() {
        if (sendPopview == null)
            sendPopview = PopUpViewUtil.getInstance();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_order_send, null, false);
        final EditText table, personCount, note;
        table = (EditText) view.findViewById(R.id.tablename);
        personCount = (EditText) view.findViewById(R.id.person_count);
        if (responseOrd != null) {
            personCount.setText(responseOrd.getData().getPernum());
        }
        note = (EditText) view.findViewById(R.id.note);
        Button save, delete, cancel;
        save = (Button) view.findViewById(R.id.save);
        delete = (Button) view.findViewById(R.id.delete);
        cancel = (Button) view.findViewById(R.id.cancel);
        delete.setVisibility(View.GONE);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPopview.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderId == 0) {
                    presenter.sendOrder(new RequestOrder().
                            setItems(orders.getItems()).
                            setMid(AppMode.getInstance().getMid()).
                            setUid(AppMode.getInstance().getUid()).
                            setPernum(personCount.getText().toString()).
                            setSmoney(orderInfolayout.getGivePrice() + "").
                            setSsmoney(orderInfolayout.getLastPrice() + "").
                            setZk(orders.getDisconut() + "").
                            setZkmoney(orderInfolayout.getDiscountPrice() + "").
                            setTmoney(orderInfolayout.getAllPrice() + "").
                            setTabname(tabname).
                            setTab_id(tableId).setPayee("1234").
                            setYsmoney(orderInfolayout.getLastPrice() + ""));
                } else {
                    presenter.updateOreder(new RequestOrder().setId(orderId + "").
                            setItems(orders.getItems()).
                            setMid(AppMode.getInstance().getMid()).
                            setUid(AppMode.getInstance().getUid()).
                            setPernum(personCount.getText().toString()).
                            setSmoney(orderInfolayout.getGivePrice() + "").
                            setSsmoney(orderInfolayout.getLastPrice() + "").
                            setZk(orders.getDisconut() + "").
                            setZkmoney(orderInfolayout.getDiscountPrice() + "").
                            setTmoney(orderInfolayout.getAllPrice() + "").
                            setTabname(tabname).
                            setTab_id(tableId).setPayee("1234").
                            setYsmoney(orderInfolayout.getLastPrice() + "").
                            setType("0").
                            setCre_tm(responseOrd.getData().getCre_tm()).
                            setE_tm(responseOrd.getData().getE_tm()).
                            setOrdernum(responseOrd.getData().getOrdernum()).
                            setOrdnum(responseOrd.getData().getOrdnum()).
                            setYm(responseOrd.getData().getYm()).setWritedishs(responseOrd.getData().getWritedishs()).setAllremarks(responseOrd.getData().getAllremarks())
                            .setStorename(responseOrd.getData().getStorename())
                            .setType_txt(responseOrd.getData().getType_txt())
                    );
                    // updateItems();
                }
            }
        });
        if (tabname == null) {
            table.setText("");
        } else {
            table.setText(tabname);
        }
        sendPopview.popListWindow(send, view,
                sendPopview.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                sendPopview.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3,
                Gravity.NO_GRAVITY, null);
    }

    private void updateItems() {
        //  presenter.updateOreder(new RequestUpdataDeshItems().setId(orderId + "").setItems(orders.getItems()));
    }

    @BindView(R.id.info)
    Button info;

    @OnClick(R.id.info)
    void onclick_info() {
        if (orders != null && orders.getAll() != null) {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(null, null).setOrderList(orders), null);
        } else {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(null, null), null);
        }
    }

    @BindView(R.id.other)
    Button other;
    @BindView(R.id.dazhe)
    Button dazhe;
    @BindView(R.id.pay)
    Button pay;

    @OnClick(R.id.pay)
    void onclick_pay() {
        if (orderId != 0) {
            replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(orderId).setOrders(orders), null);

        }
    }

    Unbinder unbinder;

    @OnClick(R.id.number_edit)
    void onclick_numberEdit() {
        if (orders != null) {
            popUpkey(0, "", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {

                }

                @Override
                public void onKeySure(String s) {

                    if (StringHelp.isFloat(s)) {
                        orders.getOrder(orders.getCurrPosition()).setCount(Float.valueOf(s));
                        setOrderInfolayout(orders.getCurrPosition());
                    }


                }

                @Override
                public void onKeyNub(String s) {

                }
            });
        }
    }


    private void popUpkey(int mode, String hint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.init(getContext(), numberEdit, 2);
        keyWeight.setOnKeyClick(onKeyClick);
    }


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderDishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDishFragment newInstance(String param1, String param2) {
        OrderDishFragment fragment = new OrderDishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public String getTableId() {
        return tableId;
    }

    public OrderDishFragment setTableId(String tableId) {
        this.tableId = tableId;
        return this;
    }

    private String tableId;

    public String getTabname() {
        return tabname;
    }

    public OrderDishFragment setTabname(String tabname) {
        this.tabname = tabname;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderDishFragment setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    private int orderId;
    private String tabname;
    // private List<RequestOrder.ItemsBean> order = new ArrayList<>();
    private OrderInfo orderInfolayout;
    private OrderItemMesg orderItemMesglayout;

    private MainActivity.BackPressEvent backPressEvent = new MainActivity.BackPressEvent() {
        @Override
        public void onBackPress() {
            if (!isSend) {
                MesgShow.showMesg("", "有菜品未送单，确定退出？", info, new MesgShow.OnButtonListener() {
                    @Override
                    public void onListerner() {
                        popBackFragment();
                    }
                }, new MesgShow.OnButtonListener() {
                    @Override
                    public void onListerner() {

                    }
                }, true);
            } else {
                popBackFragment();
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_desh, container, false);
        unbinder = ButterKnife.bind(this, view);
        initlistview();
        orderInfolayout = new OrderInfo();
        orderItemMesglayout = new OrderItemMesg();
        orderItemMesglayout.init(itemMesg);
        orderInfolayout.init(orderInfo);

        return view;
    }

    private DeshListAdapter deshListAdapter;
    private DcListAdapter dcListAdapter;
    private Orders orders;

    @Override
    public void onResume() {
        super.onResume();
        if (orders != null) {
            setOrderInfolayout(orders.getCurrPosition());
        }
        MainActivity.setBackPressEvent(backPressEvent);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.removeBackPressEvent(backPressEvent);
    }

    private void initlistview() {

        deshListAdapter = new DeshListAdapter();
        dcListAdapter = new DcListAdapter();
        dcListAdapter.setOnItemClickLister(new OnItemClickLister() {
            @Override
            public void OnItemClick(View view, int position) {
                dcId = dcListAdapter.getMitems().get(position).getId();
                getDeshList(dcId);
            }
        });
        deshList.setAdapter(deshListAdapter);
        deshMenueList.setAdapter(dcListAdapter);

        /*HorizontalPageLayoutManager gridLayoutManager = new HorizontalPageLayoutManager(5, 3);
        deshList.setLayoutManager(gridLayoutManager);*/
        PagerLayoutManager horizontalPageLayoutManager = new PagerLayoutManager(getContext(), 6, 3);
        deshList.setLayoutManager(horizontalPageLayoutManager);
        LinearPagerLayoutManager pagerLayoutManaer = new LinearPagerLayoutManager(getContext(), 6, 1);
        deshMenueList.setLayoutManager(pagerLayoutManaer);
        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        scrollHelper.setUpRecycleView(deshList);
        //设置页面滚动监听
        scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                // Toast.makeText(getActivity(), "" + index, Toast.LENGTH_SHORT).show();
            }
        });
        deshListAdapter.setOnItemClickLister(new OnItemClickLister() {
            @Override
            public void OnItemClick(View view, int position) {
                if (orders == null) {
                    orders = new Orders();
                }
                if (orders.Iscontan(deshListAdapter.getMitems().get(position).getId())) {

                } else {
                    Orders.Order order = new Orders.Order();
                    order.setItemsBean(deshListAdapter.getMitems().get(position).
                            setCount(1 + ""));
                    orders.setOrders(deshListAdapter.getMitems().get(position).getId(), order);

                }
                orders.setCurrPosition(deshListAdapter.getMitems().get(position).getId());
                numberEdit.setText(orders.getOrder(orders.getCurrPosition()).getCount() + "");
                setOrderInfolayout(deshListAdapter.getMitems().get(position).getId());
                isSend = false;
            }
        });
        if (presenter == null) {
            presenter = new OrderImpl();
            presenter.setView(this);
        }
        if (orderId != 0) {
            presenter.getOrder(new RequestGetOrder().setId(orderId));
        }
        presenter.getDcList(new RequestPrintList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
    }

    private void setOrderInfolayout(Orders.Order id) {
        if (orderItemMesglayout != null)
            orderItemMesglayout.setPrice(id.getAllPrice() + "").
                    setName(id.getDeshName() + id.getDeshPrice()).setMethod(id.getMethodName() + id.getMethodPrice());
        if (orderInfolayout != null) {
            orderInfolayout.setMesg(orders);
        }
    }

    private void setOrderInfolayout(String id) {
        if (id == null) {
            orderItemMesglayout.setMethod("").setCount("1").setName("").setPrice("");
        } else {
            if (orderItemMesglayout != null)
                orderItemMesglayout.
                        setPrice(orders.getOrder(id).getAllPrice() + "").
                        setName(orders.getOrder(id).getDeshName() + orders.getOrder(id).getDeshPrice()).setMethod(orders.getOrder(id).getMethodName() + orders.getOrder(id).getMethodPrice()).setCount(orders.getOrder(id).getCount() + orders.getOrder(id).getGiveCount() + "");

        }
        if (orderInfolayout != null) {
            orderInfolayout.setMesg(orders);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void setTitle() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMesg(String mesg) {
        MesgShow.showMesg("", mesg, info, new MesgShow.OnButtonListener() {
            @Override
            public void onListerner() {

            }
        }, new MesgShow.OnButtonListener() {
            @Override
            public void onListerner() {

            }
        }, false);
    }

    @Override
    public void showProg(String mesg) {

    }


    private OrderPresenter presenter;

    @Override
    public void setPresenter(OrderPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    private boolean isFirstLoadDc = true;
    private String dcId;

    @Override
    public void setDcList(List<ResponseGetCaileiList.DataBean> data) {
        dcListAdapter.setMitems(data);
        if (isFirstLoadDc) {
            dcId = data.get(0).getId();
            isFirstLoadDc = false;
        }
        getDeshList(dcId);
    }

    private void getDeshList(String dcid) {
        presenter.getDeshList(new RequestGetCaipinList().
                setDc_id(dcid).
                setUid(AppMode.getInstance().getUid()).
                setMid(AppMode.getInstance().getMid()));
    }

    @Override
    public void setDeshList(List<ResponseGetCaipinList.DataBean> deshList) {
        deshListAdapter.setMitems(deshList);
    }

    @Override
    public void sendSucc() {
        if (sendPopview != null) {
            sendPopview.dismiss();
        }
        isSend = true;
    }

    private boolean isSend = true;
    private ResponseGetOrder responseOrd;

    @Override
    public void setOrder(ResponseGetOrder getOrder) {
        if (orders == null) {
            orders = new Orders();
        }
        this.responseOrd = getOrder;
        List<ResponseGetCaipinList.DataBean> itemsBeen = getOrder.getData().getItems();
        for (int i = 0; i < itemsBeen.size(); i++) {
            if (orders.getOrders().containsKey(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id())) {
                orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).setSend(true).
                        setCount(Float.valueOf(Float.valueOf(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getCount()) + Float.valueOf(itemsBeen.get(i).getCount()))).
                        setGiveCount(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getGiveCount() + itemsBeen.get(i).
                                getGiveCount());
            } else {
                orders.setOrders(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id(),
                        new Orders.Order().setItemsBean(itemsBeen.get(i)).setSend(true).setCount(Float.valueOf(itemsBeen.get(i).getCount())).
                                setGiveCount(itemsBeen.get(i).
                                        getGiveCount()));
            }
        }
        orders.setCurrPosition(orders.getOrders().keyAt(0));
        setOrderInfolayout(orders.getCurrPosition());
        isSend = true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static class Orders {
        public ArrayMap<String, Order> getOrders() {
            return orders;
        }

        private ArrayMap<String, Order> orders = new ArrayMap<>();

        public float getDisconut() {
            return disconut;
        }

        public void setDisconut(float disconut) {
            this.disconut = disconut;
        }

        private float disconut = 1;

        public String getCurrPosition() {

            return currPosition;
        }

        public void setCurrPosition(String currPosition) {
            this.currPosition = currPosition;
        }

        String currPosition;

        public void setOrders(String id, Order order) {
            orders.put(id, order);
        }

        public void removeOrders(String id) {
            orders.remove(id);
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
            for (int i = 0; i < orderList.size(); i++) {
                itemsBeen.add(orderList.get(i).getItemsBean());
            }
            return itemsBeen;
        }

        public static class Order {
            public Order setCount(float count) {
                this.count = count;
                itemsBean.setCount((this.count) + "");
                return this;
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

            boolean isSend = false;
            float count = 1;

            public float getGiveCount() {
                return giveCount;
            }

            float giveCount;

            public Order setItemsBean(ResponseGetCaipinList.DataBean itemsBean) {
                this.itemsBean = itemsBean;
                return this;
            }

            public ResponseGetCaipinList.DataBean getItemsBean() {
                return itemsBean;
            }

            ResponseGetCaipinList.DataBean itemsBean;/*= new RequestOrder.ItemsBean().setDish_num(1);*/
            List<RequestAddMethod> methods = new ArrayList<>();

            public Order setGiveCount(float count) {
                this.giveCount = count;
                itemsBean.setGiveCount((int) giveCount);
                return this;
            }

            public Order cancelGive() {
                count = count + giveCount;
                giveCount = 0;
                itemsBean.setGiveCount((int) giveCount);
                itemsBean.setCount((int) count + "");
                return this;
            }

            public Order addGiveCount() {
                if (count >= 1) {
                    giveCount = giveCount + 1;
                    count = count - 1;
                }
                itemsBean.setCount(count + "");
                itemsBean.setGiveCount((int) (this.giveCount));
                return this;
            }

            public String getDeshName() {
                return itemsBean.getName();
            }

            public float getDeshPrice() {
                return count * Float.valueOf(itemsBean.getPrice());
            }

            public String getMethodName() {
                if (methods != null && methods.size() > 0) {
                    return methods.get(0).getName();
                }
                return "";
            }

            public String getMethodPrice() {
                if (methods != null && methods.size() > 0) {
                    return methods.get(0).getPrice();
                }
                return "";
            }

            public float getGivePrice() {
                return giveCount * Float.valueOf(itemsBean.getPrice());
            }

            public float getAllPrice() {
                float price = 0;
                for (int i = 0; i < methods.size(); i++) {
                    price = price + Float.valueOf(methods.get(i).getPrice());
                }
                return price + Float.valueOf(itemsBean.getPrice()) * count;

            }
        }
    }

}
