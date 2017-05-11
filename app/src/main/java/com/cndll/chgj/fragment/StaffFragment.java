package com.cndll.chgj.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateStaff;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStaffList;
import com.cndll.chgj.mvp.presenter.StaffPresenter;
import com.cndll.chgj.mvp.view.StaffView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.SelectView;

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
 * Use the {@link StaffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffFragment extends BaseFragment implements StaffView {
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
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.register)
    Button register;


    @OnClick(R.id.register)
    void onclick_register() {
        final PopviewStaff popviewStaff = new PopviewStaff();
        popviewStaff.show();
        title.setText("我的员工");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        popviewStaff.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popviewStaff.dismiss();
            }
        });
        popviewStaff.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                if (popviewStaff.isDicount.isSelect()) {
                    stringBuilder.append("1,");
                }

                if (popviewStaff.isGive.isSelect()) {
                    stringBuilder.append("2,");
                }
                if (popviewStaff.isRetrun.isSelect()) {
                    stringBuilder.append("3,");
                }
                if (popviewStaff.isOrder.isSelect()) {
                    stringBuilder.append("4,");
                }
                if (popviewStaff.isExcl.isSelect()) {
                    stringBuilder.append("5");
                }
                presenter.addStaff(new RequestAddStaff().setMid(AppMode.getInstance().getMid()).
                        setPassword(popviewStaff.staffPassword.getText().toString()).
                        setQx(stringBuilder.toString()).
                        setTel(popviewStaff.staffId.getText().toString()).
                        setUsername(popviewStaff.staffName.getText().toString()));
            }
        });
    }

    @BindView(R.id.parent)
    LinearLayout parent;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaffFragment newInstance(String param1, String param2) {
        StaffFragment fragment = new StaffFragment();
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

    StaffAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new StaffAdapter();
        list.setAdapter(adapter);
        presenter.getStaffList(new RequestPrintList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
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

    @Override
    public void showMesg(String mesg) {

    }

    @Override
    public void showProg(String mesg) {

    }

    StaffPresenter presenter;

    @Override
    public void setPresenter(StaffPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void showStaffList(List<ResponseGetStaffList.DataBean> responseAddStaff) {
        adapter.setMitems(responseAddStaff);
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

    public class StaffAdapter extends BaseAdapter {
        public List<ResponseGetStaffList.DataBean> getMitems() {
            return mitems;
        }

        public void setMitems(List<ResponseGetStaffList.DataBean> mitems) {
            this.mitems = mitems;
            notifyDataSetChanged();
        }

        List<ResponseGetStaffList.DataBean> mitems;

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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.satffPassword.setText(mitems.get(position).getPasstext());
            viewHolder.staff.setText(mitems.get(position).getUsername() + " " + mitems.get(position).getTel());
            viewHolder.staffQuanxian.setText(mitems.get(position).getQxtxt());
            viewHolder.staffQuanxian.setTextColor(Color.RED);
            viewHolder.sting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopviewStaff popviewStaff = new PopviewStaff();
                    popviewStaff.show();
                    popviewStaff.staffId.setText(mitems.get(position).getTel());
                    popviewStaff.staffName.setText(mitems.get(position).getUsername());
                    popviewStaff.staffPassword.setText(mitems.get(position).getPasstext());
                    String qx = mitems.get(position).getQx();
                    if (qx.contains("1")) {
                        popviewStaff.isDicount.setSelect(true);
                    } else {
                        popviewStaff.isDicount.setSelect(false);
                    }
                    if (qx.contains("2")) {
                        popviewStaff.isGive.setSelect(true);
                    } else {
                        popviewStaff.isGive.setSelect(false);
                    }
                    if (qx.contains("3")) {
                        popviewStaff.isRetrun.setSelect(true);
                    } else {
                        popviewStaff.isRetrun.setSelect(false);
                    }
                    if (qx.contains("4")) {
                        popviewStaff.isOrder.setSelect(true);
                    } else {
                        popviewStaff.isOrder.setSelect(false);
                    }
                    if (qx.contains("5")) {
                        popviewStaff.isExcl.setSelect(true);
                    } else {
                        popviewStaff.isExcl.setSelect(false);
                    }

                    popviewStaff.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.deleteStaff(new RequestDelete().setId(mitems.get(position).getId()));
                        }
                    });
                    popviewStaff.cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popviewStaff.dismiss();
                        }
                    });
                    popviewStaff.save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final StringBuilder stringBuilder = new StringBuilder();
                            if (popviewStaff.isDicount.isSelect()) {
                                stringBuilder.append("1,");
                            }

                            if (popviewStaff.isGive.isSelect()) {
                                stringBuilder.append("2,");
                            }
                            if (popviewStaff.isRetrun.isSelect()) {
                                stringBuilder.append("3,");
                            }
                            if (popviewStaff.isOrder.isSelect()) {
                                stringBuilder.append("4,");
                            }
                            if (popviewStaff.isExcl.isSelect()) {
                                stringBuilder.append("5");
                            }
                            presenter.updateStaff(new RequestUpdateStaff().setMid(AppMode.getInstance().getMid()).
                                    setPassword(popviewStaff.staffPassword.getText().toString()).
                                    setQx(stringBuilder.toString()).
                                    setTel(popviewStaff.staffId.getText().toString()).
                                    setUsername(popviewStaff.staffName.getText().toString()).
                                    setId(mitems.get(position).getId()));
                            popviewStaff.dismiss();
                        }
                    });
                }
            });
            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.staff)
            TextView staff;
            @BindView(R.id.satff_password)
            TextView satffPassword;
            @BindView(R.id.staff_quanxian)
            TextView staffQuanxian;
            @BindView(R.id.sting)
            Button sting;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class PopviewStaff {
        @BindView(R.id.staff_name)
        EditText staffName;
        @BindView(R.id.staff_id)
        EditText staffId;
        @BindView(R.id.staff_password)
        EditText staffPassword;
        @BindView(R.id.is_dicount)
        SelectView isDicount;
        @BindView(R.id.is_give)
        SelectView isGive;
        @BindView(R.id.is_retrun)
        SelectView isRetrun;
        @BindView(R.id.textView5)
        TextView textView5;
        @BindView(R.id.is_order)
        SelectView isOrder;
        @BindView(R.id.is_excl)
        SelectView isExcl;
        @BindView(R.id.cancel)
        Button cancel;
        @BindView(R.id.delete)
        Button delete;
        @BindView(R.id.save)
        Button save;
        private PopUpViewUtil popUpViewUtil;
        Unbinder unbinder;
        View view;

        private void init() {
            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_staff, null, false);
            unbinder = ButterKnife.bind(this, view);

            popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
                @Override
                public void onDismiss() {
                    unbinder.unbind();
                }
            });
        }

        public void show() {
            init();
            popUpViewUtil.popListWindow(parent, view,
                    popUpViewUtil.getWindowManager(parent.getContext()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(parent.getContext()).getDefaultDisplay().getHeight() / 2, Gravity.CENTER, null);
        }

        public void dismiss() {
            popUpViewUtil.dismiss();
        }
    }
}
