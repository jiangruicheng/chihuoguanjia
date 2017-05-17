package com.cndll.chgj.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
import com.cndll.chgj.mvp.presenter.LoginPresenter;
import com.cndll.chgj.mvp.presenter.impl.RegisterImpl;
import com.cndll.chgj.mvp.view.LoginView;
import com.cndll.chgj.weight.MesgShow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements LoginView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.back)
    Button back;

    @OnClick(R.id.back)
    void onclick_back() {
        popBackFragment();
    }

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    @OnClick(R.id.login)
    void onclick_login() {
        presenter.login(new RequestLogin().setCode(number.getText().toString()).setTel(tel.getText().toString()).setPassword(password.getText().toString()), getContext());
    }

    @BindView(R.id.register)
    Button register;

    @OnClick(R.id.register)
    void onclick_register() {
        replaceFragmentAddToBackStack(RegisterFragment.newInstance(null, null), new RegisterImpl());
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, RegisterFragment.newInstance(null, null)).addToBackStack("").commit();
    }

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("登录");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CHGJ", MODE_PRIVATE);
        info.setText("当前门店号：" + sharedPreferences.getString("mdcode", "") + "\n" + sharedPreferences.getString("mdname", ""));
        number.setText(sharedPreferences.getString("mdcode", ""));
        tel.setText(sharedPreferences.getString("tel", ""));
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
        MesgShow.showMesg("", mesg, login, new MesgShow.OnButtonListener() {

            @Override
            public void onListerner() {

            }
        }, new MesgShow.OnButtonListener() {
            @Override
            public void onListerner() {

            }
        }, false);
    }

    @Override
    public void showProg(String mesg) {

    }

    private LoginPresenter presenter;

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void loginSucces() {
        //replaceFragment(HomeFragment.newInstance(null, null), null);
        popBackFragment();
    }

    @Override
    public void showUserMesg(String[] strings) {

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
