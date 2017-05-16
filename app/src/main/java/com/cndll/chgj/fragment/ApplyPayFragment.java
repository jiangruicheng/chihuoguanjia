package com.cndll.chgj.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ApplyPayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplyPayFragment extends BaseFragment {
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
    @BindView(R.id.idcard_face)
    ImageView idcardFace;

    @OnClick(R.id.idcard_face)
    void onclick_idcardface() {
        getImage();
    }

    @BindView(R.id.idcard_back)
    ImageView idcardBack;

    @OnClick(R.id.idcard_back)
    void onclick_idcardback() {
        getImage();
    }

    @BindView(R.id.business_licence)
    ImageView businessLicence;

    @OnClick(R.id.business_licence)
    void onclick_business() {
        getImage();
    }

    @BindView(R.id.storyID)
    EditText storyID;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.bankcard)
    EditText bankcard;
    @BindView(R.id.bankcard_username)
    EditText bankcardUsername;
    @BindView(R.id.bank_name)
    EditText bankName;
    @BindView(R.id.bank_adrress)
    EditText bankAdrress;
    @BindView(R.id.info)
    TextView info;
    Unbinder unbinder;
    @BindView(R.id.sure)
    Button sure;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ApplyPayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplyPayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplyPayFragment newInstance(String param1, String param2) {
        ApplyPayFragment fragment = new ApplyPayFragment();
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
        View view = inflater.inflate(R.layout.fragment_apply_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void getImage() {
        SelectImage selectImage = new SelectImage();
        selectImage.init(sure);
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

    public class SelectImage {
        View view;
        PopUpViewUtil popUpViewUtil;
        @BindView(R.id.carma)
        TextView carma;
        @BindView(R.id.photo)
        TextView photo;
        @BindView(R.id.cancel)
        TextView cancel;
        Unbinder unbinder;

        public void init(View location) {
            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_addimage, null, false);
            unbinder = ButterKnife.bind(this, view);
            carma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");//相片类型
                    getActivity().startActivityForResult(intent, 0x01);

                }
            });
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");//相片类型
                    getActivity().startActivityForResult(intent, 0x02);

                }
            });
            popUpViewUtil.popListWindow(location, view, popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth(), popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 3, Gravity.BOTTOM, null);
        }
    }
}
