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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.adapter.RegisterListAdpater;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStoreList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseStoreTye;
import com.cndll.chgj.mvp.presenter.RegisterPresenter;
import com.cndll.chgj.mvp.view.RegisterView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.util.StringHelp;
import com.cndll.chgj.weight.MesgShow;
import com.cndll.chgj.weight.OptionPickView;

import java.util.ArrayList;
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
public class RegisterFragment extends BaseFragment<RegisterListAdpater> implements RegisterView {
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
    /*RegisterListAdpater adapter;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new RegisterListAdpater(getActivity(), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        adapter.setOnItemClick(new ListAdapter.OnItemsClick() {
            @Override
            public void onReEidetClick(View view, int position) {
                if (adapter.getMitems() != null) {
                    ResponseGetStoreList.DataBean dataBean = ((ResponseGetStoreList.DataBean) adapter.getMitems().get(position));
                    popview(dataBean.getName(), dataBean.getType(), AppMode.getInstance().getTel(), Integer.valueOf(dataBean.getProvince()), Integer.valueOf(dataBean.getCity()), dataBean.getCode());
                }
            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mItemTouchHelper.attachToRecyclerView(list);
        if (AppMode.getInstance().getUid() != null) {
            presenter.getStoreList(AppMode.getInstance().getUid());
        }
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

    @Override
    public void onStop() {
        super.onStop();
        List<ResponseGetStoreList.DataBean> a = (List<ResponseGetStoreList.DataBean>) adapter.getMitems();
        List<RequestMendianOrd> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            b.add(new RequestMendianOrd().setId(Integer.valueOf(a.get(i).getId())).setOrd(a.get(i).getOrderList()));
        }
        presenter.ordStore(b);
    }

    private RegisterInfo registerInfo;

    private void popview(String name, String type, String tel, int province, int city, String storeid) {
        registerInfo = new RegisterInfo();
        registerInfo.init(name, type, tel, province, city, storeid);
        registerInfo.popUpView();
    }

    private void popview() {
        registerInfo = new RegisterInfo();
        registerInfo.init();
        registerInfo.popUpView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showMesg(String mesg) {
        MesgShow.showMesg("", mesg, register, null, null, false);
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
    public void loadListView(List<ResponseGetStoreList.DataBean> list) {
        adapter.setMitems(list);
    }


    public void showArea(List<String> item0, List<List<String>> item1, ResponseArea responseArea) {
        if (registerInfo != null) {
            registerInfo.showAre(item0, item1, responseArea);
        }
    }

    @Override
    public void showStoreType(List<ResponseStoreTye.DataBean> dataBean) {
        if (registerInfo != null) {
            registerInfo.showStroeType(dataBean);
        }
    }

    @Override
    public void setVerify(String verify) {
        if (registerInfo != null) {
            registerInfo.setEdi_verify(verify);
        }
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

    private class RegisterInfo {
        ViewGroup view = (ViewGroup) LayoutInflater.from(getActivity()).
                inflate(R.layout.popview_register_info, parent, false);
        PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        TextView spinner;
        ArrayAdapter arrayAdapter;
        LinearLayout verifyLayout, storyIdLayout;
        Button cancel, save, delete, getverify;
        TextView shi, sheng, storyId;
        EditText name, tel, edi_verify;
        OptionPickView optionPickView;
        View.OnClickListener register = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString() == null || name.getText().toString().trim().equals("")) {
                    showMesg("请输入名称");
                    return;
                }
                if (spinner.getText().toString() == null || spinner.getText().toString().trim().equals("")) {
                    showMesg("请选择门店类型");
                }
                if (verify.equals("")) {
                    showMesg("请获取验证码");
                    return;
                }
                if (!verify.equals(edi_verify.getText().toString())) {
                    showMesg("验证码不对,请重新输入");
                    return;
                }
                if (shengPostion == -1 || shiPostion == -1) {
                    showMesg("请选择地址");
                    return;
                }
                presenter.register(spinner.getText().toString(), tel.getText().toString(), name.getText().toString(), shengPostion, shiPostion);
                dismiss();
            }
        };
        List<ResponseStoreTye.DataBean> dataBean;
        List<String> dataString = new ArrayList<>();
        int selectDataBean = -1;
        private boolean isShowAreList;

        private void init(String pname, final String typename, String ptel, int pshengPostion, int pshiPostion, String storyId) {
            presenter.getArea();
            shiPostion = pshiPostion;
            shengPostion = pshengPostion;
            spinner = (TextView) view.findViewById(R.id.type);
            delete = (Button) view.findViewById(R.id.delete);
            delete.setVisibility(View.GONE);
            name = (EditText) view.findViewById(R.id.edit_name);
            cancel = (Button) view.findViewById(R.id.cancel);
            save = (Button) view.findViewById(R.id.save);
            shi = (TextView) view.findViewById(R.id.shi);
            sheng = (TextView) view.findViewById(R.id.sheng);
            this.storyId = (TextView) view.findViewById(R.id.story_id);
            getverify = (Button) view.findViewById(R.id.get_verify);
            tel = (EditText) view.findViewById(R.id.edit_tel);
            edi_verify = (EditText) view.findViewById(R.id.edit_verify);
            verifyLayout = (LinearLayout) view.findViewById(R.id.verify_layout);
            storyIdLayout = (LinearLayout) view.findViewById(R.id.story_id_layout);
            name.setHint(pname);
            spinner.setText(typename);
            if (ptel != null) {
                tel.setHint(ptel);
                tel.setEnabled(false);
                verifyLayout.setVisibility(View.GONE);
                storyIdLayout.setVisibility(View.VISIBLE);
                this.storyId.setText(storyId);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
                save.setOnClickListener(register);
            }

            spinner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    presenter.getStoreType();
                    final PopUpViewUtil popupSpinner = PopUpViewUtil.getInstance();
                    View spinner_list = LayoutInflater.from(getContext()).inflate(R.layout.popview_spinner_list, null, false);
                    arrayAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_register_item, dataString);
                    ListView spinnerItem = (ListView) spinner_list.findViewById(R.id.spinner);
                    spinnerItem.setAdapter(arrayAdapter);
                    spinnerItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ((TextView) v).setText(dataBean.get(position).getName());
                            selectDataBean = position;
                            popupSpinner.dismiss();
                        }
                    });
                    int[] locations = new int[2];
                    spinner.getLocationOnScreen(locations);
                    locations[1] = locations[1] + spinner.getHeight();
                    popupSpinner.popListWindow(list, spinner_list,
                            spinner.getWidth(), popupSpinner.getWindowManager(getContext()).
                                    getDefaultDisplay().getHeight() * 1 / 5, Gravity.NO_GRAVITY, locations);
                }
            });

            getverify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringHelp.isMobileNO(tel.getText().toString())) {
                        presenter.getVerify(tel.getText().toString());
                    } else {
                        showMesg("手机号输入有误，请重新输入");
                    }
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            shi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showArePopUpView();
                }
            });
            sheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showArePopUpView();
                }
            });
            popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
                @Override
                public void onDismiss() {
                    selectDataBean = -1;
                    shengPostion = -1;
                    shiPostion = -1;
                }
            });
        }

        private void init() {
            init("", "", null, -1, -1, "");
        }

        private void popUpView() {
            popUpViewUtil.popListWindow(list, view,
                    popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().
                            getWidth() * 19 / 20,
                    popUpViewUtil.getWindowManager(getContext()).
                            getDefaultDisplay().getHeight() * 3 / 5,
                    Gravity.CENTER, null);

        }

        private void dismiss() {
            arrayAdapter = null;
            popUpViewUtil.dismiss();
        }

        private ResponseArea responseArea;
        private int shengPostion = -1;
        private int shiPostion = -1;

        /*spinner.setAdapter(arrayAdapter);*/
        private void showAre(List<String> item0, List<List<String>> item1, final ResponseArea responseArea) {
            this.responseArea = responseArea;

            if (optionPickView == null) {
                optionPickView = new OptionPickView(getActivity(), R.layout.dialog_opitionpick);
                optionPickView.setLooper(false, false);
                optionPickView.setOptionItem(item0, item1);
            }
            if (optionPickView.getOnOptionPickViewSelect() == null) {
                optionPickView.setOnOptionPickViewSelect(new OptionPickView.OnOptionPickViewSelect() {
                    @Override
                    public void onSelect(int sheng, int shi) {
                        shengPostion = Integer.valueOf(responseArea.getData().get(sheng).getId());
                        shiPostion = Integer.valueOf(responseArea.getData().get(sheng).getCitys().get(shi).getId());
                        RegisterInfo.this.sheng.setText(responseArea.getData().get(sheng).getName());
                        RegisterInfo.this.shi.setText(responseArea.getData().get(sheng).getCitys().get(shi).getName());
                        isShowAreList = false;
                    }

                    @Override
                    public void onCancel() {
                        isShowAreList = false;
                    }
                });
            }
            if (isShowAreList) {
                optionPickView.show();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        for (int i = 0; i < responseArea.getData().size(); i++) {
                            if (responseArea.getData().get(i).getId().equals(String.valueOf(shengPostion))) {
                                final int finalI = i;
                                sheng.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        sheng.setText(responseArea.getData().get(finalI).getName());
                                    }
                                });
                                for (int j = 0; j < responseArea.getData().get(i).getCitys().size(); j++) {
                                    if (responseArea.getData().get(i).getCitys().get(j).getId().equals(String.valueOf(shiPostion))) {
                                        final int finalJ = j;
                                        final int finalI1 = i;
                                        shi.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                shi.setText(responseArea.getData().get(finalI1).getCitys().get(finalJ).getName());
                                                return;
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                }.start();
            }
        }

        private String verify = "";

        private void showArePopUpView() {
            isShowAreList = true;
            if (optionPickView == null) {
                presenter.getArea();
            } else {
                optionPickView.show();
            }
        }

        private void setEdi_verify(String verify) {
            this.verify = verify;
        }

        private void showStroeType(List<ResponseStoreTye.DataBean> dataBean) {
            this.dataBean = dataBean;
            if (dataString == null) {
                dataString = new ArrayList<>();
            } else {
                dataString.clear();
            }
            for (int i = 0; i < dataBean.size(); i++) {
                dataString.add(dataBean.get(i).getName());
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }


}
