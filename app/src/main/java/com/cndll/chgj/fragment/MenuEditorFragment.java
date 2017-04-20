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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;

import java.util.HashMap;
import java.util.Iterator;
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
public class MenuEditorFragment extends Fragment {
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

    @OnClick(R.id.add_caipin)
    void onclick_caipin() {
        if (!fragments.get(CAIPIN).isVisible()) {
            switchFragment(CAIPIN);
        } else {

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
