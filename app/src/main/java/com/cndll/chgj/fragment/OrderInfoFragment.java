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
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
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
import com.cndll.chgj.weight.KeyWeight;
import com.cndll.chgj.weight.MesgShow;
import com.cndll.chgj.weight.OrderInfo;
import com.cndll.chgj.weight.OrderItemMesg;
import com.cndll.chgj.weight.PopOrderRequest;

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
 * Use the {@link OrderInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInfoFragment extends BaseFragment implements OrderView {
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

        popUpkey(KeyWeight.Mode_OnlyNumb, "取消", "确认", new KeyWeight.OnKeyClick() {
            @Override
            public void onKeyCancel(String s) {

            }

            @Override
            public void onKeySure(String s) {
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
                        setYsmoney(orderInfo.getLastPrice() + "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(OrderInfoFragment.this) {
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
                            if (((ResponseAddOrd) baseResponse).getData().getOid() != 0) {
                                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(((ResponseAddOrd) baseResponse).getData().getOid()).setOrders(order), null);
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
                final PopUpViewUtil popviewDesk = PopUpViewUtil.getInstance();
                View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_turndesk, null, false);
                RecyclerView deskList = (RecyclerView) view.findViewById(R.id.desk_list);
                final OrderDeskListAdapter orderDeskListAdapter = new OrderDeskListAdapter();
                deskList.setAdapter(orderDeskListAdapter);
                LinearPagerLayoutManager layoutManager = new LinearPagerLayoutManager(getContext(), 3, 4);
                deskList.setLayoutManager(layoutManager);

                orderDeskListAdapter.setOnItemClickLister(new OnItemClickLister() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        presenter.turnOrder(orderId + "", orderDeskListAdapter.getItems().get(position).getName(), orderDeskListAdapter.getItems().get(position).getId());
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
                        subscribe(new MObeserver(OrderInfoFragment.this) {
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
        popviewOther.removeDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderId != 0) {
                    presenter.removeOrder(orderId + "", 2 + "");
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
                            order = new OrderDishFragment.Orders();
                        }
                        order.addWriteDish(name.getText().toString(), new OrderDishFragment.Orders.Write().setItemsBean(writeDishBean.
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
        if (!OrderDishFragment.Orders.isChange) {
            if (orderId != 0) {
                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(orderId).setOrders(order), null);
            }
        } else {
            MesgShow.showMesg("", "有菜品未送单,请先送单", dazhe, null, null, false);
        }
    }

    @BindView(R.id.dazhe)
    Button dazhe;

    @OnClick(R.id.dazhe)
    void onclick_discount() {
        if (!OrderDishFragment.Orders.isChange && orderId != 0) {
            popUpkey(2, "取消打折", "确定", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {
                    order.setDisconut(1);
                    orderInfo.setMesg(order);
                }

                @Override
                public void onKeySure(String s) {

                    if (StringHelp.isFloat(s)) {
                        if (Float.valueOf(s) <= 0.99 && Float.valueOf(s) >= 0.1) {
                            order.setDisconut(Float.valueOf(s));
                            orderInfo.setMesg(order);
                        }
                    }


                }

                @Override
                public void onKeyNub(String s) {

                }
            });
        } else {
            MesgShow.showMesg("", "有菜品未送单,请先送单", dazhe, null, null, false);
        }
    }

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderDishFragment.Orders getOrderList() {
        return order;
    }

    private void popUpkey(int mode, String hint, String sureHint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setCancelText(hint);
        keyWeight.setSureText(sureHint);
        keyWeight.init(getContext(), orderListView, mode);
        keyWeight.setOnKeyClick(onKeyClick);
    }

    public OrderInfoFragment setOrderList(OrderDishFragment.Orders order) {
        this.order = order;
        if (adapter != null) {
            adapter.setOrderList(order);
        }
        return this;
    }

    private OrderDishFragment.Orders order;

    public OrderInfoFragment() {
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
    public static OrderInfoFragment newInstance(String param1, String param2) {
        OrderInfoFragment fragment = new OrderInfoFragment();
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

    public int getOrderId() {
        return orderId;
    }

    public OrderInfoFragment setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    int orderId;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (!AppMode.getInstance().isDeskMode()) {
            txtDiscount.setVisibility(View.INVISIBLE);
            zhekou.setVisibility(View.INVISIBLE);
            layoutModeHaveTesk.setVisibility(View.GONE);
            payNodesk.setVisibility(View.VISIBLE);
        } else {
            layoutModeHaveTesk.setVisibility(View.VISIBLE);
            payNodesk.setVisibility(View.GONE);
        }
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
        orderInfo = new OrderInfo();
        orderInfo.init(orderinfolayout);
        orderInfo.setMesg(order);
        adapter.setOnItemClick(new OrderListAdapter.OnItemClick() {
            @Override
            public void onNumbClick(final OrderDishFragment.Orders order, final List<OrderDishFragment.Orders.Order> orders, final int position, View Item) {
                final boolean isOrderWrite;
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
                popUpkey(0, "取消", "确定", new KeyWeight.OnKeyClick() {
                    @Override
                    public void onKeyCancel(String s) {

                    }

                    @Override
                    public void onKeySure(String s) {
                        orderItemMesg = new OrderItemMesg();
                        orderItemMesg.init(container);
                        if (!isOrderWrite) {
                            order.getOrder(order.getCurrPosition()).setCount(Float.valueOf(s));
                        } else {
                            order.writeDish.get(order.getCurrPosition()).setCount(Float.valueOf(s));
                        }
                        setOrderInfolayout(order.getCurrPosition(), isOrderWrite);

                    }

                    @Override
                    public void onKeyNub(String s) {

                    }
                });
            }

            @Override
            public void onRequest(final int position, View view) {
                boolean iss;
                final boolean isOrderWrite;
                orderItemMesg = new OrderItemMesg();
                orderItemMesg.init(container);
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
                    iss = order.writeDish.get(order.getCurrPosition()).isSend();
                } else {
                    iss = order.getOrder(order.getCurrPosition()).isSend;
                }
                if (iss) {
                    final PopOrderRequest popOrderRequest = new PopOrderRequest();
                    popOrderRequest.init(getContext(), view);
                    popOrderRequest.setFirstText("赠送");
                    popOrderRequest.setSecondText("退菜");
                    if (isOrderWrite) {
                        if (order.writeDish.get(order.getCurrPosition()).getGiveCount() == 0) {
                            popOrderRequest.setThirdVisble(View.GONE);
                        } else {
                            popOrderRequest.setThirdText("取消赠送");
                        }
                    } else {
                        if (order.getOrder(order.getCurrPosition()).getGiveCount() == 0) {
                            popOrderRequest.setThirdVisble(View.GONE);
                        } else {
                            popOrderRequest.setThirdText("取消赠送");
                        }
                    }
                    popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                        @Override
                        public void onFirst(View view) {
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
                            if (!isOrderWrite) {
                                order.getOrder(order.getCurrPosition()).addGiveCount();
                            } else {
                                String id = order.getCurrPosition();
                                OrderDishFragment.Orders.Write w = order.writeDish.get(id);
                                order.writeDish.get(order.getCurrPosition()).addGiveCount();
                            }
                            if (!isOrderWrite) {
                                if (order.getOrder(order.getCurrPosition()).getGiveCount() == 0) {
                                    popOrderRequest.setThirdVisble(View.GONE);
                                } else {
                                    popOrderRequest.setThirdVisble(View.VISIBLE);
                                    popOrderRequest.setThirdText("取消赠送");
                                }
                            } else {
                                if (order.writeDish.get(order.getCurrPosition()).getGiveCount() == 0) {
                                    popOrderRequest.setThirdVisble(View.GONE);
                                } else {
                                    popOrderRequest.setThirdVisble(View.VISIBLE);
                                    popOrderRequest.setThirdText("取消赠送");
                                }
                            }
                            setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
                        }

                        @Override
                        public void onSecond(View view) {
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
                            if (!isOrderWrite)
                                order.getOrder(order.getCurrPosition()).backDesh();
                            else
                                order.writeDish.get(order.getCurrPosition()).backDesh();
                            /*if (order.getOrders().size() == 0) {
                                order.setCurrPosition(null);
                            } else {
                                order.setCurrPosition(order.getOrders().keyAt(0));
                            }*/
                            setOrderInfolayout(order.getCurrPosition(), isOrderWrite);
                        }

                        @Override
                        public void onThird(View view) {
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
                            if (!isOrderWrite) {
                                order.getOrder(order.getCurrPosition()).cancelGive();
                            } else {
                                order.writeDish.get(order.getCurrPosition()).cancelGive();
                            }
                            setOrderInfolayout(order.getCurrPosition(), isOrderWrite);

                        }
                    });
                    popOrderRequest.show();
                } else {
                    final PopOrderRequest popOrderRequest = new PopOrderRequest();
                    popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                        @Override
                        public void onFirst(View v) {
                            if (isOrderWrite) {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setWrite(order.writeDish.get(order.getCurrPosition())), new NoteImpl());
                            } else {
                                replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setOrder(order.getOrder(order.getCurrPosition())), new NoteImpl());
                            }
                            popOrderRequest.dismiss();
                        }

                        @Override
                        public void onSecond(View view) {
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
                            adapter.notifyDataSetChanged();
                        }
                    });
                    popOrderRequest.init(getContext(), view);
                    popOrderRequest.show();
                }
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

    private void setOrderInfolayoutWrite(String id) {
        if (id == null) {
            orderItemMesg.setMethod("").setCount("1").setName("").setPrice("");
        }
        if (orderItemMesg != null)
            Log.d("at", "setOrderInfolayoutWrite: " + order.writeDish.get(id));
        OrderDishFragment.Orders.Write write = order.writeDish.get(id);
        orderItemMesg.setPrice(order.writeDish.get(id).getAllPrice() + "").
                setName(order.writeDish.get(id).getDeshName() + order.writeDish.get(id).getDeshPrice()).setCount(order.writeDish.get(id).getCount() + "");
        if (order.writeDish.get(id).getItemsBean().getRemarks() != null) {
            orderItemMesg.setMethod(order.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getName() + order.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getPrice());
        } else {
            orderItemMesg.setMethod(" ");
        }
        if (orderInfo != null) {
            orderInfo.setMesg(order);
        }
    }

    private void setOrderInfolayout(String id) {
        if (id == null) {
            orderItemMesg.setMethod("").setCount("1").setName("").setPrice("");
        } else {
            if (orderItemMesg != null)
                orderItemMesg.
                        setPrice(order.getOrder(id).getAllPrice() + "").
                        setName(order.getOrder(id).getDeshName() + order.getOrder(id).getDeshPrice()).setMethod(order.getOrder(id).getMethodName() + order.getOrder(id).getMethodPrice()).setCount(order.getOrder(id).getCount() + order.getOrder(id).getGiveCount() + "");

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

    @Override
    public void sendSucc() {

    }

    @Override
    public void setOrder(ResponseGetOrder getOrder) {

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

    public static class OrderListAdapter extends BaseAdapter {
        public interface OnItemClick {
            void onNumbClick(OrderDishFragment.Orders order, List<OrderDishFragment.Orders.Order> orders, int position, View Item);

            void onRequest(int position, View view);
        }

        public OnItemClick getOnItemClick() {
            return onItemClick;
        }

        public void setOnItemClick(OnItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }

        private OnItemClick onItemClick;
        private OrderDishFragment.Orders order;

        public List<OrderDishFragment.Orders.Order> getOrderList() {
            return orderList;
        }

        public void setOrderList(OrderDishFragment.Orders order) {
            this.order = order;
            this.orderList = order.getAll();
            notifyDataSetChanged();
        }

        List<OrderDishFragment.Orders.Order> orderList;

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
            ViewHolder viewHolder = new ViewHolder(convertView);
            final boolean isOrderWrite;
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
                viewHolder.numberEdit.setText(order.writeDish.get(order.getCurrPosition()).getCount() + order.writeDish.get(order.getCurrPosition()).getGiveCount() + "");
                final View finalConvertView = convertView;
                viewHolder.numberEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null) {
                            onItemClick.onNumbClick(order, orderList, position, finalConvertView);
                        }
                    }
                });
                viewHolder.deshName.setText(order.writeDish.get(order.getCurrPosition()).getDeshName() + order.writeDish.get(order.getCurrPosition()).getDeshPrice());
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
                viewHolder.numberEdit.setText(order.getOrders().get(order.getCurrPosition()).getCount() + order.getOrders().get(order.getCurrPosition()).getGiveCount() + "");
                final View finalConvertView = convertView;
                viewHolder.numberEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null) {
                            onItemClick.onNumbClick(order, orderList, position, finalConvertView);
                        }
                    }
                });
                viewHolder.deshName.setText(order.getOrders().get(order.getCurrPosition()).getDeshName() + order.getOrders().get(order.getCurrPosition()).getDeshPrice());
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
                        onItemClick.onRequest(position, v);
                    }
                }
            });
            return convertView;
        }

        public static class ViewHolder {
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
    }
}
