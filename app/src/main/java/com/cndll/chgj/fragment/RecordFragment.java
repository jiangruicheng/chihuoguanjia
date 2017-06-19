package com.cndll.chgj.fragment;


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
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRecord;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends BaseFragment {
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
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
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

    RecordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("缴费记录");
        adapter = new RecordAdapter();
        list.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
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
                    adapter.setMitems(((ResponseRecord) baseResponse).getData().getPaylist());
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class RecordAdapter extends BaseAdapter {
        public List<ResponseRecord.DataBean.PaylistBean> getMitems() {
            return mitems;
        }

        public void setMitems(List<ResponseRecord.DataBean.PaylistBean> mitems) {
            this.mitems = mitems;
            notifyDataSetChanged();
        }

        private List<ResponseRecord.DataBean.PaylistBean> mitems;

        public int getCount() {
            if (mitems != null) {
                return mitems.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return mitems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.info.setText("续费" + mitems.get(position).getName());
            viewHolder.price.setText("-￥" + mitems.get(position).getMoney());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(mitems.get(position).getCre_tm());
            Date date = new Date(lt);
            viewHolder.time.setText(simpleDateFormat.format(date));
            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.info)
            TextView info;
            @BindView(R.id.time)
            TextView time;
            @BindView(R.id.price)
            TextView price;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
