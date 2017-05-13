package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.presenter.impl.NoteImpl;
import com.cndll.chgj.mvp.presenter.impl.OrderImpl;
import com.cndll.chgj.weight.KeyWeight;
import com.cndll.chgj.weight.OrderInfo;
import com.cndll.chgj.weight.OrderItemMesg;
import com.cndll.chgj.weight.PopOrderRequest;

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
 * Use the {@link OrderInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderInfoFragment extends BaseFragment {
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

    @OnClick(R.id.send)
    void onclick_send() {
        replaceFragmentAddToBackStack(SendFragment.newInstance(null, null).setOrderDishFragment((OrderDishFragment) fragmentList.get(fragmentList.size() - 2)), new OrderImpl());
    }

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

    private void popUpkey(int mode, String hint, KeyWeight.OnKeyClick onKeyClick) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.init(getContext(), orderListView, 2);
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

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_request, container, false);
        unbinder = ButterKnife.bind(this, view);
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
                popUpkey(0, "", new KeyWeight.OnKeyClick() {
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
            if (order.writeDish != null) {
                return order.getOrders().size() + order.writeDish.size();
            } else {
                return order.getOrders().size();
            }
//            return 0;
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
}
