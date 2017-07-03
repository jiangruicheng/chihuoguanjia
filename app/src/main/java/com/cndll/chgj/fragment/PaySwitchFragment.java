package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.info.Orders;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePayStatue;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.weight.OrderInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaySwitchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaySwitchFragment extends BaseFragment {
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
    @BindView(R.id.xianjin)
    TextView xianjin;

    @OnClick(R.id.xianjin)
    void onclick_xianjin() {
        gotoPay("3");
    }

    @BindView(R.id.weixin)
    TextView weixin;
    @BindView(R.id.zhifubao)
    TextView zhifubao;
    @BindView(R.id.card)
    TextView card;

    @OnClick(R.id.card)
    void onclick_card() {

        gotoPay(4 + "");
        /*AppRequest.getAPI().payMoney(orderID + "", orderInfolayout.getLastPrice() + "", 4 + "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
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

                     printOrders();
                }
            }
        });*/
    }

    private void gotoPay(String type) {
        replaceFragmentAddToBackStack(SuccessFragment.newInstance(type, orderInfolayout.getLastPrice() + "").setOrderID(orderID), null);
    }

/*    private void printOrders() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
       *//* AppRequest.getAPI().printOrder(orderID + "", "总出品单", year + "-" + month + "-" + day, AppMode.getInstance().getUsername(), AppMode.getInstance().getPrint_code()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
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
        });*//*
    }*/

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PaySwitchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaySwitchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaySwitchFragment newInstance(String param1, String param2) {
        PaySwitchFragment fragment = new PaySwitchFragment();
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

    OrderInfo orderInfolayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_switch, container, false);
        unbinder = ButterKnife.bind(this, view);
        orderInfolayout = new OrderInfo();
        AppRequest.getAPI().updataPayee(orderID + "", AppMode.getInstance().getUsername()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResponseAddOrd>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseAddOrd responseAddOrd) {
                responseAddOrd.getData();
            }
        });
        title.setText("收款");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        orderInfolayout.init(orderInfo);
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                if (orderID != 0)
                    isHavePayCount("微信");
                titlename = "微信收款";
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                if (orderID != 0)
                    //gotoWebView();

                    isHavePayCount("支付宝");
                titlename = "支付宝收款";
            }
        });
        setOrderInfolayout();
        return view;
    }

    String titlename;

    private void gotoWebView() {
        String url = String.format(AppRequest.ACCOUNTURL + "web/costpay?id=%d&type=%d&mid=%s", orderID, type, AppMode.getInstance().getMid());
        replaceFragmentAddToBackStack(WebViewFragment.newInstance(url, titlename).setOrderID(orderID), null);
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrderID() {
        return orderID;
    }

    public PaySwitchFragment setOrderID(int orderID) {
        this.orderID = orderID;
        return this;
    }

    private int orderID = 0;

    public Orders getOrders() {
        return orders;
    }

    public PaySwitchFragment setOrders(Orders orders) {
        this.orders = orders;

        return this;
    }

    private Orders orders;

    private void setOrderInfolayout() {

        if (orderInfolayout != null) {
            orderInfolayout.setMesg(null, orders);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
/*        if (context instanceof OnFragmentInteractionListener) {
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

    private boolean isHavePayCount(final String s) {
        AppRequest.getAPI().
                payStatue(AppMode.getInstance().getUid(), AppMode.getInstance().getMid()).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(
                        new BaseView() {
                            @Override
                            public void showMesg(String mesg) {

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

                            }

                            @Override
                            public void setPresenter(BasePresenter presenter) {

                            }
                        }
                ) {
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
                        if (((ResponsePayStatue) baseResponse).getData() == 1) {
                            gotoWebView();
                        } else if (((ResponsePayStatue) baseResponse).getData() == -2) {
                            replaceFragmentAddToBackStack(TurnToApplyPayFragment.newInstance(s, null), null);
                        } else if (((ResponsePayStatue) baseResponse).getData() == 0) {
                            showToast("支付申请审核中");
                        } else if (((ResponsePayStatue) baseResponse).getData() == -1) {
                            showToast("审核失败");
                            replaceFragmentAddToBackStack(TurnToApplyPayFragment.newInstance(s, null), null);
                        }
                    }
                });
        return false;
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
