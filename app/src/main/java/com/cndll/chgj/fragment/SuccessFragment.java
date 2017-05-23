package com.cndll.chgj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.util.StringHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuccessFragment extends BaseFragment {
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
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;
    @BindView(R.id.info_color_black)
    TextView infoColorBlack;
    @BindView(R.id.info_color_red)
    TextView infoColorRed;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SuccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuccessFragment newInstance(String param1, String param2) {
        SuccessFragment fragment = new SuccessFragment();
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

    public String getPrice() {
        return price;
    }

    public SuccessFragment setPrice(String price) {
        this.price = price;
        return this;
    }

    private String price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_success, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText(mParam1);
        if (StringHelp.isFloat(mParam2)) {
            infoColorBlack.setText("收款成功");
            infoColorRed.setText("￥" + mParam2);
            button.setText("完成");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fragmentList.get(fragmentList.size() - 3) instanceof OrderInfoFragment) {
                        popBackFragment();
                        popBackFragment();
                        popBackFragment();
                        popBackFragment();
                    } else {
                        popBackFragment();
                        popBackFragment();
                        popBackFragment();
                    }
                }
            });

        } else {
            infoColorBlack.setVisibility(View.GONE);
            infoColorRed.setText(mParam2);
            button.setText("重新登录");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AppMode.getInstance().isLoading()) {
                        popBackFragment();
                        popBackFragment();
                    } else {
                        popBackFragment();
                        popBackFragment();
                    }
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppMode.getInstance().isLoading()) {
                    popBackFragment();
                    popBackFragment();
                } else {
                    popBackFragment();
                    popBackFragment();
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

    public interface DoThing {
        void doting();
    }
}
