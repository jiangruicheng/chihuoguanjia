package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetSeting;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.weight.ButtonSwitch;

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
 * Use the {@link SetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetingFragment extends BaseFragment {
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


    Unbinder unbinder;
    @BindView(R.id.payapp)
    TextView payapp;

    @OnClick(R.id.payapp)
    void onclick_payapp() {
        if (AppMode.getInstance().isLoading()) {
            replaceFragmentAddToBackStack(PayAppFragment.newInstance(null, null), null);
        } else {
            baseShowMesg("请先登录再进行缴费操作", payapp);
        }
    }

    @BindView(R.id.save)
    Button save;

    @OnClick(R.id.save)
    void onclick_save() {
        String printset;
        if (printSet.isLeft()) {
            printset = "1";
        } else {
            printset = "2";
        }
        AppRequest.getAPI().setting(AppMode.getInstance().getUid(),
                AppMode.getInstance().getMid(),
                backSet.isLeftInt() + "", printset,
                discountSet.isLeftInt() + "").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseView) {
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
                    Toast.makeText(getContext(), "sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private BaseView baseView = new BaseView() {
        @Override
        public void showMesg(String mesg) {

        }

        @Override
        public void showProg(String mesg) {

        }

        @Override
        public void setPresenter(BasePresenter presenter) {

        }
    };
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SetingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetingFragment newInstance(String param1, String param2) {
        SetingFragment fragment = new SetingFragment();
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

    private ButtonSwitch backSet, printSet, discountSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seting, container, false);
        unbinder = ButterKnife.bind(this, view);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        View view1 = view.findViewById(R.id.back_set);
        View view2 = view.findViewById(R.id.print_set);
        View view3 = view.findViewById(R.id.discount_set);
        backSet = new ButtonSwitch();
        printSet = new ButtonSwitch();
        discountSet = new ButtonSwitch();
        backSet.setText("是", "否");
        backSet.setTextColor(Color.WHITE, Color.GRAY);
        backSet.setRightBackground(R.drawable.shape_dialog_fillet_solid);
        backSet.setLeftBackground(R.drawable.shape_fillet_solid_blue);
        printSet.setText("一菜一单", "一桌一单");
        printSet.setTextColor(Color.WHITE, Color.GRAY);
        printSet.setRightBackground(R.drawable.shape_dialog_fillet_solid);
        printSet.setLeftBackground(R.drawable.shape_fillet_solid_blue);
        discountSet.setText("是", "否");
        discountSet.setTextColor(Color.WHITE, Color.GRAY);
        discountSet.setRightBackground(R.drawable.shape_dialog_fillet_solid);
        discountSet.setLeftBackground(R.drawable.shape_fillet_solid_blue);
        backSet.init(view1);
        printSet.init(view2);
        discountSet.init(view3);
        backSet.setLeft(true);
        printSet.setLeft(true);
        discountSet.setLeft(true);
        title.setText("高级设置");
        AppRequest.getAPI().getSetting(AppMode.getInstance().getUid(), AppMode.getInstance().getMid()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseView) {
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
                    ResponseGetSeting responseGetSeting = ((ResponseGetSeting) baseResponse);
                    if (IsTrue(responseGetSeting.getData().getTcis_print())) {
                        backSet.setLeft(true);
                    } else {
                        backSet.setLeft(false);
                    }
                    if (IsTrue(responseGetSeting.getData().getDis_zk())) {
                        discountSet.setLeft(true);
                    } else {
                        discountSet.setLeft(false);
                    }
                    if (IsTrue(responseGetSeting.getData().getCd_method())) {
                        printSet.setLeft(true);
                    } else {
                        printSet.setLeft(false);
                    }
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

    private boolean IsTrue(String s) {
        if (s.equals("1")) {
            return true;
        } else {
            return false;
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
}
