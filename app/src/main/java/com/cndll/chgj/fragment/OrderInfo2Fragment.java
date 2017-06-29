package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.OnItemClickLister;
import com.cndll.chgj.adapter.OrderDeskListAdapter;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.info.Orders;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintBackDesh;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.presenter.impl.NoteImpl;
import com.cndll.chgj.mvp.presenter.impl.OrderImpl;
import com.cndll.chgj.mvp.view.OrderView;
import com.cndll.chgj.util.LinearPagerLayoutManager;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.util.StringHelp;
import com.cndll.chgj.weight.AllLayout;
import com.cndll.chgj.weight.KeyUtuil;
import com.cndll.chgj.weight.KeyWeight;
import com.cndll.chgj.weight.MesgShow;
import com.cndll.chgj.weight.OrderInfo;
import com.cndll.chgj.weight.OrderItemMesg;
import com.cndll.chgj.weight.PopOrderRequest;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderInfo2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInfo2Fragment extends BaseFragment implements OrderView {
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
    @BindView(R.id.order_list)
    ListView orderListView;
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
    LinearLayout orderinfolayout;
    @BindView(R.id.other)
    Button other;
    @BindView(R.id.txt_discount)
    TextView txtDiscount;
    @BindView(R.id.layout_mode_have_tesk)
    LinearLayout layoutModeHaveTesk;
    @BindView(R.id.pay_nodesk)
    Button payNodesk;

    @OnClick(R.id.pay_nodesk)
    void onclick_paynodesk() {

        popUpkey(KeyWeight.Mode_OnlyNumb, Color.rgb(171, 171, 171), Color.rgb(1, 169, 104), "请输入桌台号或牌号", "取消", "确认", new KeyWeight.OnKeyClick() {
            @Override
            public void onKeyCancel(String s) {

            }

            @Override
            public void onKeySure(String s) {
                showProg("");
                AppRequest.getAPI().sendOrd(new RequestOrder().
                        setItems(order.getItems()).
                        setMid(AppMode.getInstance().getMid()).
                        setUid(AppMode.getInstance().getUid()).
                        setPernum("1").
                        setSmoney(orderInfo.getGivePrice() + "").
                        setSsmoney(orderInfo.getLastPrice() + "").
                        setZk(order.getDisconut() + "").
                        setZkmoney(orderInfo.getDiscountPrice() + "").
                        setTmoney(orderInfo.getAllPrice() + "").
                        setTabname(s).
                        setTab_id(0 + "").setPayee("1234").
                        setYsmoney(orderInfo.getLastPrice() + "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(OrderInfo2Fragment.this) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        disProg();
                        disProg();
                        super.onNext(baseResponse);
                        if (baseResponse.getCode() == 1) {
                            if (((ResponseAddOrd) baseResponse).getData().getOid() != 0) {
                                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(((ResponseAddOrd) baseResponse).getData().getOid()).setOrders(order), null);
                                // presenter.printSetting(((ResponseAddOrd) baseResponse).getData().getOid());
                            }

                        }
                    }
                });

            }

            @Override
            public void onKeyNub(String s) {

            }
        });
    }

    @OnClick(R.id.other)
    void onclick_other() {
        final PopviewOther popviewOther = new PopviewOther();
        popviewOther.init();
        popviewOther.popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                popviewOther.unbinder.unbind();
            }
        });
        popviewOther.trunDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popviewOther.dismiss();
                if (orderId == 0) {
                    showMesg("此台不存在消费，无需转台");
                    return;
                }
                final PopUpViewUtil popviewDesk = PopUpViewUtil.getInstance();
                View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_turndesk, null, false);
                RecyclerView deskList = (RecyclerView) view.findViewById(R.id.desk_list);
                final OrderDeskListAdapter orderDeskListAdapter = new OrderDeskListAdapter();
                deskList.setAdapter(orderDeskListAdapter);
                LinearPagerLayoutManager layoutManager = new LinearPagerLayoutManager(getContext(), 3, 4);
                deskList.setLayoutManager(layoutManager);

                orderDeskListAdapter.setOnItemClickLister(new OnItemClickLister() {
                    @Override
                    public void OnItemClick(View view, final int position) {
                        showMesg("确定转到" + orderDeskListAdapter.getItems().get(position).getName(), new MesgShow.OnButtonListener() {
                            @Override
                            public void onListerner() {
                                presenter.turnOrder(orderId + "", orderDeskListAdapter.getItems().get(position).getName(), orderDeskListAdapter.getItems().get(position).getId());
                                popviewDesk.dismiss();

                            }
                        });
                    }
                });
                Button cancel = (Button) view.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popviewDesk.dismiss();
                    }
                });
                int[] locations = new int[2];
                orderListView.getLocationOnScreen(locations);
                popviewDesk.popListWindow(send, view,
                        popviewDesk.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                        orderListView.getHeight(),
                        Gravity.NO_GRAVITY, locations);
                AppRequest.getAPI().getDeskList(new RequestGetDeskList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setIsoc(2 + "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new MObeserver(OrderInfo2Fragment.this) {
                            @Override
                            public void onCompleted() {
                                super.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }

                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                super.onNext(baseResponse);
                                if (baseResponse.getCode() == 1) {
                                    orderDeskListAdapter.setItems(((ResponseGetDeskList) baseResponse).getData());
                                }
                            }
                        });
            }
        });
        popviewOther.printOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popviewOther.dismiss();
                if (orderId == 0) {
                    showMesg("此台不存在消费，无需打印");
                    return;
                }
                if (order.isChange) {
                    showMesg("有菜品未送单，不能打印");
                } else {
                    printOrders();
                }
            }
        });
        popviewOther.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popviewOther.dismiss();
            }
        });
        popviewOther.removeDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderId == 0) {
                    showMesg("此台不存在消费，无需撤台");
                    return;
                }
                if (orderId != 0) {
                    showMesg("撤台将会撤销消费，是否确认", new MesgShow.OnButtonListener() {
                        @Override
                        public void onListerner() {
                            presenter.removeOrder(orderId + "", 2 + "");
                            popviewOther.dismiss();
                        }
                    });
                }
            }
        });
        popviewOther.writeDesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_order_write, null, false);
                final EditText name, number, price;
                Button cancel, delete, save;
                final PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
                name = (EditText) view.findViewById(R.id.name);
                number = (EditText) view.findViewById(R.id.number);
                price = (EditText) view.findViewById(R.id.price);
                cancel = (Button) view.findViewById(R.id.cancel);
                delete = (Button) view.findViewById(R.id.delete);
                save = (Button) view.findViewById(R.id.save);
                delete.setVisibility(View.GONE);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpViewUtil.dismiss();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestOrder.WriteDishBean writeDishBean = new RequestOrder.WriteDishBean();
                        if (order == null) {
                            order = new Orders();
                        }
                        order.addWriteDish(name.getText().toString(), new
                                Orders.Write().setOrders(order).setSend(false).
                                setItemsBean(writeDishBean.
                                        setIsWrite("1").
                                        setCount(number.getText().toString()).
                                        setGiveCount("0").
                                        setPrice(price.getText().toString()).
                                        setName(name.getText().toString())).
                                setCount(Float.valueOf(number.getText().toString())).setGiveCount(0));
                        adapter.notifyDataSetChanged();
                    }
                });
                popUpViewUtil.popListWindow(send, view,
                        popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                        popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3,
                        Gravity.NO_GRAVITY, null);
            }
        });
        popviewOther.popUpViewUtil.popListWindow(send, popviewOther.view,
                popviewOther.popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                popviewOther.popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3,
                Gravity.BOTTOM, null);
    }

    @BindView(R.id.send)
    Button send;

    @OnClick(R.id.send)
    void onclick_send() {
        if (order == null) {
            showMesg("不存在未送单菜品，无需送单");
            return;
        }
        if (order.getOrders().size() != 0 || order.writeDish.size() != 0) {
            replaceFragmentAddToBackStack(SendFragment.newInstance(null, null).setOrderDishFragment((OrderDishFragment) fragmentList.get(fragmentList.size() - 2)), new OrderImpl());
        }
    }

    @BindView(R.id.pay)
    Button pay;

    @OnClick(R.id.pay)
    void onclick_pay() {
        // replaceFragmentAddToBackStack(ApplyPayFragment.newInstance(null, null), null);
        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isOrder()) {
            showMesg("无买单权限");
            return;
        }
        if (orderId == 0) {
            showMesg("此台不存在消费，无需买单");
            return;
        }
        if (!order.isChange) {
            if (orderId != 0) {
                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(orderId).setOrders(order), null);
            }
        } else {
            MesgShow.showMesg("", "有菜品未送单,不能买单", dazhe, null, null, false);
        }
    }

    @BindView(R.id.dazhe)
    Button dazhe;

    @OnClick(R.id.dazhe)
    void onclick_discount() {
        if (orderId == 0) {
            showMesg("此台不存在消费，无需打折");
            return;
        }
        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isDiscount()) {
            showMesg("无打折权限");
            return;
        }
        if (!order.isChange && orderId != 0) {
            popUpkey(2, Color.rgb(171, 171, 171), Color.rgb(1, 169, 104), "请输入折扣，例如8折则输入0.8", "取消打折", "确定", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {
                    order.setDisconut(1);
                    orderInfo.setMesg(order);
                    sendOrds();
                }

                @Override
                public void onKeySure(String s) {

                    if (StringHelp.isFloat(s)) {
                        if (Float.valueOf(s) <= 0.99 && Float.valueOf(s) >= 0.1) {
                            order.setDisconut(Float.valueOf(s));
                            orderInfo.setMesg(order);
                            sendOrds();
                        }
                    }


                }

                @Override
                public void onKeyNub(String s) {

                }
            });
        } else {
            MesgShow.showMesg("", "有菜品未送单,不能打折", dazhe, null, null, false);
        }
    }

    Unbinder unbinder;

    @Override
    public void reload() {
        super.reload();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Orders getOrderList() {
        return order;
    }

    private void printOrders() {
        showProg("");
        toast("正在打印");
        AppRequest.getAPI().printBill(orderId + "", AppMode.getInstance().getPrint_code(), AppMode.getInstance().getUsername()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(OrderInfo2Fragment.this) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                disProg();
                toast(baseResponse.extra);
            }
        });
    }

    private void popUpkey(int mode, int cancelcolor, int surecolor, String showhint, String hint, String sureHint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setCancelText(hint);
        keyWeight.setSureText(sureHint);
        keyWeight.setShowHintText(showhint);
        keyWeight.setCancelcolor(cancelcolor);
        keyWeight.setSurecolor(surecolor);
        keyWeight.init(getContext(), orderListView, mode);
        keyWeight.setOnKeyClick(onKeyClick);
    }

    public OrderInfo2Fragment setOrderList(Orders order) {
        this.order = order;
        if (adapter != null) {
            adapter.setOrderList(order);
        }
        return this;
    }

    private Orders order;

    public OrderInfo2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderInfo2Fragment newInstance(String param1, String param2) {
        OrderInfo2Fragment fragment = new OrderInfo2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private OrderListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    OrderInfo orderInfo;
    OrderItemMesg orderItemMesg;
    private List<RequestPrintBackDesh.ItemsBean> backDesh;

    public int getOrderId() {
        return orderId;
    }

    public OrderInfo2Fragment setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    int orderId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (!AppMode.getInstance().isDeskMode()) {
            //txtDiscount.setVisibility(View.INVISIBLE);
            //zhekou.setVisibility(View.INVISIBLE);
            layoutModeHaveTesk.setVisibility(View.GONE);
            payNodesk.setVisibility(View.VISIBLE);
        } else {
            layoutModeHaveTesk.setVisibility(View.VISIBLE);
            payNodesk.setVisibility(View.GONE);
        }
        title.setText("");
        titleLeft.setText(mParam1);
        titleRight.setText(mParam2);
        adapter = new OrderListAdapter();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        if (order != null) {
            adapter.setOrderList(order);
        }
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selectItem = position;
                adapter.notifyDataSetChanged();
            }
        });
        orderInfo = new OrderInfo();
        orderInfo.init(orderinfolayout);
        orderInfo.setMesg(order);
        adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onNumbClick(Orders orders, String id, final int position, View Item) {
                orders.view = OrderInfo2Fragment.this;
                orders.numbEdit(orders.getCurrPosition(), new KeyUtuil.Builder().setContext(getContext()).setLocation(Item).
                        setDoFuckCancelUnsend(new Orders.DoFuck() {
                            @Override
                            public void doFuck(Object o) {
                                adapter.notifyDataSetChanged();
                            }
                        }).setDoFuckCancelSend(new Orders.DoFuck<List<RequestPrintBackDesh.ItemsBean>>() {
                    @Override
                    public void doFuck(List<RequestPrintBackDesh.ItemsBean> o) {
                        backDesh = o;
                        sendOrds(presenter.BACK);
                        isBackDesh = true;
                    }
                }).setDoFuckSureUnSend(new Orders.DoFuck() {
                    @Override
                    public void doFuck(Object o) {
                        adapter.notifyDataSetChanged();
                    }
                }).setDoFuckSureSend(new Orders.DoFuck() {
                    @Override
                    public void doFuck(Object o) {
                        sendOrds();
                    }
                }));
            }

            @Override
            public void onRequest(final Orders orders, final int position, final String id, View view) {
                adapter.selectItem = position;
                orders.view = OrderInfo2Fragment.this;
                // adapter.notifyDataSetChanged();
                final PopOrderRequest popOrderRequest;
                if (orders.isDeshSend(id)) {
                    popOrderRequest = new PopOrderRequest();
                    popOrderRequest.init(getContext(), view);
                    popOrderRequest.four.setVisibility(View.GONE);
                    popOrderRequest.setFirstText("赠送");
                    popOrderRequest.setSecondText("退菜");
                    popOrderRequest.show();
                    if (orders.isWritDesh(id)) {
                        if (orders.isWritDesh(id))
                            if (order.writeDish.get(order.getCurrPosition()).getGiveCount() == 0) {
                                popOrderRequest.setThirdVisble(View.GONE);
                                popOrderRequest.setViewHeight(2);
                            } else {
                                popOrderRequest.setThirdText("取消赠送");
                                popOrderRequest.setViewHeight(3);
                            }
                    } else {
                        if (order.getOrder(order.getCurrPosition()).getGiveCount() == 0) {
                            popOrderRequest.setThirdVisble(View.GONE);
                            popOrderRequest.setViewHeight(2);
                        } else {
                            popOrderRequest.setThirdText("取消赠送");
                            popOrderRequest.setViewHeight(3);
                        }
                    }
                } else {
                    popOrderRequest = new PopOrderRequest();
                    popOrderRequest.init(getContext(), view);
                    popOrderRequest.show();
                    boolean isGive = false;
                    if (orders.isDeshSend(id)) {
                        if (order.writeDish.get(order.getCurrPosition()).giveCount != 0) {
                            isGive = true;
                        }
                    } else {
                        if (order.getOrder(order.getCurrPosition()).giveCount != 0) {
                            isGive = true;
                        }
                    }
                    if (isGive) {
                        popOrderRequest.setViewHeight(4);
                        popOrderRequest.four.setVisibility(View.VISIBLE);
                        popOrderRequest.four.setText("取消赠送");
                        popOrderRequest.four.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                popOrderRequest.setViewHeight(3);
                                popOrderRequest.four.setVisibility(View.GONE);
                                popOrderRequest.dismiss();
                            }
                        });
                    } else {
                        popOrderRequest.four.setVisibility(View.GONE);
                        popOrderRequest.setViewHeight(3);
                    }

                }
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View view) {

                        if (orders.isDeshSend(id)) {
                            orders.giveDesh(id, new KeyUtuil.Builder().setContext(getContext()).setLocation(view).
                                    setDoFuckSureUnSend(new Orders.DoFuck() {
                                        @Override
                                        public void doFuck(Object o) {
                                            adapter.notifyDataSetChanged();
                                            popOrderRequest.dismiss();
                                        }
                                    }).setDoFuckSureSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    sendOrds(presenter.GIVE);
                                    popOrderRequest.dismiss();
                                }
                            }).setDoFuckCancelSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    popOrderRequest.dismiss();
                                }
                            }));

                        } else {
                            if (orders.isWritDesh(id)) {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setWrite(order.writeDish.get(id)), new NoteImpl());
                            } else {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setOrder(orders.getOrder(id)), new NoteImpl());
                            }
                            popOrderRequest.dismiss();
                        }

                    }

                    @Override
                    public void onSecond(View view) {
                           /* if (order.isAdd) {
                                showMesg("有菜品未送单，请先送单");
                                return;
                            }*/
                         /*   if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isReturn()) {
                                showMesg("无退菜权限");
                                popOrderRequest.dismiss();
                                return;
                            }
                            showMesg("是否确定退菜", new MesgShow.OnButtonListener() {
                                @Override
                                public void onListerner() {
                                    boolean isOrderWrite;
                                    int i;
                                    if (order.writeDish == null || position > order.writeDish.size() - 1) {
                                        isOrderWrite = false;
                                        if (order.writeDish == null) {
                                            i = 0;
                                        } else {
                                            i = order.writeDish.size();
                                        }
                                        order.setCurrPosition(order.getOrders().keyAt(position - i));
                                    } else {
                                        order.setCurrPosition(order.writeDish.keyAt(position));
                                        isOrderWrite = true;
                                    }
                                    backDesh = new ArrayList<RequestPrintBackDesh.ItemsBean>();

                                    if (!isOrderWrite) {
                                        backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(order.getOrder(order.getCurrPosition()).getItemsBean().getName()).
                                                setMoney(order.getOrder(order.getCurrPosition()).getItemsBean().getPrice()).
                                                setNum(*//*order.getOrder(order.getCurrPosition()).getCount() +*//* "1").
                                                setUnit(order.getOrder(order.getCurrPosition()).getItemsBean().getUnit()).
                                                setM_name(""));
                                        order.getOrder(order.getCurrPosition()).backDesh();
                                    } else {
                                        backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(order.writeDish.get(order.getCurrPosition()).getItemsBean().getName()).
                                                setMoney(order.writeDish.get(order.getCurrPosition()).getItemsBean().getPrice()).
                                                setNum(*//*order.writeDish.get(order.getCurrPosition()).getCount() +*//* "1").
                                                setUnit("盘").
                                                setM_name(""));
                                        order.writeDish.get(order.getCurrPosition()).backDesh();
                                    }
                            *//*if (order.getOrders().size() == 0) {
                                order.setCurrPosition(null);
                            } else {
                                order.setCurrPosition(order.getOrders().keyAt(0));
                            }*//*
                                    setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
                                    popOrderRequest.dismiss();
                                    sendOrds(presenter.BACK);
                                    isBackDesh = true;
                                }
                            });*/
                        if (orders.isDeshSend(id)) {
                            orders.backDesh(orders.getCurrPosition(), new Orders.DoFuck<List<RequestPrintBackDesh.ItemsBean>>() {
                                @Override
                                public void doFuck(List<RequestPrintBackDesh.ItemsBean> itemsBeen) {
                                    backDesh = itemsBeen;
                                    sendOrds(presenter.BACK);
                                    isBackDesh = true;
                                    popOrderRequest.dismiss();
                                }
                            });
                        } else {
                            orders.giveDesh(id, new KeyUtuil.Builder().setDoFuckSureUnSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    adapter.notifyDataSetChanged();
                                    popOrderRequest.dismiss();
                                }
                            }).setDoFuckSureSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    sendOrds();
                                    popOrderRequest.dismiss();
                                }
                            }).setDoFuckCancelSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    popOrderRequest.dismiss();
                                }
                            }));
                        }

                    }

                    @Override
                    public void onThird(View view) {
                        if (orders.isDeshSend(id)) {
                            orders.cancelGive(id, new KeyUtuil.Builder().setDoFuckSureSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    sendOrds();
                                    popOrderRequest.dismiss();
                                }
                            }).setDoFuckSureUnSend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    adapter.notifyDataSetChanged();
                                    popOrderRequest.dismiss();
                                }
                            }).setDoFuckCancelUnsend(new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    popOrderRequest.dismiss();
                                }
                            }));
                        } else {
                            orders.deleteDesh(id, new Orders.DoFuck() {
                                @Override
                                public void doFuck(Object o) {
                                    adapter.notifyDataSetChanged();
                                    popOrderRequest.dismiss();
                                }
                            });
                        }

                    }
                });

                 /*else*/ /*{
                    final PopOrderRequest popOrderRequest = new PopOrderRequest();
                    popOrderRequest.init(getContext(), view);
                    popOrderRequest.show();
                    boolean isGive = false;
                    if (orders.isDeshSend(id)) {
                        if (order.writeDish.get(order.getCurrPosition()).giveCount != 0) {
                            isGive = true;
                        }
                    } else {
                        if (order.getOrder(order.getCurrPosition()).giveCount != 0) {
                            isGive = true;
                        }
                    }

                    if (isGive) {
                        popOrderRequest.setViewHeight(4);
                        popOrderRequest.four.setVisibility(View.VISIBLE);
                        popOrderRequest.four.setText("取消赠送");
                        popOrderRequest.four.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                popOrderRequest.setViewHeight(3);
                                popOrderRequest.four.setVisibility(View.GONE);
                                popOrderRequest.dismiss();
                            }
                        });
                    } else {
                        popOrderRequest.four.setVisibility(View.GONE);
                        popOrderRequest.setViewHeight(3);
                    }

                    popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                        @Override
                        public void onFirst(View v) {
                            if (orders.isOrderDesh(id)) {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setWrite(order.writeDish.get(order.getCurrPosition())), new NoteImpl());
                            } else {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setOrder(order.getOrder(order.getCurrPosition())), new NoteImpl());
                            }
                            popOrderRequest.dismiss();
                        }

                        @Override
                        public void onSecond(final View view) {
                            final boolean isOrderWrite;
                            int i;
                            final String currID;
                            if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isGive()) {
                                showMesg("无赠送权限");
                                popOrderRequest.dismiss();
                                return;
                            }
                            if (order.writeDish == null || position > order.writeDish.size() - 1) {
                                isOrderWrite = false;
                                if (order.writeDish == null) {
                                    i = 0;
                                } else {
                                    i = order.writeDish.size();
                                }
                                currID = order.getOrders().keyAt(position - i);
                                order.setCurrPosition(order.getOrders().keyAt(position - i));
                            } else {
                                currID = order.writeDish.keyAt(position);
                                order.setCurrPosition(order.writeDish.keyAt(position));
                                isOrderWrite = true;
                            }
                            if (order != null) {
                                if (!isOrderWrite) {
                                    order.getOrder(order.getCurrPosition()).addGiveCount();
                                } else {
                                    order.writeDish.get(order.getCurrPosition()).addGiveCount();
                                }
                                setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
                                if (!isOrderWrite) {
                                    if (order.getOrder(order.getCurrPosition()).getCount() == 0) {
                                        view.setBackgroundColor(Color.GRAY);
                                    }
                                } else {
                                    if (order.writeDish.get(order.getCurrPosition()).getCount() == 0) {
                                        view.setBackgroundColor(Color.GRAY);
                                    }
                                }
                                popOrderRequest.four.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!isOrderWrite) {
                                            order.getOrder(currID).cancelGive();
                                        } else {
                                            order.writeDish.get(currID).cancelGive();
                                        }
                                        setOrderInfolayout(currID, isOrderWrite);
                                        view.setBackgroundResource(R.color.cube_holo_blue_dark);
                                        popOrderRequest.setViewHeight(3);
                                        popOrderRequest.four.setVisibility(View.GONE);
                                        popOrderRequest.dismiss();
                                    }
                                });
                                popOrderRequest.setViewHeight(4);
                                popOrderRequest.four.setVisibility(View.VISIBLE);
                                popOrderRequest.dismiss();
                                popOrderRequest.four.setText("取消赠送");
                            }
                        }

                        @Override
                        public void onThird(View v) {
                            boolean isOrderWrite;
                            int i;
                            if (order.writeDish == null || position > order.writeDish.size() - 1) {
                                isOrderWrite = false;
                                if (order.writeDish == null) {
                                    i = 0;
                                } else {
                                    i = order.writeDish.size();
                                }
                                order.setCurrPosition(order.getOrders().keyAt(position - i));
                            } else {
                                order.setCurrPosition(order.writeDish.keyAt(position));
                                isOrderWrite = true;
                            }
                            if (isOrderWrite) {
                                order.removeWriteDish(order.getCurrPosition());
                            } else {
                                order.removeOrders(order.getCurrPosition());
                            }
                            popOrderRequest.dismiss();
                            adapter.notifyDataSetChanged();
                        }
                    });

                }*/
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void setOrderInfolayout(String id, boolean iswrite) {
        if (iswrite) {
            setOrderInfolayoutWrite(id);
        } else {
            setOrderInfolayout(id);
        }
        adapter.notifyDataSetChanged();
    }

    public ResponseGetOrder getResponseOrd() {
        return responseOrd;
    }

    public OrderInfo2Fragment setResponseOrd(ResponseGetOrder responseOrd) {
        this.responseOrd = responseOrd;
        return this;
    }

    private ResponseGetOrder responseOrd;

    public String getTabname() {
        return tabname;
    }

    public OrderInfo2Fragment setTabname(String tabname) {
        this.tabname = tabname;
        return this;
    }

    public String getTabID() {
        return tabID;
    }

    public OrderInfo2Fragment setTabID(String tabID) {
        this.tabID = tabID;
        return this;
    }

    private String tabname;
    private String tabID;

    private void sendOrds(int type) {
        if (orderId == 0) {
         /*   orderPresenter.sendOrder(new RequestOrder().
                    setItems(orderDishFragment.orders.getItems()).
                    setMid(AppMode.getInstance().getMid()).
                    setUid(AppMode.getInstance().getUid()).
                    setPernum(personCount.getText().toString()).
                    setSmoney(orderDishFragment.orderInfolayout.getGivePrice() + "").
                    setSsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                    setZk(orderDishFragment.orders.getDisconut() * 10 + "").
                    setZkmoney(orderDishFragment.orderInfolayout.getDiscountPrice() + "").
                    setTmoney(orderDishFragment.orderInfolayout.getAllPrice() + "").
                    setTabname(orderDishFragment.tabname).
                    setTab_id(orderDishFragment.tableId).setPayee("1234").
                    setYsmoney(orderDishFragment.orderInfolayout.getLastPrice() + ""));*/
        } else {
            presenter.updateOreder(new RequestOrder().setId(orderId + "").
                            setItems(order.getItems()).
                            setMid(AppMode.getInstance().getMid()).
                            setUid(AppMode.getInstance().getUid()).
                            setPernum(responseOrd.getData().getPernum()).
                            setSmoney(orderInfo.getGivePrice() + "").
                            setSsmoney(orderInfo.getLastPrice() + "").
                            setZk(order.getDisconut() + "").
                            setZkmoney(orderInfo.getDiscountPrice() + "").
                            setTmoney(orderInfo.getAllPrice() + "").
                            setTabname(tabname).
                            setTab_id(tabID).setPayee(AppMode.getInstance().getUsername()).
                            setYsmoney(orderInfo.getLastPrice() + "").
                            setType("0").
                            setCre_tm(responseOrd.getData().getCre_tm()).
                            setE_tm(responseOrd.getData().getE_tm()).
                            setOrdernum(responseOrd.getData().getOrdernum()).
                            setOrdnum(responseOrd.getData().getOrdnum()).
                            setYm(responseOrd.getData().getYm()).
                            setWritedishs(order.getWriteDish()).
                            setAllremarks(order.getAllMethod())
                            .setStorename(responseOrd.getData().getStorename())
                            .setType_txt(responseOrd.getData().getType_txt())
                    , type);
        }
    }

    private void sendOrds() {
        sendOrds(0);
    }

    private void setOrderInfolayoutWrite(String id) {
        if (id == null) {
            orderItemMesg.setMethod("").setCount("1").setName("").setPrice("");
        }
        if (orderItemMesg != null)
            Log.d("at", "setOrderInfolayoutWrite: " + order.writeDish.get(id));
        Orders.Write write = order.writeDish.get(id);
        orderItemMesg.setPrice(order.writeDish.get(id).getAllPrice() + "").
                setName(order.writeDish.get(id).getDeshName() + order.writeDish.get(id).getOnePrice()).setCount(order.writeDish.get(id).getCount() + "").setMethod("");
        if (order.writeDish.get(id).getItemsBean().getRemarks() != null && order.writeDish.get(id).getItemsBean().getRemarks().size() != 0) {
            orderItemMesg.setMethod(order.writeDish.get(id).getItemsBean().getRemarks().get(0).getName() + order.writeDish.get(id).getItemsBean().getRemarks().get(0).getPrice());
        }
        if (order.writeDish.get(id).getGiveCount() != 0) {
            orderItemMesg.setMethod(orderItemMesg.getMethod().getText().toString() + "赠送：" + order.writeDish.get(id).getGiveCount());
        }
        if (order.writeDish.get(id).getBackCount() != 0) {
            orderItemMesg.setMethod(orderItemMesg.getMethod().getText().toString() + "退菜：" + order.writeDish.get(id).getBackCount());
        }
        if (orderInfo != null) {
            orderInfo.setMesg(order);
        }
    }

    private void setOrderInfolayout(String id) {
        if (id == null) {
            orderItemMesg.setMethod("").setCount("1").setName("").setPrice("");
        } else {
            if (orderItemMesg != null) {
                orderItemMesg.
                        setPrice(order.getOrder(id).getAllPrice() + "").
                        setName(order.getOrder(id).getDeshName() + order.getOrder(id).getOnePrice()).setMethod(order.getOrder(id).getMethodName() + order.getOrder(id).getMethodPrice()).setCount(order.getOrder(id).getCount() /*+ order.getOrder(id).getGiveCount() */ + "");
                if (order.getOrder(id).getGiveCount() != 0) {
                    orderItemMesg.setMethod(orderItemMesg.getMethod().getText().toString() + "赠送：" + order.getOrder(id).getGiveCount());
                }
                if (order.getOrder(id).getBackCount() != 0) {
                    orderItemMesg.setMethod(orderItemMesg.getMethod().getText().toString() + "退菜：" + order.getOrder(id).getBackCount());
                }
            }
        }
        if (orderInfo != null) {
            orderInfo.setMesg(order);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
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
        MesgShow.showMesg("", mesg, send, new MesgShow.OnButtonListener() {
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
        baseShowProg(back);
    }

    @Override
    public void disProg() {
        baseDisProg();
    }

    @Override
    public void toast(String s) {
        showToast(s);
    }

    OrderPresenter presenter;

    @Override
    public void setPresenter(OrderPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void setDcList(List<ResponseGetCaileiList.DataBean> data) {

    }

    @Override
    public void setDeshList(List<ResponseGetCaipinList.DataBean> deshList) {

    }

    private boolean isBackDesh;

    @Override
    public void sendSucc(int ord) {
        if (isBackDesh) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            String date = year + "-" + month + "-" + day + " " + hour + ":" + minute;
            printBackDesh(new RequestPrintBackDesh().setSname(AppMode.getInstance().getUsername()).setTitle("退菜单").setDate(date).setTabcode(tabname).setItems(backDesh));
            return;
        }
        order.isChange = true;
    }

    private void printBackDesh(RequestPrintBackDesh requestPrintBackDesh) {
        AppRequest.getAPI().printOrder(requestPrintBackDesh).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(this) {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        if (baseResponse.getCode() == 1) {
                            isBackDesh = false;
                            backDesh = null;
                        }
                    }
                });
    }

    @Override
    public void setOrder(ResponseGetOrder getOrder) {
        if (order == null) {
            order = new Orders();
        } else {
            if (order.writeDish != null) {
                order.writeDish.clear();
            }
            order.getOrders().clear();
        }
        this.responseOrd = getOrder;
        List<RequestOrder.WriteDishBean> writedishs = null;
        if (getOrder.getData().getWritedishs() != null) {
            writedishs = getOrder.getData().getWritedishs();
            for (int i = 0; i < writedishs.size(); i++) {
                order.addWriteDish(writedishs.get(i).getName(), new Orders.Write().
                        setOrders(order).
                        setItemsBean(writedishs.get(i)).
                        setSend(true).
                        setCount(Float.valueOf(writedishs.get(i).getCount())).
                        setGiveCount(Float.valueOf(writedishs.get(i).getGiveCount())).
                        setBackCount(writedishs.get(i).getBackCount()));
            }
        }
        List<ResponseGetCaipinList.DataBean> itemsBeen = getOrder.getData().getItems();

        order.setDisconut(Float.valueOf(getOrder.getData().getZk()));
        for (int i = 0; i < itemsBeen.size(); i++) {
            /*if (orders.getOrders().containsKey(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id())) {
                orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).setSend(true).
                        setCount(Float.valueOf(Float.valueOf(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getCount()) + Float.valueOf(itemsBeen.get(i).getCount()))).
                        setGiveCount(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getGiveCount() + itemsBeen.get(i).
                                getGiveCount());
            } else*/
            //{
            itemsBeen.get(i).setAddCount(0);
            order.setOrders(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id(),
                    new Orders.Order().setOrders(order).setItemsBean(itemsBeen.get(i)).setSend(true).setCount(Float.valueOf(itemsBeen.get(i).getCount())).
                            setGiveCount(itemsBeen.get(i).
                                    getGiveCount()).setBackCount(itemsBeen.get(i).getBackCount()));
            //}
        }
        /*if (itemsBeen.size() != 0) {
            isOrderWrite = false;
            orders.setCurrPosition(orders.getOrders().keyAt(0));
        } else if (writedishs != null && writedishs.size() != 0) {
            isOrderWrite = true;
            orders.setCurrPosition(orders.writeDish.keyAt(0));

        } else {
            orders.setCurrPosition(null);
        }
*/
        adapter.notifyDataSetChanged();
        // setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
        //isSend = true;
        order.isChange = false;
        order.isAdd = false;
    }

    @Override
    public void showMesgView(String mesg, MesgShow.OnButtonListener sure) {
        showMesg(mesg, sure);
    }

    @Override
    public void printNoDeskOrderSucc(int orderId) {

    }

    private void showMesg(String mesg, MesgShow.OnButtonListener sure) {
        MesgShow.showMesg("", mesg, back, sure, null, true);
    }

    @Override
    public void backView() {
        popBackFragment();
        popBackFragment();
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

    public interface OnItemClick {
        void onNumbClick(Orders order, String id, int position, View Item);

        void onRequest(Orders orders, int position, String id, View view);
    }

    public class OrderListAdapter extends BaseAdapter {

        int selectItem = 0;

        public OnItemClick getOnItemClick() {
            return onItemClick;
        }

        public void setOnItemClick(OnItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }

        private OnItemClick onItemClick;
        private Orders order;

        public List<Orders.Order> getOrderList() {
            return orderList;
        }

        public void setOrderList(Orders order) {
            this.order = order;
            this.orderList = order.getAll();
            notifyDataSetChanged();
        }

        List<Orders.Order> orderList;

        @Override
        public int getCount() {
            if (order != null && order.writeDish != null) {
                return order.getOrders().size() + order.writeDish.size();
            } else if (order != null) {
                return order.getOrders().size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (orderList != null) {
                return orderList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_desh_info, parent, false);
            convertView.setBackgroundColor(Color.BLACK);

            ViewHolder viewHolder = new ViewHolder(convertView);
            final boolean isOrderWrite;
            int i;
            String id;
            if (order.writeDish == null || position > order.writeDish.size() - 1) {
                isOrderWrite = false;
                if (order.writeDish == null) {
                    i = 0;
                } else {
                    i = order.writeDish.size();
                }
                id = order.getOrders().keyAt(position - i);
                order.setCurrPosition(order.getOrders().keyAt(position - i));
            } else {
                id = order.writeDish.keyAt(position);
                order.setCurrPosition(order.writeDish.keyAt(position));
                isOrderWrite = true;
            }
            if (isOrderWrite) {
                viewHolder.numberEdit.setText(order.writeDish.get(order.getCurrPosition()).getCount() /*+ order.writeDish.get(order.getCurrPosition()).getGiveCount()*/ + "");
                final View finalConvertView = convertView;
                viewHolder.allLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null /*&& !order.writeDish.get(order.getCurrPosition()).isSend*/) {
                            onItemClick.onNumbClick(order, order.getCurrPosition(), position, finalConvertView);
                        }
                    }
                });
                viewHolder.deshName.setText(order.writeDish.get(order.getCurrPosition()).getDeshName() + "  " + order.writeDish.get(order.getCurrPosition()).getDeshPrice());
                viewHolder.deshMethod.setText(order.writeDish.get(order.getCurrPosition()).getMethodName() + order.writeDish.get(order.getCurrPosition()).getMethodPrice());
                viewHolder.sendstatue.setVisibility(View.VISIBLE);
                if (order.writeDish.get(order.getCurrPosition()).isSend) {
                    viewHolder.sendstatue.setText("已送单");
                    viewHolder.sendstatue.setTextColor(Color.rgb(0, 110, 255));
                } else {
                    viewHolder.sendstatue.setText("未送单");
                    viewHolder.sendstatue.setTextColor(Color.RED);
                }
            } else {
                viewHolder.numberEdit.setText(order.getOrders().get(order.getCurrPosition()).getCount() /*+ order.getOrders().get(order.getCurrPosition()).getGiveCount() */ + "");
                final View finalConvertView = convertView;
                viewHolder.allLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null /*&& !order.getOrders().get(order.getCurrPosition()).isSend*/) {
                            onItemClick.onNumbClick(order, order.getCurrPosition(), position, finalConvertView);
                        }
                    }
                });
                viewHolder.deshName.setText(order.getOrders().get(order.getCurrPosition()).getDeshName() + "  " + order.getOrders().get(order.getCurrPosition()).getDeshPrice());
                viewHolder.deshMethod.setText(order.getOrders().get(order.getCurrPosition()).getMethodName() + order.getOrders().get(order.getCurrPosition()).getMethodPrice());
                viewHolder.sendstatue.setVisibility(View.VISIBLE);
                if (order.getOrders().get(order.getCurrPosition()).isSend) {
                    viewHolder.sendstatue.setText("已送单");
                    viewHolder.sendstatue.setTextColor(Color.rgb(0, 110, 255));
                } else {
                    viewHolder.sendstatue.setText("未送单");
                    viewHolder.sendstatue.setTextColor(Color.RED);
                }
            }
            viewHolder.yaoqiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {

                        onItemClick.onRequest(order, position, order.getCurrPosition(), v);
                    }
                }
            });
            orderItemMesg = new OrderItemMesg();
            orderItemMesg.init(convertView);
            orderItemMesg.setList(true);
            setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
            if (order.getOrder(order.getCurrPosition()) != null ? order.getOrder(order.getCurrPosition()).getItemsBean().getCount().equals("0.0") : order.writeDish.get(order.getCurrPosition()).getCount() == 0) {

            } else {
                if (position == selectItem) {
                    viewHolder.numberEdit.setBackgroundResource(R.drawable.shape_button_orderinfo_edit);
                    viewHolder.yaoqiu.setBackgroundResource(R.drawable.shape_button_orderinfo_edit);
                    viewHolder.numberEdit.setTextColor(Color.WHITE);
                    viewHolder.yaoqiu.setTextColor(Color.WHITE);
                } else {
                    viewHolder.numberEdit.setBackgroundResource(R.drawable.shape_dialog_fillet_solid);
                    viewHolder.numberEdit.setTextColor(Color.rgb(0x06, 0x3c, 0x9f));
                    viewHolder.yaoqiu.setBackgroundResource(R.drawable.shape_dialog_fillet_solid);
                    viewHolder.yaoqiu.setTextColor(Color.rgb(0x06, 0x3c, 0x9f));
                }
            }

            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.number_edit)
            public TextView numberEdit;
            @BindView(R.id.desh_name)
            public TextView deshName;
            @BindView(R.id.desh_method)
            public TextView deshMethod;
            @BindView(R.id.layout_center)
            public LinearLayout layoutCenter;
            @BindView(R.id.all_price)
            public TextView allPrice;
            @BindView(R.id.sendstatue)
            public TextView sendstatue;
            @BindView(R.id.yaoqiu)
            public TextView yaoqiu;
            @BindView(R.id.item_mesg)
            public LinearLayout itemMesg;
            @BindView(R.id.alllayout)
            public AllLayout allLayout;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class PopviewOther {
        PopUpViewUtil popUpViewUtil;
        View view;
        @BindView(R.id.write_desh)
        TextView writeDesh;
        @BindView(R.id.remove_desk)
        TextView removeDesk;
        @BindView(R.id.trun_desk)
        TextView trunDesk;
        @BindView(R.id.print_order)
        TextView printOrder;
        @BindView(R.id.cancel)
        TextView cancel;

        Unbinder unbinder;

        private void init() {
            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_oderdesh_other, null, false);
            unbinder = ButterKnife.bind(this, view);
        }

        private void dismiss() {
            popUpViewUtil.dismiss();
        }
    }
}
