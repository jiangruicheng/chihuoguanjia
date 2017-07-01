package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.TimePick;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrintReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrintReportFragment extends BaseFragment {
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
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.today)
    Button today;
    @BindView(R.id.yesterday)
    Button yesterday;
    @BindView(R.id.defined)
    Button defined;
    @BindView(R.id.print)
    Button print;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PrintReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrintReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrintReportFragment newInstance(String param1, String param2) {
        PrintReportFragment fragment = new PrintReportFragment();
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

    String url;
    String baseUrl = AppRequest.ACCOUNTURL;
    String stm, etm;
    int year;
    int month;
    int day;
    Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_print_report, container, false);
        unbinder = ButterKnife.bind(this, view);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 5) {
            calendar.add(Calendar.DATE, -1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        stm = etm = year + "-" + month + "-" + day;
        title.setText("报表查询");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour < 5) {
                    calendar.add(Calendar.DATE, -1);
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH) + 1;
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                } else {
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                }
                stm = etm = year + "-" + month + "-" + day;
                setUrl(stm, etm);
            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour < 5) {
                    calendar.add(Calendar.DATE, -1);
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH) + 1;
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                } else {
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                }
                stm = etm = year + "-" + month + "-" + day;
                setUrl(stm, etm);
            }
        });
        defined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelect timeSelect = new TimeSelect();
                timeSelect.init();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mParam1) {
                    case "day":
                        AppRequest.getAPI().printDayReport(AppMode.getInstance().getUid(), AppMode.getInstance().getMid(),
                                stm, etm, AppMode.getInstance().getPrint_code()).subscribeOn(Schedulers.io()).
                                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
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
                                    Toast.makeText(getContext(), "打印成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case "all":
                        AppRequest.getAPI().printAllReport(AppMode.getInstance().getUid(), AppMode.getInstance().getMid(), stm, etm, AppMode.getInstance().getPrint_code()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
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
                                    Toast.makeText(getContext(), "打印成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        break;
                }
            }
        });
        setUrl(stm, stm);
        return view;
    }

    private void setUrl(String stm, String etm) {
        switch (mParam1) {
            case "day":
                url = String.format(baseUrl + "Web/dayprint?mid=%s&uid=%s&stm=%s&etm=%s", AppMode.getInstance().getMid(), AppMode.getInstance().getUid(), stm, etm);
                break;
            case "all":
                url = String.format(baseUrl + "Web/dishprint?mid=%s&uid=%s&stm=%s&etm=%s", AppMode.getInstance().getMid(), AppMode.getInstance().getUid(), stm, etm);
                break;
        }
        webview.loadUrl(url);
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
      /*  if (context instanceof OnFragmentInteractionListener) {
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
                    timePick.showPopView(defined);
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
                    setUrl(stm, etm);
                    popUpViewUtil.dismiss();
                }
            });
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePick timePick = new TimePick(getActivity());
                    timePick.showPopView(defined);
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
            popUpViewUtil.popListWindow(defined, view, popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 5, Gravity.CENTER, null);
        }
    }
}
