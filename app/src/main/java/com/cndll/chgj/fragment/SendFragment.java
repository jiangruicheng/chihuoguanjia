package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.view.OrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    EditText tablename;
    @BindView(R.id.person_count)
    EditText personCount;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.cancel)
    Button cancel;
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

    public OrderDishFragment getOrderDishFragment() {
        return orderDishFragment;
    }

    public SendFragment setOrderDishFragment(OrderDishFragment orderDishFragment) {
        this.orderDishFragment = orderDishFragment;
        return this;
    }

    OrderDishFragment orderDishFragment;

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
        rightText.setText("修改");
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            setTab_id(orderDishFragment.tableId).setPayee("1234").
                            setYsmoney(orderDishFragment.orderInfolayout.getLastPrice() + ""));
                } else {
                    orderPresenter.updateOreder(new RequestOrder().setId(orderDishFragment.orderId + "").
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
                            setTab_id(orderDishFragment.tableId).setPayee("1234").
                            setYsmoney(orderDishFragment.orderInfolayout.getLastPrice() + "").
                            setType("0").
                            setCre_tm(orderDishFragment.responseOrd.getData().getCre_tm()).
                            setE_tm(orderDishFragment.responseOrd.getData().getE_tm()).
                            setOrdernum(orderDishFragment.responseOrd.getData().getOrdernum()).
                            setOrdnum(orderDishFragment.responseOrd.getData().getOrdnum()).
                            setYm(orderDishFragment.responseOrd.getData().getYm()).
                            setWritedishs(orderDishFragment.orders.getWriteDish()).
                            setAllremarks(orderDishFragment.orders.getAllMethod())
                            .setStorename(orderDishFragment.responseOrd.getData().getStorename())
                            .setType_txt(orderDishFragment.responseOrd.getData().getType_txt())
                    );
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

    }

    @Override
    public void showProg(String mesg) {

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
    public void sendSucc() {
        if (fragmentList.get(fragmentList.size() - 2) instanceof OrderDishFragment) {
            popBackFragment();
            popBackFragment();
        } else {
            popBackFragment();
            popBackFragment();
            popBackFragment();
        }
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
}
