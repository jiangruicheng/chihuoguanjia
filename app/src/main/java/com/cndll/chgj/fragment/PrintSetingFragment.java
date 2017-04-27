package com.cndll.chgj.fragment;

import android.content.Context;
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
import com.cndll.chgj.util.PopUpViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrintSetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrintSetingFragment extends Fragment {
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
    @BindView(R.id.parent)
    LinearLayout parent;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PrintSetingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrintSetingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrintSetingFragment newInstance(String param1, String param2) {
        PrintSetingFragment fragment = new PrintSetingFragment();
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
        View view = inflater.inflate(R.layout.fragment_print_seting, container, false);
        unbinder = ButterKnife.bind(this, view);
        PrintAdatper adatper = new PrintAdatper();
        list.setAdapter(adatper);
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

    public static class PrintAdatper extends BaseAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_printsting, parent, false);
            final TextView print_name = (TextView) convertView.findViewById(R.id.print_name);
            final TextView print_statue = (TextView) convertView.findViewById(R.id.print_statue);
            Button seting = (Button) convertView.findViewById(R.id.sting);
            seting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popview_printsting, null, false);
                    final EditText name = (EditText) view.findViewById(R.id.print_name);
                    EditText id = (EditText) view.findViewById(R.id.print_id);
                    EditText key = (EditText) view.findViewById(R.id.print_key);
                    Button cancel = (Button) view.findViewById(R.id.cancel);
                    Button delete = (Button) view.findViewById(R.id.delete);
                    Button save = (Button) view.findViewById(R.id.save);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popUpViewUtil.dismiss();
                        }
                    });
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            print_name.setText(name.getText().toString());
                            print_statue.setText("ttttttt");
                            popUpViewUtil.dismiss();
                        }
                    });
                    popUpViewUtil.popListWindow(parent, view,
                            popUpViewUtil.getWindowManager(parent.getContext()).getDefaultDisplay().getWidth(),
                            popUpViewUtil.getWindowManager(parent.getContext()).getDefaultDisplay().getHeight() / 3, Gravity.CENTER, null);
                }
            });

            return convertView;
        }


    }
}
