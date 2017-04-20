package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.RegisterListAdpater;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.presenter.RegisterPresenter;
import com.cndll.chgj.mvp.view.RegisterView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.OptionPickView;

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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements RegisterView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    @BindView(R.id.parent)
    LinearLayout parent;
    @BindView(R.id.register)
    Button register;

    @OnClick(R.id.register)
    void onclick_register() {
        popview();
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    private ItemTouchHelper mItemTouchHelper;
    RegisterListAdpater adapter = new RegisterListAdpater(getActivity(), new OnStartDragListener() {
        @Override
        public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
            mItemTouchHelper.startDrag(viewHolder);
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        unbinder = ButterKnife.bind(this, view);
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(list);
        return view;
    }

    // TODO:     Rename method, update argument and hook method into UI event
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

    private void popview() {
        final ViewGroup view = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.popview_register_info, parent, false);
        final PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        Spinner spinner = (Spinner) view.findViewById(R.id.type);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, new String[]{"1234", "121314", "12131414"});
        Button cancel, save, delete;
        delete = (Button) view.findViewById(R.id.delete);
        delete.setVisibility(View.GONE);
        cancel = (Button) view.findViewById(R.id.cancel);
        save = (Button) view.findViewById(R.id.save);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpViewUtil.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionPickView == null) {
                    presenter.getArea();
                } else {
                    optionPickView.show();
                }
            }
        });
        spinner.setAdapter(arrayAdapter);
        popUpViewUtil.popListWindow(list, view,
                popUpViewUtil.getWindowManager(getContext()).
                        getDefaultDisplay().getWidth() * 19 / 20, popUpViewUtil.getWindowManager(getContext()).
                        getDefaultDisplay().getHeight() * 3 / 5, Gravity.CENTER, null);
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

    private RegisterPresenter presenter;

    @Override
    public void setPresenter(RegisterPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void loadListView() {

    }

    private OptionPickView optionPickView;

    @Override
    public void showArea(List<String> item0, List<List<String>> item1, ResponseArea responseArea) {
        if (optionPickView == null) {
            optionPickView = new OptionPickView(getActivity(), R.layout.dialog_opitionpick);
            optionPickView.setLooper(false, false);
            optionPickView.setOptionItem(item0, item1);
        }
        optionPickView.show();

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
