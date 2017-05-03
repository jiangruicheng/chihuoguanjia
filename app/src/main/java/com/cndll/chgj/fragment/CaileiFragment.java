package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.adapter.CaiLeiListAdapter;
import com.cndll.chgj.adapter.ListAdapter;
import com.cndll.chgj.itemtouchhelperdemo.helper.OnStartDragListener;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.util.LinearPagerLayoutManager;
import com.cndll.chgj.util.PagingScrollHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaileiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaileiFragment extends BaseFragment<CaiLeiListAdapter> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.menu_list)
    RecyclerView menuList;
    Unbinder unbinder;
    @BindView(R.id.menu_name)
    TextView menuName;
    @BindView(R.id.revise)
    Button revise;
    @BindView(R.id.edit)
    LinearLayout edit;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CaileiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaileiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaileiFragment newInstance(String param1, String param2) {
        CaileiFragment fragment = new CaileiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*private ItemTouchHelper mItemTouchHelper;*/
    public void setAdapterList(List<ResponseGetCaileiList.DataBean> data) {
        adapter.setMitems(data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void setMenuEvent(MenuEditorFragment.MenuEvent menuEvent) {
        this.menuEvent = menuEvent;
    }

    /*CaiLeiListAdapter adapter;*/
    private MenuEditorFragment.MenuEvent menuEvent;

    public CaiLeiListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cailei, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearPagerLayoutManager linearPagerLayoutManager = new LinearPagerLayoutManager(getContext(), 5, 1);
        menuList.setLayoutManager(linearPagerLayoutManager);
        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        scrollHelper.setUpRecycleView(menuList);
        adapter = new CaiLeiListAdapter(getContext(), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        adapter.setOnItemClick(new ListAdapter.OnItemsClick() {
            @Override
            public void onReEidetClick(View view, int position) {

            }

            @Override
            public void onItemClick(View view, int position) {
                if (menuEvent != null) {
                    menuEvent.trunCaipin(((List<ResponseGetCaileiList.DataBean>) adapter.getMitems()).get(position).getId());
                }
            }
        });

        setListViewAdapter(menuList);

        /*CaiPinListAdapter adapter = new CaiPinListAdapter(getActivity(), new OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });

        adapter.setType(CaiPinListAdapter.CAILEI);
        menuList.setHasFixedSize(true);
        menuList.setAdapter(adapter);
        menuList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(menuList);*/


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
