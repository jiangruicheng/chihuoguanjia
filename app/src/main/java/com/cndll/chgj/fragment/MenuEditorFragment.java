package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.MenuPrenster;
import com.cndll.chgj.mvp.view.MenuView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.ButtonSwitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuEditorFragment extends Fragment implements MenuView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.back)
    Button back;

    @OnClick(R.id.back)
    void onclick_back() {
        fragmentManager.popBackStack();
    }

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.frame1)
    FrameLayout frame;
    @BindView(R.id.add_cailei)
    Button addCailei;

    @OnClick(R.id.add_cailei)
    void onclick_cailei() {
        if (!fragments.get(CAILEI).isVisible()) {
            switchFragment(CAILEI);
        } else {
            PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.popview_add_cailei, null, false);
            popUpViewUtil.popListWindow(addCailei,
                    view,
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getHeight() / 10 * 2, Gravity.CENTER, null);
        }
    }

    @BindView(R.id.add_caipin)
    Button addCaipin;
    private AddCaiPin popview;

    @OnClick(R.id.add_caipin)
    void onclick_caipin() {
        if (!fragments.get(CAIPIN).isVisible()) {
            switchFragment(CAIPIN);
        } else {
            popview = new AddCaiPin();
            popview.init();
            popview.show();
        }
    }

    FragmentManager fragmentManager;

    private void switchFragment(String name) {
        Iterator iterator = fragments.values().iterator();
        while (iterator.hasNext()) {
            Fragment fragment = (Fragment) iterator.next();
            if (fragment.equals(fragments.get(name))) {
                fragmentManager.beginTransaction().show(fragment).commit();
            } else {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }

        }
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuEditorFragment newInstance(String param1, String param2) {
        MenuEditorFragment fragment = new MenuEditorFragment();
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

    private static final String CAILEI = "CL";
    private static final String CAIPIN = "CP";
    private Map<String, Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_editor, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragments = new HashMap<>();
        fragments.put(CAILEI, CaileiFragment.newInstance(null, null));
        fragments.put(CAIPIN, CaipinFragment.newInstance(null, null));
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame1, fragments.get(CAILEI)).add(R.id.frame1, fragments.get(CAIPIN)).hide(fragments.get(CAIPIN)).commit();
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

    @Override
    public void setPresenter(MenuPrenster presenter) {

    }

    @Override
    public void showPrintList(List<ResponsePrintList.DataBean> dataBeen) {
        if (popview != null) {
            popview.showPrint(dataBeen);
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

    private class AddCaiPin {
        ArrayAdapter arrayAdapter;
        List<String> dataString = new ArrayList<>();
        PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popview_caipin, null, false);
        View buttonSwitch = view.findViewById(R.id.bs_dazhe);
        View buttonSwitch1 = view.findViewById(R.id.bs_over);
        View buttonSwitch2 = view.findViewById(R.id.bs_print);
        ButtonSwitch dazhe, print, over;
        EditText name, unit, price, queryid;
        TextView printer;
        Button cancel, delete, save;


        public void init() {
            dataString.add("1234");
            dataString.add("1234");
            dataString.add("1234");
            dataString.add("1234");
            name = (EditText) view.findViewById(R.id.name);
            unit = (EditText) view.findViewById(R.id.unit);
            price = (EditText) view.findViewById(R.id.price);
            queryid = (EditText) view.findViewById(R.id.query_code);
            cancel = (Button) view.findViewById(R.id.cancel);
            printer = (TextView) view.findViewById(R.id.print);
            save = (Button) view.findViewById(R.id.save);
            delete = (Button) view.findViewById(R.id.delete);
            dazhe = initSiwtch(buttonSwitch, "可", "否");
            print = initSiwtch(buttonSwitch2, "可", "否");
            over = initSiwtch(buttonSwitch1, "可", "否");

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUpViewUtil.dismiss();
                }
            });
            printer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopUpViewUtil popupSpinner = PopUpViewUtil.getInstance();
                    View spinner_list = LayoutInflater.from(getContext()).inflate(R.layout.popview_spinner_list, null, false);
                    arrayAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_register_item, dataString);
                    ListView spinnerItem = (ListView) spinner_list.findViewById(R.id.spinner);
                    spinnerItem.setAdapter(arrayAdapter);
                    spinnerItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /*((TextView) v).setText(dataBean.get(position).getName());
                            selectDataBean = position;*/
                            popupSpinner.dismiss();
                        }
                    });
                    int[] locations = new int[2];
                    printer.getLocationOnScreen(locations);
                    locations[1] = locations[1] + printer.getHeight();
                    popupSpinner.popListWindow(frame, spinner_list,
                            printer.getWidth(), popupSpinner.getWindowManager(getContext()).
                                    getDefaultDisplay().getHeight() * 1 / 5, Gravity.NO_GRAVITY, locations);
                }
            });
        }

        public void showPrint(List<ResponsePrintList.DataBean> dataBeen) {

            if (dataBeen != null) {
                if (dataString == null) {
                    dataString = new ArrayList<>();
                } else {
                    dataString.clear();
                }
                for (int i = 0; i < dataBeen.size(); i++) {
                    dataString.add(dataBeen.get(i).getName());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        }

        private ButtonSwitch initSiwtch(View view, String left, String right) {
            ButtonSwitch aSwitch = new ButtonSwitch();
            aSwitch.setLeftBackground(R.drawable.shape_button_yellow);
            aSwitch.setRightBackground(R.drawable.shape_dialog_fillet_solid);
            aSwitch.setText(left, right);
            aSwitch.setTextColor(0xffDC5301, 0xff000000);
            aSwitch.init(view);
            return aSwitch;
        }

        public void show() {
            popUpViewUtil.popListWindow(addCailei,
                    view,
                    popUpViewUtil.getWindowManager(
                            getActivity()).
                            getDefaultDisplay().
                            getWidth(),
                    popUpViewUtil.getWindowManager(
                            getActivity()).
                            getDefaultDisplay().
                            getHeight() / 10 * 6, Gravity.CENTER, null);

        }
    }
}
