package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRecord;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.util.StringHelp;

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
 * Use the {@link PayAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayAppFragment extends BaseFragment {
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

    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.overtime)
    TextView overtime;
    @BindView(R.id.list)
    ListView list;

    @OnClick(R.id.pay)
    void onclick_pay() {
        if (money == "") {

        } else {
            gotoWebview();
        }
    }

    Unbinder unbinder;
/*
    @BindView(R.id.bottons)
    LinearLayout bottons;
*/

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PayAppFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayAppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayAppFragment newInstance(String param1, String param2) {
        PayAppFragment fragment = new PayAppFragment();
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //  setTextStyle(bottons, v.getId());
           /* switch (v.getId()) {
                case R.id.one:
                    month = 1;
                    name = "一个月";
                    break;
                case R.id.three:
                    month = 3;
                    name = "三个月";
                    break;
                case R.id.six:
                    month = 6;
                    name = "六个月";
                    break;
                case R.id.year:
                    month = 12;
                    name = "一年";
                    break;
                case R.id.all:
                    month = 70 * 12;
                    name = "70年";
                    break;
            }*/
            //  pay.setText("去支付（" + month * money + ")");
        }
    };

    private void setOnClick(View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                ((ViewGroup) view).getChildAt(i).setOnClickListener(onClickListener);
            }
        }
    }

    private void setTextStyle(View view, @IdRes int id) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                if (((ViewGroup) view).getChildAt(i).getId() == id) {
                    ((TextView) ((ViewGroup) view).getChildAt(i)).setTextColor(Color.WHITE);
                    ((ViewGroup) view).getChildAt(i).setBackgroundResource(R.drawable.shape_button_yellow);
                } else {
                    ((TextView) ((ViewGroup) view).getChildAt(i)).setTextColor(Color.rgb(255, 0xd1, 00));
                    ((ViewGroup) view).getChildAt(i).setBackgroundResource(R.drawable.shape_fillet_button_yellow);
                }
            }
        }
    }

    BaseView baseView = new BaseView() {
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
    };
    ChargeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        // setOnClick(bottons);
        adapter = new ChargeAdapter();
        list.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("记录");
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentAddToBackStack(RecordFragment.newInstance(null, null), null);
            }
        });
        title.setText("软件缴费");
        AppRequest.getAPI().record(AppMode.getInstance().getMid()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseView) {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 1) {
                    ResponseRecord responseRecord = (ResponseRecord) baseResponse;
                    storeName.setText(responseRecord.getData().getStoreinfo().getName());
                    overtime.setText(responseRecord.getData().getStoreinfo().getCode() + "\n" + "到期时间" + responseRecord.getData().getStoreinfo().getOvertm_tx());
                    adapter.setMitems(((ResponseRecord) baseResponse).getData().getPaylist());
                }
            }
        });

        return view;
    }

    String money = "", month;
    String name = "";

    private void gotoWebview() {
        String url = String.format(
                AppRequest.ACCOUNTURL + "web/spay?money=%s&uid=%s&mid=%s&month=%s&name=%s",
                money,
                AppMode.getInstance().getUid(),
                AppMode.getInstance().getMid(),
                month, name);
        replaceFragmentAddToBackStack(WebViewFragment.newInstance(url, "软件缴费"), null);

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

    public class ChargeAdapter extends BaseAdapter {
        public List<ResponseRecord.DataBean.PaylistBean> getMitems() {
            return mitems;
        }

        private int select = -1;

        public void setMitems(List<ResponseRecord.DataBean.PaylistBean> mitems) {
            this.mitems = mitems;
            notifyDataSetChanged();
        }

        private List<ResponseRecord.DataBean.PaylistBean> mitems;

        @Override
        public int getCount() {
            if (mitems != null) {
                return mitems.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mitems != null) {
                return mitems.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_storecharge, parent, false);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            if (position == select) {
                viewHolder.one.setTextColor(Color.WHITE);
                viewHolder.one.setBackgroundResource(R.drawable.shape_button_yellow);
            }
            viewHolder.one.setText(mitems.get(position).getName());
            viewHolder.one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    month = (mitems.get(position).getMonth());
                    name = mitems.get(position).getName();
                    money = (mitems.get(position).getMoney());
                    pay.setText("去支付（" + StringHelp.float2Int(money + "") + ")");
                    select = position;
                    notifyDataSetChanged();

                }
            });
            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.one)
            TextView one;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
