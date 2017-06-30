package com.cndll.chgj.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestVerify;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseVerify;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.util.StringHelp;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindPasswordFragment extends BaseFragment {
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
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.verify)
    EditText verify;
    @BindView(R.id.get_verify)
    Button getVerify;

    @OnClick(R.id.get_verify)
    void onclick_getverify() {
        getVerify.setEnabled(false);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                handler.sendMessage(handler.obtainMessage());
            }
        };
        timer.schedule(task, 0, 1000);
        AppRequest.getAPI().getVerify(new RequestVerify().setTel(username.getText().toString())).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseView) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "获取失败", Toast.LENGTH_SHORT).show();
                super.onError(e);
                if (timer != null) {
                    timer.cancel();
                    getVerify.setText("获取");
                    getVerify.setEnabled(true);
                }
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse instanceof ResponseVerify) {
                    if (baseResponse.getCode() == 1) {
                        sverify = String.valueOf(((ResponseVerify) baseResponse).getData());
                    }

                }
            }
        });
    }

    @BindView(R.id.sure)
    Button sure;

    @OnClick(R.id.sure)
    void onclick_sure() {
        if (!StringHelp.isNumeric(password.getText().toString())) {
            showToast("请输入纯数字密码");
            return;
        }
        if (sverify != null && sverify.equals(verify.getText().toString()))
            AppRequest.getAPI().updatePassword(username.getText().toString(), password.getText().toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseView) {
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
                        replaceFragmentAddToBackStack(SuccessFragment.newInstance("密码重置", "密码重置成功"), null);
                        AppMode.getInstance().setUid("3");
                        AppMode.getInstance().setLoading(false);
                        AppMode.getInstance().setMid("3");
                        AppMode.getInstance().setToken(null);
                    }
                }
            });
    }

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String sverify;

    Timer timer;
    TimerTask task;
    int time = 60;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (getVerify == null) {
                return;
            }
            if (time != 0) {
                getVerify.setText(time + "");
                time--;
            } else {
                getVerify.setEnabled(true);
                getVerify.setText("获取");
            }
        }
    };
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

    public FindPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindPasswordFragment newInstance(String param1, String param2) {
        FindPasswordFragment fragment = new FindPasswordFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_password2, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("修改密码");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
