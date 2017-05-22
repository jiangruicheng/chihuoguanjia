package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.cndll.chgj.adapter.OrderDeskListAdapter;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintBackDesh;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
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
import com.cndll.chgj.util.HorizontalPageLayoutManager;
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
    @BindView(R.id.sendstatue)
    TextView sendstatue;
    @BindView(R.id.mode_have_tesk)
    LinearLayout modeHaveTesk;
    @BindView(R.id.query_nodesk)
    Button queryNodesk;

    @OnClick(R.id.query_nodesk)
    void onclick_querynodesk() {
        queryDesh();
    }

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
                        setItems(orders.getItems()).
                        setMid(AppMode.getInstance().getMid()).
                        setUid(AppMode.getInstance().getUid()).
                        setPernum("1").
                        setSmoney(orderInfolayout.getGivePrice() + "").
                        setSsmoney(orderInfolayout.getLastPrice() + "").
                        setZk(orders.getDisconut() + "").
                        setZkmoney(orderInfolayout.getDiscountPrice() + "").
                        setTmoney(orderInfolayout.getAllPrice() + "").
                        setTabname(s).
                        setTab_id(0 + "").setPayee("1234").
                        setYsmoney(orderInfolayout.getLastPrice() + "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(OrderDishFragment.this) {
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
                                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(((ResponseAddOrd) baseResponse).getData().getOid()).setOrders(orders), null);

                            }
                            ;
                        }
                    }
                });

            }

            @Override
            public void onKeyNub(String s) {

            }
        });
      /*  } else {
            MesgShow.showMesg("", "有菜品未送单,请先送单", dazhe, null, null, false);
        }*/
    }

    @BindView(R.id.info_nodesk)
    Button infoNodesk;

    @OnClick(R.id.info_nodesk)
    void onclick_infonodesk() {
        if (orders != null && orders.getAll() != null) {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(titleLeft.getText().toString(), titleRight.getText().toString()).setOrderList(orders).setOrderId(orderId), new OrderImpl());
        } else {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(null, null), new OrderImpl());
        }
    }

    @BindView(R.id.mode_no_desk)
    LinearLayout modeNoDesk;

    @OnClick(R.id.yaoqiu)
    void onclick_yaoqiu() {
        if (orders != null) {
            boolean iss;

            if (isOrderWrite) {
                iss = orders.writeDish.get(orders.getCurrPosition()).isSend();
            } else {
                iss = orders.getOrder(orders.getCurrPosition()).isSend;
            }
            if (iss) {
                final PopOrderRequest popOrderRequest = new PopOrderRequest();
                popOrderRequest.init(getContext(), yaoqiu);
                popOrderRequest.show();
                popOrderRequest.setFirstText("赠送");
                popOrderRequest.setSecondText("退菜");
                if (orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0) {
                    popOrderRequest.setViewHeight(2);
                    popOrderRequest.setThirdVisble(View.GONE);
                } else {
                    popOrderRequest.setViewHeight(3);
                    popOrderRequest.setThirdText("取消赠送");
                }
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View view) {
                        if (!isOrderWrite) {
                            orders.getOrder(orders.getCurrPosition()).addGiveCount();
                        } else {
                            orders.writeDish.get(orders.getCurrPosition()).addGiveCount();
                        }
                        if (orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0) {
                            popOrderRequest.setThirdVisble(View.GONE);
                            popOrderRequest.setViewHeight(2);
                        } else {
                            popOrderRequest.setThirdVisble(View.VISIBLE);
                            popOrderRequest.setViewHeight(3);
                            popOrderRequest.setThirdText("取消赠送");
                        }
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        sendOrds();
                    }

                    @Override
                    public void onSecond(View view) {
                        backDesh = new ArrayList<RequestPrintBackDesh.ItemsBean>();
                        if (!isOrderWrite) {
                            backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.getOrder(orders.getCurrPosition()).getItemsBean().getName()).
                                    setMoney(orders.getOrder(orders.getCurrPosition()).getItemsBean().getPrice()).
                                    setNum(orders.getOrder(orders.getCurrPosition()).getCount() + "").
                                    setUnit(orders.getOrder(orders.getCurrPosition()).getItemsBean().getUnit()).
                                    setM_name(""));
                            orders.getOrder(orders.getCurrPosition()).backDesh();

                        } else {
                            backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getName()).
                                    setMoney(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getPrice()).
                                    setNum(orders.writeDish.get(orders.getCurrPosition()).getCount() + "").
                                    setUnit("盘").
                                    setM_name(""));
                            orders.writeDish.get(orders.getCurrPosition()).backDesh();
                        }
                        if (orders.getOrders().size() == 0) {
                            orders.setCurrPosition(null);
                        } else {
                            orders.setCurrPosition(orders.getOrders().keyAt(0));
                        }
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        sendOrds();
                        isBackDesh = true;
                    }

                    @Override
                    public void onThird(View view) {
                        if (!isOrderWrite) {
                            orders.getOrder(orders.getCurrPosition()).cancelGive();
                        } else {
                            orders.writeDish.get(orders.getCurrPosition()).cancelGive();
                        }
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        sendOrds();
                    }
                });

            } else {
                final PopOrderRequest popOrderRequest = new PopOrderRequest();
                popOrderRequest.init(getContext(), yaoqiu);
                popOrderRequest.show();
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View v) {
                        if (isOrderWrite) {
                            replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setWrite(orders.writeDish.get(orders.getCurrPosition())), new NoteImpl());
                        } else {
                            replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setOrder(orders.getOrder(orders.getCurrPosition())), new NoteImpl());
                        }
                        popOrderRequest.dismiss();
                    }

                    @Override
                    public void onSecond(View view) {
                        if (orders != null) {
                            if (!isOrderWrite) {
                                orders.getOrder(orders.getCurrPosition()).addGiveCount();
                            } else {
                                orders.writeDish.get(orders.getCurrPosition()).addGiveCount();
                            }
                            setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                            if (!isOrderWrite) {
                                if (orders.getOrder(orders.getCurrPosition()).getCount() == 0) {
                                    view.setBackgroundColor(Color.GRAY);
                                }
                            } else {
                                if (orders.writeDish.get(orders.getCurrPosition()).getCount() == 0) {
                                    view.setBackgroundColor(Color.GRAY);
                                }
                            }
                        }
                    }

                    @Override
                    public void onThird(View v) {
                        if (isOrderWrite) {
                            orders.removeWriteDish(orders.getCurrPosition());
                        } else {
                            orders.removeOrders(orders.getCurrPosition());
                        }
                        if (orders.getOrders().size() != 0) {
                            orders.setCurrPosition(orders.getOrders().keyAt(0));
                            setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        } else {
                            orderItemMesglayout.setMethod("").setCount("1").setName("").setPrice("");
                            orderInfolayout.setAllMoney("0").setCount("0").setDiscount("0").setGive("0").setLastMoney("0");
                            orders = null;
                            popOrderRequest.dismiss();
                        }
                    }
                });

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
    DeshListAdapter queryListAdapter;

    @OnClick(R.id.query)
    void onclick_query() {
        queryDesh();
    }

    private void queryDesh() {
        final PopUpViewUtil popUpQuery = PopUpViewUtil.getInstance();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_orderdesh_query, null, false);
        View key = view.findViewById(R.id.key);
        popUpQuery.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                queryListAdapter = null;
            }
        });
        RecyclerView list = (RecyclerView) view.findViewById(R.id.desh_list);
        if (queryListAdapter == null) {
            queryListAdapter = new DeshListAdapter();
        }
        queryListAdapter.setOnItemClickLister(new OnItemClickLister() {
            @Override
            public void OnItemClick(View view, int position) {
                if (orders == null) {
                    orders = new Orders();
                }
                if (orders.Iscontan(queryListAdapter.getMitems().get(position).getId())) {

                } else {
                    Orders.Order order = new Orders.Order();
                    order.setItemsBean(queryListAdapter.getMitems().get(position).
                            setCount(1 + ""));
                    orders.setOrders(queryListAdapter.getMitems().get(position).getId(), order);

                }
                orders.setCurrPosition(queryListAdapter.getMitems().get(position).getId());
                numberEdit.setText(orders.getOrder(orders.getCurrPosition()).getCount() + "");
                isOrderWrite = false;

                setOrderInfolayout(queryListAdapter.getMitems().get(position).getId(), isOrderWrite);
                isSend = false;
            }
        });
        list.setAdapter(queryListAdapter);
        HorizontalPageLayoutManager manager = new HorizontalPageLayoutManager(1, 3);
        list.setLayoutManager(manager);
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setKey(key);
        keyWeight.setChilder(true);
        keyWeight.setShowHintText("输入菜品查询码，首字母查询点餐");
        keyWeight.setOnKeyClick(new KeyWeight.OnKeyClick() {
            @Override
            public void onKeyCancel(String s) {
                popUpQuery.dismiss();
            }

            @Override
            public void onKeySure(String s) {

            }

            @Override
            public void onKeyNub(String s) {
                presenter.getDeshList(new RequestGetCaipinList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(s));
            }
        });
        keyWeight.init(getContext(), view, KeyWeight.Mode_NoButton);
        int[] locations = new int[2];
        deshList.getLocationOnScreen(locations);
        popUpQuery.popListWindow(send, view,
                popUpQuery.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                deshList.getHeight(),
                Gravity.NO_GRAVITY, locations);
    }

    @BindView(R.id.send)
    Button send;


    @OnClick(R.id.send)
    void onclick_send() {
        if (orders == null) {
            return;
        }
        if (orders.getOrders().size() != 0 || orders.writeDish.size() != 0) {
            replaceFragmentAddToBackStack(SendFragment.newInstance(null, null).setOrderDishFragment(this), new OrderImpl());
        }
    }

    @BindView(R.id.info)
    Button info;

    @OnClick(R.id.info)
    void onclick_info() {
        if (orders != null && orders.getAll() != null) {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(titleLeft.getText().toString(), titleRight.getText().toString()).setOrderList(orders).setOrderId(orderId).setResponseOrd(responseOrd).setTabID(tableId).setTabname(tabname), new OrderImpl());
        } else {
            replaceFragmentAddToBackStack(OrderInfoFragment.newInstance(null, null), new OrderImpl());
        }
    }

    @BindView(R.id.other)
    Button other;

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
                deshList.getLocationOnScreen(locations);
                popviewDesk.popListWindow(send, view,
                        popviewDesk.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                        deshList.getHeight(),
                        Gravity.NO_GRAVITY, locations);
                AppRequest.getAPI().getDeskList(new RequestGetDeskList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setIsoc(2 + "")).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribe(new MObeserver(OrderDishFragment.this) {
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
                if (orders.isChange) {
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
                if (orderId != 0) {
                    presenter.removeOrder(orderId + "", 2 + "");
                    popviewOther.dismiss();
                }
            }
        });
        popviewOther.writeDesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popviewOther.dismiss();
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
                        if (orders == null) {
                            orders = new Orders();
                        }
                        if (name.getText().toString() == null || name.getText().toString().equals("")) {
                            showMesg("请输入菜品名字");
                            return;
                        }
                        if (number.getText().toString() == null || name.getText().toString().equals("")) {
                            showMesg("请输入菜品数量");
                            return;
                        }
                        if (price.getText().toString() == null || name.getText().toString().equals("")) {
                            showMesg("请输入菜品价格");
                            return;
                        }

                        orders.addWriteDish(name.getText().toString(), new Orders.Write().setOrders(orders).setItemsBean(writeDishBean.
                                setIsWrite("1").
                                setCount(number.getText().toString()).
                                setGiveCount("0").
                                setPrice(price.getText().toString()).
                                setName(name.getText().toString())).
                                setCount(Float.valueOf(number.getText().toString())).setGiveCount(0));
                        isOrderWrite = true;
                        orders.setCurrPosition(name.getText().toString());
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        numberEdit.setText(orders.writeDish.get(orders.getCurrPosition()).getCount() + "");
                        popUpViewUtil.dismiss();

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

    private void printOrders() {
        AppRequest.getAPI().printBill(orderId + "", AppMode.getInstance().getPrint_code(), AppMode.getInstance().getUsername()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(OrderDishFragment.this) {
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
            }
        });
    }

    private boolean isBackDesh;

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

    @BindView(R.id.dazhe)
    Button dazhe;

    @OnClick(R.id.dazhe)
    void onclick_discount() {
        if (!orders.isChange && orderId != 0) {
            popUpkey(2, "取消打折", "确定", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {
                    orders.setDisconut(1);
                    setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                    sendOrds();
                }

                @Override
                public void onKeySure(String s) {

                    if (StringHelp.isFloat(s)) {
                        if (Float.valueOf(s) <= 0.99 && Float.valueOf(s) >= 0.1) {
                            orders.setDisconut(Float.valueOf(s));
                            setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                            sendOrds();
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

    @BindView(R.id.pay)
    Button pay;

    @OnClick(R.id.pay)
    void onclick_pay() {
        // replaceFragmentAddToBackStack(ApplyPayFragment.newInstance(null, null), null);
        if (!orders.isChange) {
            if (orderId != 0) {
                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(orderId).setOrders(orders), null);

            }
        } else {
            MesgShow.showMesg("", "有菜品未送单,请先送单", dazhe, null, null, false);
        }
    }

    Unbinder unbinder;

    @OnClick(R.id.number_edit)
    void onclick_numberEdit() {
        boolean isClick;
        if (orders == null) {
            return;
        }
        if (isOrderWrite) {
            isClick = orders.writeDish.get(orders.getCurrPosition()).isSend;
        } else {
            isClick = orders.getOrder(orders.getCurrPosition()).isSend;
        }
        if (orders != null && !isClick) {
            popUpkey(KeyWeight.Mode_OnlyNumb, "删除", "确定", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {
                    if (isOrderWrite) {
                        orders.removeWriteDish(orders.getCurrPosition());
                    } else {
                        orders.removeOrders(orders.getCurrPosition());
                    }
                    if (orders.getOrders().size() != 0) {
                        orders.setCurrPosition(orders.getOrders().keyAt(0));
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                    } else {
                        orderItemMesglayout.setMethod("").setCount("1").setName("").setPrice("");
                        orderInfolayout.setAllMoney("0").setCount("0").setDiscount("0").setGive("0").setLastMoney("0");
                        orders = null;
                    }
                }

                @Override
                public void onKeySure(String s) {

                    if (StringHelp.isFloat(s)) {
                        if (!isOrderWrite) {
                            orders.getOrder(orders.getCurrPosition()).setCount(Float.valueOf(s));
                        } else {
                            orders.writeDish.get(orders.getCurrPosition()).setCount(Float.valueOf(s));
                        }
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                    }


                }

                @Override
                public void onKeyNub(String s) {

                }
            });
        }
    }


    private void popUpkey(int mode, String cancelhint, String surehint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setCancelText(cancelhint);
        keyWeight.setSureText(surehint);
        keyWeight.init(getContext(), numberEdit, mode);
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

    String tableId;

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

    public int orderId;
    String tabname;
    // private List<RequestOrder.ItemsBean> order = new ArrayList<>();
    protected OrderInfo orderInfolayout;
    private OrderItemMesg orderItemMesglayout;

    private void back() {
        if (orders == null) {
            popBackFragment();
            return;
        }
        if (orders.isChange && AppMode.getInstance().isDeskMode()) {
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

    public MainActivity.BackPressEvent backPressEvent = new MainActivity.BackPressEvent() {
        @Override
        public void onBackPress() {
            back();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_desh, container, false);
        unbinder = ButterKnife.bind(this, view);
        initlistview();
        if (tableId == null) {
            title.setText("快餐点餐");
            modeHaveTesk.setVisibility(View.GONE);
            modeNoDesk.setVisibility(View.VISIBLE);
        } else {
            modeHaveTesk.setVisibility(View.VISIBLE);
            modeNoDesk.setVisibility(View.GONE);
            title.setText("");
            rightText.setText("修改");
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orders == null) {
                        showMesg("还未点菜，不能修改");
                        return;
                    }
                    replaceFragmentAddToBackStack(SendFragment.newInstance(null, null).setOrderDishFragment(OrderDishFragment.this), new OrderImpl());
                }
            });
            if (responseOrd != null) {
                titleRight.setText("人数：" + responseOrd.getData().getPernum());
            } else {
                titleRight.setText("人数：2");
            }
            titleLeft.setText(" 桌台: " + tabname);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        orderInfolayout = new OrderInfo();
        orderItemMesglayout = new OrderItemMesg();
        orderItemMesglayout.init(itemMesg);
        orderInfolayout.init(orderInfo);

        return view;
    }

    @Override
    public void reload() {
        super.reload();
        if (orders == null) {
            return;
        }
        if (orders.getOrders().size() > 0) {
            isOrderWrite = false;
            orders.setCurrPosition(orders.getOrders().keyAt(0));
        } else {
            if (orders.writeDish == null || orders.writeDish.size() != 0) {
                isOrderWrite = true;
                orders.setCurrPosition(orders.writeDish.keyAt(0));
            }
        }
        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
    }

    private DeshListAdapter deshListAdapter;
    private DcListAdapter dcListAdapter;
    Orders orders;
    private boolean isOrderWrite = false;

    @Override
    public void onResume() {
        super.onResume();
        if (orders != null) {
            setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
        }
        MainActivity.setBackPressEvent(backPressEvent, this);
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
                    Orders.Order order = new Orders.Order().setOrders(orders);
                    order.setItemsBean(deshListAdapter.getMitems().get(position).
                            setCount(1 + ""));
                    orders.setOrders(deshListAdapter.getMitems().get(position).getId(), order);

                }
                orders.setCurrPosition(deshListAdapter.getMitems().get(position).getId());
                numberEdit.setText(orders.getOrder(orders.getCurrPosition()).getCount() + "");
                isOrderWrite = false;

                setOrderInfolayout(deshListAdapter.getMitems().get(position).getId(), isOrderWrite);
                isSend = false;
            }
        });
       /* if (presenter == null) {
            presenter = new OrderImpl();
            presenter.setView(this);
        }*/
        if (orderId != 0) {
            presenter.getOrder(new RequestGetOrder().setId(orderId));
        }
        presenter.getDcList(new RequestPrintList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
    }

    private void setOrderInfolayout(String id, boolean iswrite) {
        if (iswrite) {
            setOrderInfolayoutWrite(id);
        } else {
            setOrderInfolayout(id);
        }
    }

    private void setOrderInfolayoutWrite(String id) {
        if (orders.writeDish.get(id) == null || id == null) {
            orderItemMesglayout.setMethod("").setCount("1").setName("").setPrice("");

        } else {
            if (orderItemMesglayout != null)
                Log.d("at", "setOrderInfolayoutWrite: " + orders.writeDish.get(id));
            orderItemMesglayout.setPrice(orders.writeDish.get(id).getAllPrice() + "").
                    setName(orders.writeDish.get(id).getDeshName() + orders.writeDish.get(id).getDeshPrice()).setCount(orders.writeDish.get(id).getCount() + "");
            if (orders.writeDish.get(id).getItemsBean().getRemarks() != null) {
                orderItemMesglayout.setMethod(orders.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getName() + orders.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getPrice());
            } else {
                orderItemMesglayout.setMethod(" ");
            }
        }
        if (orderInfolayout != null) {
            orderInfolayout.setMesg(orders);
        }
    }

    private void setOrderInfolayout(String id) {
        if (id == null || orders.getOrder(id) == null) {
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


    OrderPresenter presenter;

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
        if (queryListAdapter != null) {
            queryListAdapter.setMitems(deshList);
            return;
        }
        deshListAdapter.setMitems(deshList);
    }

    private List<RequestPrintBackDesh.ItemsBean> backDesh;


    @Override
    public void sendSucc(int ord) {
        if (isBackDesh) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String date = year + "-" + month + "-" + day;
            printBackDesh(new RequestPrintBackDesh().setSname(AppMode.getInstance().getUsername()).setTitle("退菜单").setDate(date).setTabcode(tabname).setItems(backDesh));
            return;
        }
        isSend = true;
    }

    private boolean isSend = true;
    ResponseGetOrder responseOrd;

    private void sendOrds() {
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
                    setItems(orders.getItems()).
                    setMid(AppMode.getInstance().getMid()).
                    setUid(AppMode.getInstance().getUid()).
                    setPernum(responseOrd.getData().getPernum()).
                    setSmoney(orderInfolayout.getGivePrice() + "").
                    setSsmoney(orderInfolayout.getLastPrice() + "").
                    setZk(orders.getDisconut() * 10 + "").
                    setZkmoney(orderInfolayout.getDiscountPrice() + "").
                    setTmoney(orderInfolayout.getAllPrice() + "").
                    setTabname(tabname).
                    setTab_id(tableId).setPayee(AppMode.getInstance().getUsername()).
                    setYsmoney(orderInfolayout.getLastPrice() + "").
                    setType("0").
                    setCre_tm(responseOrd.getData().getCre_tm()).
                    setE_tm(responseOrd.getData().getE_tm()).
                    setOrdernum(responseOrd.getData().getOrdernum()).
                    setOrdnum(responseOrd.getData().getOrdnum()).
                    setYm(responseOrd.getData().getYm()).
                    setWritedishs(orders.getWriteDish()).
                    setAllremarks(orders.getAllMethod())
                    .setStorename(responseOrd.getData().getStorename())
                    .setType_txt(responseOrd.getData().getType_txt())
            );
        }
    }

    @Override
    public void setOrder(ResponseGetOrder getOrder) {
        if (orders == null) {
            orders = new Orders();
        }
        this.responseOrd = getOrder;
        List<RequestOrder.WriteDishBean> writedishs = null;
        if (getOrder.getData().getWritedishs() != null) {
            writedishs = getOrder.getData().getWritedishs();
            for (int i = 0; i < writedishs.size(); i++) {
                orders.addWriteDish(writedishs.get(i).getName(), new Orders.Write().
                        setOrders(orders).
                        setItemsBean(writedishs.get(i)).
                        setSend(isSend).
                        setCount(Float.valueOf(writedishs.get(i).getCount())).
                        setGiveCount(Float.valueOf(writedishs.get(i).getGiveCount())).
                        setBackCount(writedishs.get(i).getBackCount()));
            }
        }
        List<ResponseGetCaipinList.DataBean> itemsBeen = getOrder.getData().getItems();
        orders.setDisconut(Float.valueOf(getOrder.getData().getZk()) / 10);
        for (int i = 0; i < itemsBeen.size(); i++) {
            /*if (orders.getOrders().containsKey(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id())) {
                orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).setSend(true).
                        setCount(Float.valueOf(Float.valueOf(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getCount()) + Float.valueOf(itemsBeen.get(i).getCount()))).
                        setGiveCount(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getGiveCount() + itemsBeen.get(i).
                                getGiveCount());
            } else*/
            //{
            orders.setOrders(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id(),
                    new Orders.Order().setOrders(orders).setItemsBean(itemsBeen.get(i)).setSend(true).setCount(Float.valueOf(itemsBeen.get(i).getCount())).
                            setGiveCount(itemsBeen.get(i).
                                    getGiveCount()).setBackCount(itemsBeen.get(i).getBackCount()));
            //}
        }
        if (itemsBeen.size() != 0) {
            isOrderWrite = false;
            orders.setCurrPosition(orders.getOrders().keyAt(0));
        } else if (writedishs != null && writedishs.size() != 0) {
            isOrderWrite = true;
            orders.setCurrPosition(orders.writeDish.keyAt(0));

        } else {
            orders.setCurrPosition(null);
        }

        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
        isSend = true;
        orders.isChange = false;
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

        public ArrayMap<String, Write> writeDish;

        protected void addWriteDish(String id, Write writeDishBean) {
            if (writeDish == null) {
                writeDish = new ArrayMap<>();
            }
            writeDish.put(id, writeDishBean);
            writeDishBean.orders = this;
            isChange = true;
        }

        public void removeWriteDish(String id) {
            if (writeDish != null) {
                writeDish.remove(id);
            }
            isChange = true;
        }

        private ArrayMap<String, Order> orders = new ArrayMap<>();

        public float getDisconut() {
            return disconut;
        }

        public void setDisconut(float disconut) {
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
                for (int j = 0; j < itemsBeen.size(); j++) {
                    if (itemsBeen.get(j).getId().equals(orderList.get(i).getItemsBean().getId())) {
                        itemsBeen.get(j).setCount(Float.valueOf(orderList.get(i).getItemsBean().getCount()) + Float.valueOf(itemsBeen.get(j).getCount()) + "").setGiveCount(orderList.get(i).getItemsBean().getGiveCount() + itemsBeen.get(j).getGiveCount());
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

            public void setBackCount() {
                backCount = Float.valueOf(backCount) + 1;
                itemsBean.setBackCount(backCount + "");

            }

            float backCount;
            boolean isSend = false;
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

            float giveCount;

            public Write setItemsBean(RequestOrder.WriteDishBean itemsBean) {
                this.itemsBean = itemsBean;
                return this;
            }

            public RequestOrder.WriteDishBean getItemsBean() {
                return itemsBean;
            }

            RequestOrder.WriteDishBean itemsBean;/*= new RequestOrder.ItemsBean().setDish_num(1);*/

            public Write setGiveCount(float count) {
                this.giveCount = count;
                itemsBean.setGiveCount((int) giveCount + "");
                orders.isChange = true;
                return this;
            }

            public void backDesh() {
                if (count > 0) {
                    count = count - 1;
                } else if (giveCount > 0) {
                    giveCount = giveCount - 1;
                }
                setBackCount();
                itemsBean.setGiveCount((int) giveCount + "");
                itemsBean.setCount((int) count + "");
                orders.isChange = true;
            }

            public Write cancelGive() {
                count = count + giveCount;
                giveCount = 0;
                itemsBean.setGiveCount((int) giveCount + "");
                itemsBean.setCount((int) count + "");
                orders.isChange = true;
                return this;
            }

            public Write addGiveCount() {
                if (count >= 1) {
                    giveCount = giveCount + 1;
                    count = count - 1;
                }
                itemsBean.setCount(count + "");
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

            public String getMethodName() {
                if (itemsBean.getRemarks() != null && itemsBean.getRemarks().getRemarks().size() > 0) {
                    return itemsBean.getRemarks().getRemarks().get(0).getName();
                }
                return "";
            }

            public String getMethodPrice() {
                if (itemsBean.getRemarks() != null && itemsBean.getRemarks().getRemarks().size() > 0) {
                    return itemsBean.getRemarks().getRemarks().get(0).getPrice();
                }
                return "";
            }

            public float getGivePrice() {
                return giveCount * Float.valueOf(itemsBean.getPrice());
            }

            public float getAllPrice() {
                float price = 0;
                if (itemsBean.getRemarks() != null && itemsBean.getRemarks().getRemarks() != null)
                    for (int i = 0; i < itemsBean.getRemarks().getRemarks().size(); i++) {
                        if (itemsBean.getRemarks().getRemarks().get(i).getPrice() != null)
                            price = price + Float.valueOf(itemsBean.getRemarks().getRemarks().get(i).getPrice());
                    }
                return price + Float.valueOf(itemsBean.getPrice()) * count;

            }
        }

        public static class Order {
            protected Order() {

            }

            public float getBackCount() {
                return backCount;
            }

            public void setBackCount() {
                backCount = Float.valueOf(backCount) + 1;
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

            public void backDesh() {
                if (count > 0) {
                    count = count - 1;
                } else if (giveCount > 0) {
                    giveCount = giveCount - 1;
                }
                setBackCount();
                itemsBean.setGiveCount((int) giveCount);
                itemsBean.setCount((int) count + "");
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

            public Order setGiveCount(float count) {
                this.giveCount = count;
                itemsBean.setGiveCount((int) giveCount);
                orders.isChange = true;
                return this;
            }

            public Order cancelGive() {
                count = count + giveCount;
                giveCount = 0;
                itemsBean.setGiveCount((int) giveCount);
                itemsBean.setCount((int) count + "");
                orders.isChange = true;
                return this;
            }

            public Order addGiveCount() {
                if (count >= 1) {
                    giveCount = giveCount + 1;
                    count = count - 1;
                }
                itemsBean.setCount(count + "");
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
                        if (itemsBean.getRemark().getRemarks().get(i).getPrice() != null)
                            price = price + Float.valueOf(itemsBean.getRemark().getRemarks().get(i).getPrice());
                    }
                return (price + Float.valueOf(itemsBean.getPrice())) * count;

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

        public void dismiss() {
            popUpViewUtil.dismiss();
        }
    }
}
