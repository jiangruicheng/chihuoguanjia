package com.cndll.chgj.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.cndll.chgj.RXbus.EventType;
import com.cndll.chgj.RXbus.RxBus;
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
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.util.StringHelp;
import com.cndll.chgj.weight.AllLayout;
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
import rx.Observer;
import rx.Subscription;
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
    @BindView(R.id.alllayout)
    AllLayout alllayout;

    @OnClick(R.id.alllayout)
    void onclick_alllayout() {

        boolean isClick;
        if (orders == null) {
            return;
        }
        /*if (isOrderWrite) {
            isClick = orders.writeDish.get(orders.getCurrPosition()).isSend;
        } else {
            isClick = orders.getOrder(orders.getCurrPosition()).isSend;
        }*/
        boolean is = orders.getOrders().size() != 0;
        boolean isa = orders.writeDish == null ? false : orders.writeDish.size() != 0;
        boolean isb = is || isa;
        if (orders != null && (orders.getOrders().size() != 0 || (orders.writeDish == null ? false : orders.writeDish.size() != 0))) {
            final boolean isSend;
            String hint = "";
            if (isOrderWrite) {
                isSend = orders.writeDish.get(orders.getCurrPosition()).isSend;
            } else {
                isSend = orders.getOrder(orders.getCurrPosition()).isSend;
            }
            if (isSend) {
                hint = "退菜";
            } else {
                hint = "删除";
            }
            popUpkey(KeyWeight.Mode_OnlyNumb, Color.rgb(241, 93, 169), Color.rgb(251, 152, 67), "请输入菜品数量", hint, "确定", new KeyWeight.OnKeyClick() {
                @Override
                public void onKeyCancel(String s) {
                    if (isSend) {
                        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isReturn()) {
                            showMesg("无退菜权限");
                            return;
                        }
                        backDesh = new ArrayList<RequestPrintBackDesh.ItemsBean>();
                        if (!isOrderWrite) {
                            backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.getOrder(orders.getCurrPosition()).getItemsBean().getName()).
                                    setMoney(orders.getOrder(orders.getCurrPosition()).getItemsBean().getPrice()).
                                    setNum(/*orders.getOrder(orders.getCurrPosition()).getCount() +*/ "1").
                                    setUnit(orders.getOrder(orders.getCurrPosition()).getItemsBean().getUnit()).
                                    setM_name(""));
                            orders.getOrder(orders.getCurrPosition()).backDesh();
                        } else {
                            backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getName()).
                                    setMoney(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getPrice()).
                                    setNum(/*orders.writeDish.get(orders.getCurrPosition()).getCount() +*/ "1").
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
                    } else {
                        if (isOrderWrite) {
                            orders.removeWriteDish(orders.getCurrPosition());
                        } else {
                            orders.removeOrders(orders.getCurrPosition());
                        }
                    }

                    if (orders.getOrders().size() != 0) {
                        orders.setCurrPosition(orders.getOrders().keyAt(0));
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                    } else {
                        orderItemMesglayout.setMethod("").setCount("0").setName("").setPrice("");
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

    @BindView(R.id.txt_discount)
    TextView txtDiscount;

    @OnClick(R.id.query_nodesk)
    void onclick_querynodesk() {
        queryDesh();
    }

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
                        disProg();
                        if (baseResponse.getCode() == 1) {
                            if (((ResponseAddOrd) baseResponse).getData().getOid() != 0) {
                                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(((ResponseAddOrd) baseResponse).getData().getOid()).setOrders(orders), null);
                                MainActivity.removeBackPressEvent(backPressEvent);
                                //   presenter.printSetting(((ResponseAddOrd) baseResponse).getData().getOid());
                            }

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
        MainActivity.removeBackPressEvent(backPressEvent);
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
                popOrderRequest.four.setVisibility(View.GONE);
                boolean isHaveSend;
                if (isOrderWrite) {
                    isHaveSend = orders.writeDish.get(orders.getCurrPosition()).getGiveCount() == 0;
                } else {
                    isHaveSend = orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0;
                }
                if (isHaveSend) {
                    popOrderRequest.setViewHeight(2);
                    popOrderRequest.setThirdVisble(View.GONE);
                } else {
                    popOrderRequest.setViewHeight(3);
                    popOrderRequest.setThirdText("取消赠送");
                }
                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View view) {
                        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isGive()) {
                            showMesg("无赠送权限");
                            popOrderRequest.dismiss();
                            return;
                        }
                        if (!isOrderWrite) {
                            orders.getOrder(orders.getCurrPosition()).addGiveCount();
                        } else {
                            orders.writeDish.get(orders.getCurrPosition()).addGiveCount();
                        }
                        boolean isHaveSend;
                        if (isOrderWrite) {
                            isHaveSend = orders.writeDish.get(orders.getCurrPosition()).getGiveCount() == 0;
                        } else {
                            isHaveSend = orders.getOrder(orders.getCurrPosition()).getGiveCount() == 0;
                        }
                        if (isHaveSend) {
                            popOrderRequest.setThirdVisble(View.GONE);
                            popOrderRequest.setViewHeight(2);
                        } else {
                            popOrderRequest.setThirdVisble(View.VISIBLE);
                            popOrderRequest.setViewHeight(3);
                            popOrderRequest.setThirdText("取消赠送");
                        }
                        popOrderRequest.dismiss();
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        sendOrds(presenter.GIVE);
                    }

                    @Override
                    public void onSecond(View view) {
                        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isReturn()) {
                            showMesg("无退菜权限");
                            popOrderRequest.dismiss();
                            return;
                        }
                        showMesg("是否确定退菜", new MesgShow.OnButtonListener() {
                            @Override
                            public void onListerner() {
                                backDesh = new ArrayList<RequestPrintBackDesh.ItemsBean>();
                                if (!isOrderWrite) {
                                    orders.getOrder(orders.getCurrPosition()).backDesh();
                                    backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.getOrder(orders.getCurrPosition()).getItemsBean().getName()).
                                            setMoney(orders.getOrder(orders.getCurrPosition()).getItemsBean().getPrice()).
                                            setNum(/*orders.getOrder(orders.getCurrPosition()).getCount() +*/ orders.getOrders().get(orders.getCurrPosition()).backCountOnce + "").
                                            setUnit(orders.getOrder(orders.getCurrPosition()).getItemsBean().getUnit()).
                                            setM_name(""));


                                } else {
                                    orders.writeDish.get(orders.getCurrPosition()).backDesh();
                                    backDesh.add(new RequestPrintBackDesh.ItemsBean().setName(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getName()).
                                            setMoney(orders.writeDish.get(orders.getCurrPosition()).getItemsBean().getPrice()).
                                            setNum(/*orders.writeDish.get(orders.getCurrPosition()).getCount() +*/ orders.writeDish.get(orders.getCurrPosition()).backCountOnce + "").
                                            setUnit("盘").
                                            setM_name(""));

                                }
                                if (orders.getOrders().size() == 0) {
                                    orders.setCurrPosition(null);
                                } else {
                                    orders.setCurrPosition(orders.getOrders().keyAt(0));
                                }
                                popOrderRequest.setThirdVisble(View.GONE);
                                popOrderRequest.setViewHeight(2);
                                popOrderRequest.dismiss();
                                setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                                sendOrds(presenter.BACK);
                                isBackDesh = true;
                            }
                        });
                    }

                    @Override
                    public void onThird(View view) {
                        if (!isOrderWrite) {
                            orders.getOrder(orders.getCurrPosition()).cancelGive();
                        } else {
                            orders.writeDish.get(orders.getCurrPosition()).cancelGive();
                        }
                        popOrderRequest.dismiss();
                        setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        sendOrds();
                    }
                });

            } else {
                final PopOrderRequest popOrderRequest = new PopOrderRequest();
                popOrderRequest.init(getContext(), yaoqiu);
                popOrderRequest.show();
                boolean isGive = false;
                if (isOrderWrite) {
                    if (orders.writeDish.get(orders.getCurrPosition()).giveCount != 0) {
                        isGive = true;
                    }
                } else {
                    if (orders.getOrder(orders.getCurrPosition()).giveCount != 0) {
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
                            if (!isOrderWrite) {
                                orders.getOrder(orders.getCurrPosition()).cancelGive();
                            } else {
                                orders.writeDish.get(orders.getCurrPosition()).cancelGive();
                            }
                            popOrderRequest.setViewHeight(3);
                            popOrderRequest.four.setVisibility(View.GONE);
                            popOrderRequest.dismiss();
                            setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                        }
                    });
                } else {
                    popOrderRequest.four.setVisibility(View.GONE);
                    popOrderRequest.setViewHeight(3);
                }

                popOrderRequest.setOnItemClick(new PopOrderRequest.onItemClick() {
                    @Override
                    public void onFirst(View v) {
                        if (isOrderWrite) {
                            replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setWrite(orders.writeDish.get(orders.getCurrPosition())), new NoteImpl());
                        } else {
                            replaceFragmentAddToBackStack(NoteFragment.newInstance(null, null).setOrder(orders.getOrder(orders.getCurrPosition())), new NoteImpl());
                        }
                        popOrderRequest.dismiss();
                        MainActivity.removeBackPressEvent(backPressEvent);
                    }

                    @Override
                    public void onSecond(final View view) {
                        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isGive()) {
                            showMesg("无赠送权限");
                            popOrderRequest.dismiss();
                            return;
                        }
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
                            popOrderRequest.four.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!isOrderWrite) {
                                        orders.getOrder(orders.getCurrPosition()).cancelGive();
                                    } else {
                                        orders.writeDish.get(orders.getCurrPosition()).cancelGive();
                                    }
                                    view.setBackgroundResource(R.color.cube_holo_blue_dark);
                                    popOrderRequest.setViewHeight(3);
                                    popOrderRequest.four.setVisibility(View.GONE);
                                    setOrderInfolayout(orders.getCurrPosition(), isOrderWrite);
                                    popOrderRequest.dismiss();
                                }
                            });
                            popOrderRequest.setViewHeight(4);
                            popOrderRequest.four.setVisibility(View.VISIBLE);
                            popOrderRequest.four.setText("取消赠送");
                            popOrderRequest.dismiss();
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
                            orderItemMesglayout.setMethod("").setCount(" ").setName("").setPrice("");
                            orderInfolayout.setAllMoney("0").setCount("0").setDiscount("0").setGive("0").setLastMoney("0");
                            orders = null;
                        }
                        popOrderRequest.dismiss();
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

    PopUpViewUtil popUpQuery;

    private void queryDesh() {

        popUpQuery = PopUpViewUtil.getInstance();
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
                if (queryListAdapter.getMitems().get(position).getIs_over().equals("1")) {
                    toast("此菜已售完");
                    return;
                }
                if (orders.Iscontan(queryListAdapter.getMitems().get(position).getId())) {
                    showMesg("已点此菜，请修改数量");
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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("CHGJ", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        keyWeight.setOnTransClick(new KeyWeight.OnTransClick() {
            @Override
            public void onTrans(int mode) {
                switch (mode) {
                    case ABC:
                        editor.putInt("KEYSTATUE", ABC).commit();
                        break;
                    case NUMB:
                        editor.putInt("KEYSTATUE", NUMB).commit();
                        break;
                }
            }
        });
        keyWeight.setKeyIsABC(sharedPreferences.getInt("KEYSTATUE", -1));
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
            showMesg("不存在未送单菜品，无需送单");
            return;
        }
        if (orders.getOrders().size() != 0 || orders.writeDish.size() != 0) {
            replaceFragmentAddToBackStack(SendFragment.newInstance(null, null).setOrderDishFragment(this), new OrderImpl());
            MainActivity.removeBackPressEvent(backPressEvent);
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
        MainActivity.removeBackPressEvent(backPressEvent);
    }

    @BindView(R.id.other)
    Button other;


    @OnClick(R.id.other)
    void onclick_other() {
        final PopviewOther popviewOther = new PopviewOther();
        popviewOther.init();
        if (orderId == 0) {
            popviewOther.trunDesk.setTextColor(Color.GRAY);
            popviewOther.removeDesk.setTextColor(Color.GRAY);
        }
        popviewOther.popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                popviewOther.unbinder.unbind();
            }
        });
        popviewOther.trunDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                popviewOther.dismiss();
                if (orderId == 0) {
                    showMesg("此台不存在消费，无需转台");
                    return;
                }
                final PopUpViewUtil popviewDesk = PopUpViewUtil.getInstance();
                final View view = LayoutInflater.from(getContext()).inflate(R.layout.popview_turndesk, null, false);
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
                deshList.getLocationOnScreen(locations);
                popviewDesk.popListWindow(send, view,
                        popviewDesk.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                        deshList.getHeight(),
                        Gravity.NO_GRAVITY, locations);
                showProg("");
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
                                disProg();
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
                if (orderId == 0) {
                    showMesg("此台不存在消费，无需撤台");
                    return;
                }
                showMesg("撤台将会撤销消费，是否确认?", new MesgShow.OnButtonListener() {
                    @Override
                    public void onListerner() {
                        if (orderId != 0) {
                            presenter.removeOrder(orderId + "", 2 + "");
                            popviewOther.dismiss();
                        }
                    }
                });
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
                int[] locations = new int[2];
                locations[0] = 0;
                locations[1] = popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 4;
                popUpViewUtil.popListWindow(send, view,
                        popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                        popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3,
                        Gravity.NO_GRAVITY, locations);
            }
        });
        popviewOther.popUpViewUtil.popListWindow(send, popviewOther.view,
                popviewOther.popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                popviewOther.popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3,
                Gravity.BOTTOM, null);
    }

    private void printOrders() {
        showProg("");
        toast("正在打印");
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
                disProg();
                toast(baseResponse.extra);
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
        if (orders == null) {
            showMesg("此台不存在消费，无需打折");
            return;
        }
        if (!AppMode.getInstance().isDiscount() && !AppMode.getInstance().isBoss()) {
            showMesg("无打折权限");
            return;
        }
        if (!orders.isChange && orderId != 0) {
            popUpkey(2, Color.rgb(171, 171, 171), Color.rgb(1, 169, 104), "请输入折扣，例如8折则输入0.8", "取消打折", "确定", new KeyWeight.OnKeyClick() {
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
        if (!AppMode.getInstance().isBoss() && !AppMode.getInstance().isOrder()) {
            showMesg("无买单权限");
            return;
        }
        if (orders == null) {
            showMesg("此台不存在消费，无需买单");
            return;
        }
        // replaceFragmentAddToBackStack(ApplyPayFragment.newInstance(null, null), null);
        if (!orders.isChange) {
            if (orderId != 0) {
                replaceFragmentAddToBackStack(PaySwitchFragment.newInstance(null, null).setOrderID(orderId).setOrders(orders), null);
                MainActivity.removeBackPressEvent(backPressEvent);
            }
        } else {
            MesgShow.showMesg("", "有菜品未送单,请先送单", dazhe, null, null, false);
        }
    }

    Unbinder unbinder;

    @OnClick(R.id.number_edit)
    void onclick_numberEdit() {
    }


    private void popUpkey(int mode, int cancelcolor, int surecolor, String showhint, String cancelhint, String surehint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setCancelText(cancelhint);
        keyWeight.setSureText(surehint);
        keyWeight.setShowHintText(showhint);
        keyWeight.setCancelcolor(cancelcolor);
        keyWeight.setSurecolor(surecolor);
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
        public boolean onBackPress() {
            back();
            return true;
        }
    };
    Subscription reSet;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_desh, container, false);
        unbinder = ButterKnife.bind(this, view);
        numberEdit.setVisibility(View.GONE);
        yaoqiu.setVisibility(View.GONE);
        reSet = RxBus.getDefault().toObservable(EventType.class).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<EventType>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(EventType eventType) {
                        if (eventType.getType() != EventType.RESET) {
                            return;
                        }
                        if (orders != null) {
                            if (orders.writeDish != null) {
                                orders.writeDish.clear();
                            }
                            if (orders.orders != null) {
                                orders.orders.clear();
                            }
                        }
                        setOrderInfolayout(orders.getCurrPosition());
                    }
                });
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
                    MainActivity.removeBackPressEvent(backPressEvent);
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
            if (orders.writeDish == null || orders.writeDish.size() == 0) {

            } else {
                isOrderWrite = true;
                orders.setCurrPosition(orders.writeDish.keyAt(0));
            }
        }
        MainActivity.setBackPressEvent(backPressEvent);
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
       /* PagingScrollHelper scrollHelper = new PagingScrollHelper();
        scrollHelper.setUpRecycleView(deshList);*/
        //设置页面滚动监听
        /*scrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
            @Override
            public void onPageChange(int index) {
                // Toast.makeText(getActivity(), "" + index, Toast.LENGTH_SHORT).show();
            }
        });*/
        deshListAdapter.setOnItemClickLister(new OnItemClickLister() {
            @Override
            public void OnItemClick(View view, int position) {
                if (orders == null) {
                    orders = new Orders();
                }
                if (deshListAdapter.getMitems().get(position).getIs_over().equals("1")) {
                    toast("此菜已售完");
                    return;
                }
                if (orders.Iscontan(deshListAdapter.getMitems().get(position).getId())) {
                    showMesg("已点此菜，请修改数量");
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
                    setName(orders.writeDish.get(id).getDeshName() + orders.writeDish.get(id).getOnePrice()).setCount(orders.writeDish.get(id).getCount() + "").setMethod("");
            if (orders.writeDish.get(id).getItemsBean().getRemarks() != null) {
                orderItemMesglayout.setMethod(orders.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getName() + orders.writeDish.get(id).getItemsBean().getRemarks().getRemarks().get(0).getPrice());
            }
            if (orders.writeDish.get(id).getGiveCount() != 0) {
                orderItemMesglayout.setMethod(orderItemMesglayout.getMethod().getText().toString() + "赠送：" + orders.writeDish.get(id).getGiveCount());
            }
            if (orders.writeDish.get(id).getBackCount() != 0) {
                orderItemMesglayout.setMethod(orderItemMesglayout.getMethod().getText().toString() + "退菜：" + orders.writeDish.get(id).getBackCount());

            }
            /*if (orders.writeDish.get(id).getItemsBean().getRemarks() == null) {
                orderItemMesglayout.setMethod(" ");
            }*/
        }
        if (orderInfolayout != null) {
            orderInfolayout.setMesg(orders);
        }
    }

    private void setOrderInfolayout(String id) {
        if (id == null || orders.getOrder(id) == null) {
            orderItemMesglayout.setMethod("").setCount(" ").setName("").setPrice("");
        } else {
            if (orderItemMesglayout != null) {
                orderItemMesglayout.
                        setPrice(orders.getOrder(id).getAllPrice() + "").
                        setName(orders.getOrder(id).getDeshName() + " " + orders.getOrder(id).getOnePrice()).
                        setMethod(orders.getOrder(id).getMethodName() + orders.getOrder(id).getMethodPrice()).setCount(orders.getOrder(id).getCount() /*+ orders.getOrder(id).getGiveCount() */ + "");
                if (orders.getOrder(id).getGiveCount() != 0) {
                    orderItemMesglayout.setMethod(orderItemMesglayout.getMethod().getText().toString() + "赠送：" + orders.getOrder(id).getGiveCount());
                }
                if (orders.getOrder(id).getBackCount() != 0) {
                    orderItemMesglayout.setMethod(orderItemMesglayout.getMethod().getText().toString() + "退菜：" + orders.getOrder(id).getBackCount());
                }
            }

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
        if (reSet != null && reSet.isUnsubscribed()) {
            reSet.unsubscribe();
        }
        reSet = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showMesg(String mesg, MesgShow.OnButtonListener sure) {
        MesgShow.showMesg("", mesg, info, sure, null, true);
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
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            String date = year + "-" + month + "-" + day + " " + hour + ":" + minute;
            printBackDesh(new RequestPrintBackDesh().setSname(AppMode.getInstance().getUsername()).setTitle("退菜单").setDate(date).setTabcode(tabname).setItems(backDesh));
            return;
        }
        isSend = true;
    }

    private boolean isSend = true;
    ResponseGetOrder responseOrd;

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
                            setItems(orders.getItems()).
                            setMid(AppMode.getInstance().getMid()).
                            setUid(AppMode.getInstance().getUid()).
                            setPernum(responseOrd.getData().getPernum()).
                            setSmoney(orderInfolayout.getGivePrice() + "").
                            setSsmoney(orderInfolayout.getLastPrice() + "").
                            setZk(orders.getDisconut() + "").
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
                    , type);
        }
    }

    private void sendOrds() {
        sendOrds(0);
    }

    @Override
    public void setOrder(ResponseGetOrder getOrder) {
        if (orders == null) {
            orders = new Orders();
        } else {
            if (orders.writeDish != null) {
                orders.writeDish.clear();
            }
            orders.getOrders().clear();
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
        orders.setDisconut(Float.valueOf(getOrder.getData().getZk()));
        for (int i = 0; i < itemsBeen.size(); i++) {
            /*if (orders.getOrders().containsKey(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id())) {
                orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).setSend(true).
                        setCount(Float.valueOf(Float.valueOf(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getCount()) + Float.valueOf(itemsBeen.get(i).getCount()))).
                        setGiveCount(orders.getOrder(itemsBeen.get(i).getId() + "" + itemsBeen.get(i).getDc_id()).getGiveCount() + itemsBeen.get(i).
                                getGiveCount());
            } else*/
            //{
            itemsBeen.get(i).setAddCount(0);
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
        orders.isAdd = false;
        orders.isChange = false;
    }

    @Override
    public void printNoDeskOrderSucc(int ord) {

    }

    @Override
    public void backView() {
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

    public static class Orders {
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

        public ArrayMap<String, Write> writeDish;

        protected void addWriteDish(String id, Write writeDishBean) {
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

        private ArrayMap<String, Order> orders = new ArrayMap<>();

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

            float backCountOnce = 0;

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
                        if (itemsBean.getRemarks().getRemarks().get(i).getPrice() != null && StringHelp.isFloat(itemsBean.getRemarks().getRemarks().get(i).getPrice()))
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

            float backCountOnce = 0;

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
