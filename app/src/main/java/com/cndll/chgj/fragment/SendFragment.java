package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
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
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.info.Orders;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintBill;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseCailei;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetSeting;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.view.OrderView;
import com.cndll.chgj.util.DateFormatUtil;
import com.cndll.chgj.weight.MesgShow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends BaseFragment implements OrderView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tablename)
    TextView tablename;
    @BindView(R.id.person_count)
    EditText personCount;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.cancel)
    Button cancel;

    @OnClick(R.id.cancel)
    void onclick_cancel() {
        popBackFragment();
    }

    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.save)
    Button save;
    Unbinder unbinder;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendFragment newInstance(String param1, String param2) {
        SendFragment fragment = new SendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OrderDish2Fragment getOrderDishFragment() {
        return orderDishFragment;
    }

    public SendFragment setOrderDishFragment(OrderDish2Fragment orderDishFragment) {
        this.orderDishFragment = orderDishFragment;
        return this;
    }

    OrderDish2Fragment orderDishFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        unbinder = ButterKnife.bind(this, view);
        delete.setVisibility(View.GONE);
        titleRight.setText("人数：" + personCount.getText().toString());
        title.setText("");
        //rightText.setText("修改");
        if (orderDishFragment.responseOrd != null) {
            titleRight.setText("人数：" + orderDishFragment.responseOrd.getData().getPernum());
        } else {
            titleRight.setText("人数：2");
        }
        titleLeft.setText(" 桌台: " + orderDishFragment.tabname);
        if (orderDishFragment.responseOrd != null) {
            personCount.setText(orderDishFragment.responseOrd.getData().getPernum());
        }
        if (orderDishFragment.tabname == null) {
            tablename.setText("");
        } else {
            tablename.setText(orderDishFragment.tabname);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        save.setText("确定");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDishFragment.orders == null) {
                    return;
                }
                showProg("");
                orderDishFragment.orderInfolayout.setMesg(orderDishFragment.orders);
                if (orderDishFragment.orderId == 0) {
                    orderPresenter.sendOrder(new RequestOrder().
                            setItems(orderDishFragment.orders.getItems()).
                            setMid(AppMode.getInstance().getMid()).
                            setUid(AppMode.getInstance().getUid()).
                            setPernum(personCount.getText().toString()).
                            setSmoney(orderDishFragment.orderInfolayout.getGivePrice() + "").
                            setSsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                            setZk(orderDishFragment.orders.getDisconut() + "").
                            setZkmoney(orderDishFragment.orderInfolayout.getDiscountPrice() + "").
                            setTmoney(orderDishFragment.orderInfolayout.getAllPrice() + "").
                            setTabname(orderDishFragment.tabname).
                            setTab_id(orderDishFragment.tableId).setPayee(AppMode.getInstance().getUsername()).
                            setYsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                            setWritedishs(orderDishFragment.orders.getWriteDish()).setAllremarks(orderDishFragment.orders.getAllMethod()).setNote(note.getText().toString()));
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Orders orders;
                            if (orderDishFragment.orders != null) {
                                orders = new Orders();
                                if (orders.orders == null) {
                                    orders.orders = new ArrayMap<String, Orders.Order>();
                                }
                                if (orders.writeDish == null) {
                                    orders.writeDish = new ArrayMap<String, Orders.Write>();
                                }
                                orders.orders.putAll((Map<? extends String, ? extends Orders.Order>) orderDishFragment.orders.orders);
                                orders.orders.putAll((Map<? extends String, ? extends Orders.Order>) orderDishFragment.sendOrders.orders);
                                if (orderDishFragment.orders.writeDish != null)
                                    orders.writeDish.putAll((Map<? extends String, ? extends Orders.Write>) orderDishFragment.orders.writeDish);
                                if (orderDishFragment.sendOrders.writeDish != null)
                                    orders.writeDish.putAll((Map<? extends String, ? extends Orders.Write>) orderDishFragment.sendOrders.writeDish);
                                if (orders.allRemarklist == null) {
                                    orders.allRemarklist = new ArrayList<ResponseGetCaipinList.DataBean.RemarkBean>();
                                }
                                orders.allRemarklist.addAll(orderDishFragment.orders.getAllMethod());
                                orders.allRemarklist.addAll(orderDishFragment.sendOrders.getAllMethod());
                                orders.setDisconut(orderDishFragment.sendOrders.getDisconut());
                            } else {
                                return;
                            }
                            orderDishFragment.orderInfolayout.countAll(orders);
                            orderPresenter.updateOreder(new RequestOrder().setId(orderDishFragment.orderId + "").
                                    setItems(orders.getItems()).
                                    setMid(AppMode.getInstance().getMid()).
                                    setUid(AppMode.getInstance().getUid()).
                                    setPernum(personCount.getText().toString()).
                                    setSmoney(orderDishFragment.orderInfolayout.getGivePrice() + "").
                                    setSsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                                    setZk(orderDishFragment.sendOrders.getDisconut() + "").
                                    setZkmoney(orderDishFragment.orderInfolayout.getDiscountPrice() + "").
                                    setTmoney(orderDishFragment.orderInfolayout.getAllPrice() + "").
                                    setTabname(orderDishFragment.tabname).
                                    setTab_id(orderDishFragment.tableId).setPayee(AppMode.getInstance().getUsername()).
                                    setYsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                                    setType("0").
                                    setCre_tm(orderDishFragment.responseOrd.getData().getCre_tm()).
                                    setE_tm(orderDishFragment.responseOrd.getData().getE_tm()).
                                    setOrdernum(orderDishFragment.responseOrd.getData().getOrdernum()).
                                    setOrdnum(orderDishFragment.responseOrd.getData().getOrdnum()).
                                    setYm(orderDishFragment.responseOrd.getData().getYm()).
                                    setWritedishs(orders.getWriteDish()).
                                    setAllremarks(orders.getAllMethod())
                                    .setStorename(orderDishFragment.responseOrd.getData().getStorename())
                                    .setType_txt(orderDishFragment.responseOrd.getData().getType_txt()).
                                            setNote(note.getText().toString())
                            );
                        }
                    }.start();

                }
            }
        });
        personCount.setFocusable(true);
        personCount.setFocusableInTouchMode(true);
        personCount.requestFocus();
        showInput(personCount);
        return view;
    }

    private void printSetting(final int ord) {
        AppRequest.getAPI().getSetting(AppMode.getInstance().getUid(),
                AppMode.getInstance().getMid()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(null) {
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
                        int type;
                        if (baseResponse.getCode() == 1) {
                            ResponseGetSeting responseGetSeting = ((ResponseGetSeting) baseResponse);
                            if (responseGetSeting.getData().getCd_method().equals("1")) {
                                type = 1;
                            } else {
                                type = 2;
                            }

                            if (orderDishFragment.orderId != 0) {
                                printAddOrders(type);
                            } else {
                                printOrders(ord, 2);
                            }
                        }
                    }
                });
    }

    private void printAddOrders(int type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final String time = DateFormatUtil.transForDate1(DateFormatUtil.currentTimeStamp());
        final String data = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        Orders orders = orderDishFragment.orders;
        List<Orders.Order> orderList = orders.getAll();
        List<Orders.Write> writeList;
        switch (type) {
            case 1:
                final RequestPrintBill request = new RequestPrintBill().
                        setDate(time).
                        setSname("下单人：" + AppMode.getInstance().getUsername()).
                        setTabcode(orderDishFragment.tabname).
                        setTitle("出品分单");
                Observable.from(orderList).subscribe(new Observer<Orders.Order>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Orders.Order order) {
                        List<RequestPrintBill.ItemsBean> item = new ArrayList<RequestPrintBill.ItemsBean>();

                        item.add(new RequestPrintBill.ItemsBean().
                                setName(order.getItemsBean().getName()).
                                setMoney(order.getItemsBean().getPrice()).
                                setNum(order.getItemsBean().getAddCount() + "").
                                setUnit(order.getItemsBean().getUnit()).
                                setM_name(getMethodName(order)).
                                setMachine(order.getItemsBean().getMachine()));
                        request.setItems(item);
                        if (order.isSend) {

                        } else {
                            AppRequest.getAPI().printAddOrder(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseCailei>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(ResponseCailei responseCailei) {
                                    RxBus.getDefault().post(new EventType().setType(EventType.SHOW).setExtra(responseCailei.getExtra()));
                                }
                            });
                        }
                    }
                });
                break;
            case 2:
                if (orders.writeDish != null && orders.writeDish.values() != null) {
                    writeList = new ArrayList<>(orders.writeDish.values());
                }
                Map<String, RequestPrintBill> prints = new ArrayMap<>();
                for (Orders.Order o : orderList) {
                    if (o.isSend) {
                        continue;
                    }
                    if (!prints.containsKey(o.getItemsBean().getMachine())) {
               /* if (prints.get(o.getItemsBean().getMachine()) == null) {*/
                        prints.put(o.getItemsBean().getMachine(), new RequestPrintBill());
                        //}
                    }
                    if (prints.get(o.getItemsBean().getMachine()).getItems() == null) {
                        prints.get(o.getItemsBean().getMachine()).setItems(new ArrayList<RequestPrintBill.ItemsBean>());
                    }

                    prints.get(o.getItemsBean().getMachine()).getItems().
                            add(new RequestPrintBill.ItemsBean().
                                    setName(o.getItemsBean().getName()).
                                    setUnit(o.getItemsBean().getUnit()).
                                    setNum(o.getItemsBean().getAddCount() + "").
                                    setMoney(o.getItemsBean().getPrice()).setM_name(getMethodName(o)).setMachine(o.getItemsBean().getMachine()));
                }
                prints.values();
                Observable.from(new ArrayList<RequestPrintBill>(prints.values())).subscribe(new Observer<RequestPrintBill>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RequestPrintBill requestPrintBill) {
                        requestPrintBill.setDate(time).setSname("下单人：" + AppMode.getInstance().getUsername()).setTabcode(orderDishFragment.tabname).setTitle("追加单");
                        AppRequest.getAPI().printAddOrder(requestPrintBill).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseCailei>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseCailei responseCailei) {
                                RxBus.getDefault().post(new EventType().setExtra(responseCailei.getExtra()).setType(EventType.SHOW));
                            }
                        });
                    }
                });

                break;
        }
        if (orders.writeDish == null) {
            return;
        }
        writeList = new ArrayList<>(orders.writeDish.values());
        RequestPrintBill printBill = new RequestPrintBill();
        printBill.setItems(new ArrayList<RequestPrintBill.ItemsBean>());
        printBill.setDate(time).setSname("下单人：" + AppMode.getInstance().getUsername()).setTabcode(orderDishFragment.tabname).setTitle("追加单");
        for (Orders.Write w : writeList) {
            if (w.isSend) {

            } else {
                printBill.getItems().add(new RequestPrintBill.ItemsBean().setUnit(w.getItemsBean().getUnit()).setName(w.getItemsBean().getName()).setNum(w.getItemsBean().getCount()).setMoney(w.getItemsBean().getPrice()));
            }
        }
        AppRequest.getAPI().printAddOrder(printBill).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseCailei>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseCailei responseCailei) {
                RxBus.getDefault().post(new EventType().setExtra(responseCailei.getExtra()).setType(EventType.SHOW));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private String getMethodName(Orders.Order o) {
        StringBuffer mname = new StringBuffer("");
        if (o.getItemsBean().getRemark() != null && o.getItemsBean().getRemark().getRemarks() != null) {
            for (ResponseMethod.DataBean m : o.getItemsBean().getRemark().getRemarks()) {
                mname.append(m.getName());
                mname.append("+" + m.getPrice() + " ");
            }
            mname.insert(0, "(");
            mname.insert(mname.length(), ")");
        }
        return mname.toString();
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void printOrders(int ord, int type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        final String time = DateFormatUtil.transForDate1(DateFormatUtil.currentTimeStamp());
        AppRequest.getAPI().printOrder(ord + "", type + "", /*year + "-" + month + "-" + day + " " + hour + ":" + minute*/time, "下单人：" + AppMode.getInstance().getUsername())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(SendFragment.this) {
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

                RxBus.getDefault().post(new EventType().setExtra(baseResponse.getExtra()).setType(EventType.SHOW));
            }
        });
    }

    private void back() {
        disProg();
        if (fragmentList.get(fragmentList.size() - 2) instanceof OrderDish2Fragment) {
            popBackFragment();
            popBackFragment();
        } else {
            popBackFragment();
            popBackFragment();
            popBackFragment();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        baseShowMesg(mesg, back);
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

    OrderPresenter orderPresenter;

    @Override
    public void setPresenter(OrderPresenter presenter) {
        orderPresenter = presenter;
        orderPresenter.setView(this);
    }

    @Override
    public void setDcList(List<ResponseGetCaileiList.DataBean> data) {

    }

    @Override
    public void setDeshList(List<ResponseGetCaipinList.DataBean> deshList) {

    }

    @Override
    public void sendSucc(int ord) {
        back();
        printSetting(ord);

    }

    @Override
    public void setOrder(ResponseGetOrder getOrder) {

    }

    @Override
    public void showMesgView(String mesg, MesgShow.OnButtonListener sure) {

    }

    @Override
    public void printNoDeskOrderSucc(int orderid) {

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
}
