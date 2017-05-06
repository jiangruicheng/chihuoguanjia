package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.weight.OrderInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderRequestFragment extends Fragment {
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
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.pay)
    Button pay;
    @BindView(R.id.dazhe)
    Button dazhe;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderDishFragment.Orders getOrderList() {
        return order;
    }

    public OrderRequestFragment setOrderList(OrderDishFragment.Orders order) {
        this.order = order;
        if (adapter != null) {
            adapter.setOrderList(order);
        }
        return this;
    }

    private OrderDishFragment.Orders order;

    public OrderRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderRequestFragment newInstance(String param1, String param2) {
        OrderRequestFragment fragment = new OrderRequestFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new OrderListAdapter();
        if (order != null) {
            adapter.setOrderList(order);
        }
        orderListView.setAdapter(adapter);
        orderInfo = new OrderInfo();
        orderInfo.init(orderinfolayout);
        orderInfo.setMesg(order);
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
            if (orderList != null) {
                return orderList.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_desh_info, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.numberEdit.setText(orderList.get(position).getCount() + "");
            viewHolder.deshName.setText(orderList.get(position).getDeshName() + orderList.get(position).getDeshPrice());
            viewHolder.deshMethod.setText(orderList.get(position).getMethodName() + orderList.get(position).getMethodPrice());
            viewHolder.sendstatue.setVisibility(View.VISIBLE);
            if (orderList.get(position).isSend) {
                viewHolder.sendstatue.setText("已送单");
                viewHolder.sendstatue.setTextColor(Color.rgb(0, 110, 255));
            } else {
                viewHolder.sendstatue.setText("未送单");
                viewHolder.sendstatue.setTextColor(Color.RED);
            }
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
}
