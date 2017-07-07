package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.CaipinFunctionListAdpater;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetMethodList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateMethod;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.presenter.DeshMethodPresenter;
import com.cndll.chgj.mvp.view.DeshMethodView;
import com.cndll.chgj.util.PopUpViewUtil;

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
 * Use the {@link CaiPinFunctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaiPinFunctionFragment extends BaseFragment<CaipinFunctionListAdpater> implements DeshMethodView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search_caipin)
    EditText searchCaipin;
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_tow)
    LinearLayout titleTow;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.add_method)
    Button addMethod;

    @OnClick(R.id.add_method)
    void onclick_addMethod() {
        DeshMethod deshMethod = new DeshMethod();
        deshMethod.init(-1);
        deshMethod.show();
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CaiPinFunctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaiPinFunctionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaiPinFunctionFragment newInstance(String param1, String param2) {
        CaiPinFunctionFragment fragment = new CaiPinFunctionFragment();
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

    /*private ItemTouchHelper mItemTouchHelper;*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cai_pin_function, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("做法情况");
        adapter = new CaipinFunctionListAdpater(getContext(), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        setListViewAdapter(list);
        adapter.setOnItemClick(new ListAdapter.OnItemsClick() {
            @Override
            public void onReEidetClick(View view, int position) {
                DeshMethod deshMethod = new DeshMethod();
                deshMethod.init(position);
                deshMethod.show();
            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });
        presenter.getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
        searchCaipin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    @Override
    public void showMesg(String mesg) {
        baseShowMesg(mesg, searchCaipin);
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
        showToast(s);
    }

    DeshMethodPresenter presenter;

    @Override
    public void setPresenter(DeshMethodPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void showMethodList(List<ResponseMethod.DataBean> dataBeen) {
        adapter.setMitems(dataBeen);
    }

    @Override
    public void succGetList() {

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

    private class DeshMethod {
        private PopUpViewUtil popUpViewUtil;
        private View view;
        private EditText name, price;
        private Button cancel, save, delete;
        private int position;

        public void init(final int position) {
            this.position = position;

            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_desh_method, null, false);
            name = (EditText) view.findViewById(R.id.edit_add_cailei);
            save = (Button) view.findViewById(R.id.save);
            delete = (Button) view.findViewById(R.id.delete);
            cancel = (Button) view.findViewById(R.id.cancel);
            price = (EditText) view.findViewById(R.id.add_price);
            price.setHint("0");
            if (position > -1) {
                price.setText(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getPrice());
                name.setText(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getName());
            }
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUpViewUtil.dismiss();
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == -1) {
                        presenter.addDeshMethod(new RequestAddMethod().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(name.getText().toString()).setPrice(price.getText().toString()));
                        name.setText("");
                        price.clearComposingText();
                    } else {
                        presenter.updateDeshMethod(new RequestUpdateMethod().setId(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getId()).setName(name.getText().toString()).setPrice(price.getText().toString()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position > -1) {
                        presenter.deleteDeshMethod(new RequestDeleteMethod().setId(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getId()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
        }

        public void show() {
            popUpViewUtil.popListWindow(addMethod,
                    view,
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getHeight() / 10 * 3, Gravity.CENTER, null);
            showInput(name);
        }

    }
}
