package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.DeshListAdapter;
import com.cndll.chgj.util.LinearPagerLayoutManager;
import com.cndll.chgj.util.PagerLayoutManager;
import com.cndll.chgj.util.PagingScrollHelper;
import com.cndll.chgj.util.PopUpViewUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static butterknife.ButterKnife.bind;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderDishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDishFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.desh_menue_list)
    RecyclerView deshMenueList;
    @BindView(R.id.desh_list)
    RecyclerView deshList;
    Unbinder unbinder;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_tow)
    LinearLayout titleTow;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.yaoqiu)
    TextView yaoqiu;
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
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.info)
    Button info;
    @BindView(R.id.other)
    Button other;
    @BindView(R.id.dazhe)
    Button dazhe;
    @BindView(R.id.pay)
    Button pay;
    @BindView(R.id.number_edit)
    TextView numberEdit;
    View key;
    TextView show;
    TextView abcKey;
    TextView numberKey;

    @OnClick(R.id.number_edit)
    void onclick_number_edit() {
        popUpkey(0, "");
    }

    PopUpViewUtil popUpViewUtil;

    private void popUpkey(int mode, String hint) {
        if (popUpViewUtil == null)
            popUpViewUtil = PopUpViewUtil.getInstance();
        if (key == null) {
            key = LayoutInflater.from(getContext()).inflate(R.layout.popview_key, null, false);
            setOnclick(key);
        }
        int[] locations = new int[2];
        numberEdit.getLocationOnScreen(locations);
        locations[1] = locations[1] - popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 2;
        popUpViewUtil.popListWindow(numberEdit, key,
                popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(),
                popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 2,
                Gravity.NO_GRAVITY, locations);
        popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                if (showbuffer.length() > 0) {
                    showbuffer.delete(0, showbuffer.length());
                    popUpViewUtil = null;
                }
            }
        });
        // ((TextView)key.findViewById(R.id.tran)).setText(".");
        if (show == null) {
            show = (TextView) key.findViewById(R.id.show);
        }
        show.setText(showbuffer.toString());

        //setCap(key, cap);
    }

    private void setOnclick(View view) {
        if (!(view instanceof ViewGroup)) {
            (view).setOnClickListener(listener);
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setOnclick(((ViewGroup) view).getChildAt(i));
            }
        }
    }

    private void setCap(View view, boolean iscap) {
        if (view instanceof TextView) {
            ((TextView) view).setAllCaps(iscap);
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setCap(((ViewGroup) view).getChildAt(i), iscap);
            }
        }
    }

    boolean cap = false;
    private StringBuffer showbuffer = new StringBuffer();
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {
                if (v.getId() == R.id.acp) {
                    setCap(key, cap);
                    cap = !cap;
                    return;
                }
                if (v.getId() == R.id.key_exit) {
                    if (popUpViewUtil != null) {
                        popUpViewUtil.dismiss();
                    }
                    return;
                }
                if (v.getId() == R.id.show) {
                    return;
                }
                if (v.getId() == R.id.delete_abc || v.getId() == R.id.delete_number) {
                    if (showbuffer.length() > 0) {
                        showbuffer.deleteCharAt(showbuffer.length() - 1);
                        show.setText(showbuffer.toString());
                    }
                    return;
                }
                if (v.getId() == R.id.tran_number) {
                    key.findViewById(R.id.number_key).setVisibility(View.VISIBLE);
                    key.findViewById(R.id.abc_key).setVisibility(View.GONE);
                    return;
                }
                if (v.getId() == R.id.tran && ((TextView) v).getText().equals("ABC")) {
                    key.findViewById(R.id.number_key).setVisibility(View.GONE);
                    key.findViewById(R.id.abc_key).setVisibility(View.VISIBLE);
                    return;
                }
                if (show == null) {
                    show = (TextView) key.findViewById(R.id.show);
                }
                showbuffer.append(((TextView) v).getText());
                show.setText(showbuffer.toString());
            }
        }
    };
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_dish, container, false);
        unbinder = bind(this, view);
        initlistview();

        return view;
    }

    private void initlistview() {
        DeshListAdapter deshListAdapter = new DeshListAdapter();
        deshList.setAdapter(deshListAdapter);
        deshMenueList.setAdapter(new DeshListAdapter());

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
