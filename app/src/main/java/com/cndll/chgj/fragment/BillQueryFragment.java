package com.cndll.chgj.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetBillList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetBillList;
import com.cndll.chgj.mvp.presenter.BIllPresenter;
import com.cndll.chgj.mvp.view.BillView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.TimePick;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillQueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillQueryFragment extends BaseFragment implements BillView {
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
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.today)
    Button today;

    @OnClick(R.id.today)
    void onclic_today() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        etm = stm = year + "-" + month + "-" + day;
        billInfo.setText("账单信息      日期：" + stm + "——" + etm);
        presenter.getBillList(new RequestGetBillList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setEtm(etm).setStm(stm));
    }

    @BindView(R.id.yesterday)
    Button yesterday;

    @OnClick(R.id.yesterday)
    void onclic_yesterday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH) - 1;
        etm = stm = year + "-" + month + "-" + day;
        billInfo.setText("账单信息      日期：" + stm + "——" + etm);
        presenter.getBillList(new RequestGetBillList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setEtm(etm).setStm(stm));
    }

    @BindView(R.id.user_defined)
    Button userDefined;

    @OnClick(R.id.user_defined)
    void onclic_defined() {
        TimeSelect timeSelect = new TimeSelect();
        timeSelect.init();
    }

    private String stm, etm;
    Unbinder unbinder;
    @BindView(R.id.bill_condition)
    EditText billCondition;
    @BindView(R.id.bill_info)

    TextView billInfo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BillQueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillQueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillQueryFragment newInstance(String param1, String param2) {
        BillQueryFragment fragment = new BillQueryFragment();
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

    BillListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_query, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new BillListAdapter();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = String.format("http://dc.idc.zhonxing.com/Web/orderprint?id=%s", adapter.getItems().get(position).getId());
                replaceFragmentAddToBackStack(BillItemFragment.newInstance(url, adapter.getItems().get(position).getId()), null);
            }
        });
        title.setText("账单查询");
        billCondition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getBillList(new RequestGetBillList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(s.toString()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        list.setAdapter(adapter);
        presenter.getBillList(new RequestGetBillList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
        return view;
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

    BIllPresenter presenter;

    @Override
    public void setPresenter(BIllPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void showBillList(List<ResponseGetBillList.DataBean> dataBeen) {
        adapter.setItems(dataBeen);
    }

    public static class BillListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (items != null) {
                return items.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (items != null) {
                return items.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        public List<ResponseGetBillList.DataBean> getItems() {
            return items;
        }

        public void setItems(List<ResponseGetBillList.DataBean> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        private List<ResponseGetBillList.DataBean> items;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_billlist, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);

            if (items.get(position).getType() != null && !items.get(position).getType().equals("1")) {
                viewHolder.payStatue.setTextColor(Color.rgb(0XDC, 0X53, 0X01));
                viewHolder.tablename.setTextColor(Color.rgb(0XDC, 0X53, 0X01));
                viewHolder.time.setTextColor(Color.rgb(0XDC, 0X53, 0X01));
                viewHolder.payStatue.setText(items.get(position).getType_txt());
            } else {
                viewHolder.payStatue.setText(items.get(position).getType_txt() + " " + items.get(position).getSsmoney());
            }
            viewHolder.time.setText(items.get(position).getOrdnum());
            viewHolder.tablename.setText(items.get(position).getTabname());
            return convertView;
        }


        public static class ViewHolder {
            @BindView(R.id.time)
            TextView time;
            @BindView(R.id.pay_statue)
            TextView payStatue;
            @BindView(R.id.tablename)
            TextView tablename;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class TimeSelect {
        View view;
        PopUpViewUtil popUpViewUtil;
        @BindView(R.id.start)
        TextView start;
        @BindView(R.id.end)
        TextView end;
        @BindView(R.id.sure)
        Button sure;
        Unbinder unbinder;

        public void init() {
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_timeselecte, null, false);
            unbinder = ButterKnife.bind(this, view);
            popUpViewUtil = PopUpViewUtil.getInstance();
            popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
                @Override
                public void onDismiss() {
                    unbinder.unbind();
                }
            });
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePick timePick = new TimePick(getActivity());
                    timePick.showPopView(userDefined);
                    timePick.setOnTimePickSlect(new TimePick.OnTimePickSlect() {
                        @Override
                        public void onSelect(int year, int moth, int day) {
                            start.setText(year + "-" + moth + "-" + day);
                            stm = year + "-" + moth + "-" + day;
                        }

                        @Override
                        public void onCancel() {

                        }
                    });

                }
            });
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    billInfo.setText("账单信息      日期：" + stm + "——" + etm);
                    presenter.getBillList(new RequestGetBillList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setEtm(etm).setStm(stm));
                    popUpViewUtil.dismiss();
                }
            });
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePick timePick = new TimePick(getActivity());
                    timePick.showPopView(userDefined);
                    timePick.setOnTimePickSlect(new TimePick.OnTimePickSlect() {
                        @Override
                        public void onSelect(int year, int moth, int day) {
                            end.setText(year + "-" + moth + "-" + day);
                            etm = year + "-" + moth + "-" + day;
                        }

                        @Override
                        public void onCancel() {

                        }
                    });

                }
            });
            popUpViewUtil.popListWindow(userDefined, view, popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 5, Gravity.CENTER, null);
        }
    }
}
