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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.DeskListAdapter;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddDesk;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaDesk;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;
import com.cndll.chgj.mvp.presenter.AddDeskPresenter;
import com.cndll.chgj.mvp.view.AddDeskView;
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
 * Use the {@link DeskEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeskEditorFragment extends BaseFragment<DeskListAdapter> implements AddDeskView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.search_caipin)
    EditText searchCaipin;
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
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
    @BindView(R.id.add_desk)
    Button addDesk;

    @OnClick(R.id.add_desk)
    void onclick_addDesk() {
        DeskInfo deskInfo = new DeskInfo();
        deskInfo.init(-1);
        deskInfo.show();
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DeskEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeskEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeskEditorFragment newInstance(String param1, String param2) {
        DeskEditorFragment fragment = new DeskEditorFragment();
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
        View view = inflater.inflate(R.layout.fragment_desk_editor, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("桌台情况");
        adapter = new DeskListAdapter(getActivity(), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        adapter.setOnItemClick(new ListAdapter.OnItemsClick() {
            @Override
            public void onReEidetClick(View view, int position) {
                DeskInfo deskInfo = new DeskInfo();
                deskInfo.init(position);
                deskInfo.show();
            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });
        setListViewAdapter(list);
        presenter.getDeskList(new RequestGetDeskList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
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

    private AddDeskPresenter presenter;

    @Override
    public void setPresenter(AddDeskPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void showDeskList(List<ResponseGetDeskList.DataBean> dataBeen) {
        adapter.setMitems(dataBeen);
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

    private class DeskInfo {
        private PopUpViewUtil popUpViewUtil;
        private View view;
        private EditText name;
        private Button cancel, save, delete;
        private int position;

        public void init(final int position) {
            this.position = position;

            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_add_desk, null, false);
            name = (EditText) view.findViewById(R.id.edit_add_cailei);
            save = (Button) view.findViewById(R.id.save);
            delete = (Button) view.findViewById(R.id.delete);
            cancel = (Button) view.findViewById(R.id.cancel);
            if (position > -1) {
                name.setText(((ResponseGetDeskList.DataBean) adapter.getMitems().get(position)).getName());
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
                        presenter.addDesk(new RequestAddDesk().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(name.getText().toString()));
                        name.setText("");
                    } else {
                        presenter.updateDesk(new RequestUpdaDesk().setId(((ResponseGetDeskList.DataBean) adapter.getMitems().get(position)).getId()).setName(name.getText().toString()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position > -1) {
                        presenter.deleteDesk(new RequestDelete().setId(((ResponseGetDeskList.DataBean) adapter.getMitems().get(position)).getId()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
        }

        public void show() {
            popUpViewUtil.popListWindow(addDesk,
                    view,
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getHeight() / 10 * 2, Gravity.CENTER, null);
        }
    }
}
