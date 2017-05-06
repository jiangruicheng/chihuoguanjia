package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.DcListAdapter;
import com.cndll.chgj.adapter.DeshListAdapter;
import com.cndll.chgj.adapter.OnItemClickLister;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.view.OrderView;
import com.cndll.chgj.util.LinearPagerLayoutManager;
import com.cndll.chgj.util.PagerLayoutManager;
import com.cndll.chgj.util.PagingScrollHelper;
import com.cndll.chgj.util.StringHelp;
import com.cndll.chgj.weight.KeyWeight;
import com.cndll.chgj.weight.OrderInfo;
import com.cndll.chgj.weight.OrderItemMesg;

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
    @BindView(R.id.info)
    Button info;

    @OnClick(R.id.info)
    void onclick_info() {
        if (orders != null && orders.getAll() != null) {
            replaceFragmentAddToBackStack(OrderRequestFragment.newInstance(null, null).setOrderList(orders), null);
        } else {
            replaceFragmentAddToBackStack(OrderRequestFragment.newInstance(null, null), null);
        }
    }

    @BindView(R.id.other)
    Button other;
    @BindView(R.id.dazhe)
    Button dazhe;
    @BindView(R.id.pay)
    Button pay;
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
                    numberEdit.setText(s);

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

    private String tableId;
    // private List<RequestOrder.ItemsBean> order = new ArrayList<>();
    private OrderInfo orderInfolayout;
    private OrderItemMesg orderItemMesglayout;

    @Override
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
        PagerLayoutManager horizontalPageLayoutManager = new PagerLayoutManager(getContext(), 5, 3);
        deshList.setLayoutManager(horizontalPageLayoutManager);
        LinearPagerLayoutManager pagerLayoutManaer = new LinearPagerLayoutManager(getContext(), 5, 1);
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
                    order.setCount(1).setItemsBean(new RequestOrder.ItemsBean().setDish_money(Float.valueOf(deshListAdapter.getMitems().get(position).getPrice())).setDish_name(deshListAdapter.getMitems().get(position).getName()).setId(deshListAdapter.getMitems().get(position).getId()));
                    orders.setOrders(deshListAdapter.getMitems().get(position).getId(), order);

                }

                orders.setCurrPosition(deshListAdapter.getMitems().get(position).getId());
                numberEdit.setText(orders.getOrder(orders.getCurrPosition()).getCount() + "");
                setOrderInfolayout(deshListAdapter.getMitems().get(position).getId());

            }
        });
        presenter.getDcList(new RequestPrintList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
    }

    private void setOrderInfolayout(String id) {
        if (orderItemMesglayout != null)
            orderItemMesglayout.setPrice(orders.getOrder(id).getAllPrice() + "").
                    setName(orders.getOrder(id).getDeshName() + orders.getOrder(id).getDeshPrice()).setMethod(orders.getOrder(id).getMethodName() + orders.getOrder(id).getMethodPrice());
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

        public boolean Iscontan(String id) {
            return orders.containsKey(id);
        }

        public Order getOrder(String id) {
            return orders.get(id);
        }

        public List<Order> getAll() {
            return new ArrayList<>(orders.values());
        }

        public static class Order {
            public Order setCount(float count) {
                this.count = count;
                return this;
            }


            public float getCount() {
                return count;
            }

            public boolean isSend() {
                return isSend;
            }

            public void setSend(boolean send) {
                isSend = send;
            }

            boolean isSend = false;
            float count = 1;
            float giveCount;

            public Order setItemsBean(RequestOrder.ItemsBean itemsBean) {
                this.itemsBean = itemsBean;
                return this;
            }

            RequestOrder.ItemsBean itemsBean = new RequestOrder.ItemsBean();
            List<RequestAddMethod> methods = new ArrayList<>();

            public Order setGiveCount(float giveCount) {
                this.giveCount = giveCount;
                return this;
            }

            public String getDeshName() {
                return itemsBean.getDish_name();
            }

            public float getDeshPrice() {
                return count * itemsBean.getDish_money();
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
                return giveCount * itemsBean.getDish_money();
            }

            public float getAllPrice() {
                float price = 0;
                for (int i = 0; i < methods.size(); i++) {
                    price = price + Float.valueOf(methods.get(i).getPrice());
                }
                return price + itemsBean.getDish_money() * count;

            }
        }
    }

}
