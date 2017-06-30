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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.info.Orders;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetMethodList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.presenter.DeshMethodPresenter;
import com.cndll.chgj.mvp.presenter.NotePresenter;
import com.cndll.chgj.mvp.view.NoteView;
import com.cndll.chgj.util.PagerLayoutManager;
import com.cndll.chgj.util.PopUpViewUtil;

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
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends BaseFragment implements NoteView {
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
    @BindView(R.id.select_method)
    ListView selectMethod;
    @BindView(R.id.all_method)
    RecyclerView allMethod;
    @BindView(R.id.write_method)
    Button writeMethod;

    @OnClick(R.id.write_method)
    void onclick_writemethod() {
        DeshMethod deshMethod = new DeshMethod();
        deshMethod.init(-1);
        deshMethod.show();
    }

    @BindView(R.id.cancel)
    Button cancel;

    @OnClick(R.id.cancel)
    void onclick_cancel() {
        popBackFragment();
    }

    @BindView(R.id.makesure)
    Button makesure;

    @OnClick(R.id.makesure)
    void onclick_makesure() {
        setNote();
    }

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
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


    public Orders.Order getOrder() {
        return order;
    }

    public NoteFragment setOrder(Orders.Order order) {
        this.order = order;
        return this;
    }

    private Orders.Order order;

    public NoteFragment setWrite(Orders.Write write) {
        this.write = write;
        return this;
    }

    private Orders.Write write;
    private boolean isWrite;
    private AllMethodAdapter allMethodAdapter;
    private SelectMethodAdapter selectMethodAdapter;

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setNote() {
        if (write != null) {
            if (write.getItemsBean().getRemarks() == null) {
                write.getItemsBean().setRemarks(new ArrayList<ResponseMethod.DataBean>());
            }

            write.itemsBean.setRemarks(selectMethodAdapter.getMitems());
           /* write.itemsBean.getRemarks().setCount(write.getCount() + "");
            write.itemsBean.getRemarks().setId(write.getItemsBean().getName());*/
        } else {
            if (order.getItemsBean().getRemark() == null) {
                order.getItemsBean().setRemark(new ResponseGetCaipinList.DataBean.RemarkBean());
            }
            if (order.getItemsBean().getRemark().getRemarks() == null) {
                order.getItemsBean().getRemark().setRemarks(new ArrayList<ResponseMethod.DataBean>());
            }
            order.itemsBean.getRemark().setRemarks(selectMethodAdapter.getMitems());
            order.itemsBean.getRemark().setCount(order.getCount() + "");
            order.itemsBean.getRemark().setId(order.getItemsBean().getId());
        }
        popBackFragment();
    }

    public List<ResponseMethod.DataBean> getCurrItems() {
        return currItems;
    }

    public NoteFragment setCurrItems(List<ResponseMethod.DataBean> currItems) {
        this.currItems = currItems;
        return this;
    }

    private List<ResponseMethod.DataBean> currItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        unbinder = ButterKnife.bind(this, view);
        title.setText("备注要求");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackFragment();
            }
        });
        allMethodAdapter = new AllMethodAdapter();
        selectMethodAdapter = new SelectMethodAdapter();
        if (order.getItemsBean().getRemark() == null) {
            currItems = null;
        } else {
            currItems = new ArrayList<>(order.getItemsBean().getRemark().getRemarks());
        }
        if (currItems != null) {
            selectMethodAdapter.setMitems(currItems);
        }
        selectMethod.setAdapter(selectMethodAdapter);
        allMethod.setAdapter(allMethodAdapter);
        PagerLayoutManager gridLayoutManager = new PagerLayoutManager(getContext(), 3, 4);
        allMethod.setLayoutManager(gridLayoutManager);
        presenter.getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
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
        baseShowProg(back);
    }

    @Override
    public void disProg() {
        baseDisProg();
    }

    @Override
    public void toast(String s) {

    }

    NotePresenter presenter;

    @Override
    public void setPresenter(DeshMethodPresenter presenter) {
        this.presenter = (NotePresenter) presenter;
        this.presenter.setView(this);
    }


    @Override
    public void showMethodList(List<ResponseMethod.DataBean> dataBeen) {
        allMethodAdapter.setMitems(dataBeen);
    }

    @Override
    public void succGetList() {
        if (currItems == null) {
            return;
        }
        for (int i = 0; i < currItems.size(); i++) {
            for (int k = 0; k < allMethodAdapter.getMitems().size(); k++) {
                if (currItems.get(i).getId().equals(allMethodAdapter.getMitems().get(k).getId())) {
                    allMethodAdapter.select.add(k);
                    allMethodAdapter.notifyDataSetChanged();
                }
            }
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

    public class SelectMethodAdapter extends BaseAdapter {
        public List<ResponseMethod.DataBean> getMitems() {
            return mitems;
        }

        public void addMitems(ResponseMethod.DataBean m) {
            if (mitems == null) {
                mitems = new ArrayList<>();
            }
            mitems.add(m);
            notifyDataSetChanged();
        }

        public void removeMitem(ResponseMethod.DataBean m) {
            if (mitems != null) {
                mitems.remove(m);
                notifyDataSetChanged();
            }
        }

        public void removeMitem(String id) {
            for (int i = 0; i < mitems.size(); i++) {
                if (mitems.get(i).getId().equals(id)) {
                    mitems.remove(i);
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        public void setMitems(List<ResponseMethod.DataBean> mitems) {
            this.mitems = mitems;
            notifyDataSetChanged();
        }

        List<ResponseMethod.DataBean> mitems;

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
        public View getView(final int position, View convertView, final ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_writemethod, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.methodName.setText(mitems.get(position).getName());
            viewHolder.methodPrice.setText(mitems.get(position).getPrice());
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allMethodAdapter.unselect(mitems.get(position).getId());
                    mitems.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            @BindView(R.id.method_name)
            TextView methodName;
            @BindView(R.id.method_price)
            TextView methodPrice;
            @BindView(R.id.delete)
            Button delete;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class AllMethodAdapter extends RecyclerView.Adapter<AllMethodAdapter.ItemHolder> {
        public SelectMethodAdapter getSelectMethodAdapter() {
            return selectMethodAdapter;
        }

        public List<ResponseMethod.DataBean> getMitems() {
            return mitems;
        }

        public void setMitems(List<ResponseMethod.DataBean> mitems) {
            this.mitems = mitems;
            notifyDataSetChanged();
        }

        public void unselect(ResponseMethod.DataBean m) {
            select.remove(Integer.valueOf(mitems.indexOf(m)));
            notifyDataSetChanged();
        }

        public void unselect(String id) {
            for (int i = 0; i < mitems.size(); i++) {
                if (mitems.get(i).getId().equals(id)) {
                    select.remove(Integer.valueOf(i));
                    notifyDataSetChanged();
                    return;
                }
            }
        }

        List<Integer> select = new ArrayList<>();
        List<ResponseMethod.DataBean> selectItems = new ArrayList<>();
        List<ResponseMethod.DataBean> mitems;

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desh, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(final ItemHolder holder, final int position) {
            holder.name.setText(mitems.get(position).getName());
            holder.price.setText(mitems.get(position).getPrice());
            if (select.contains(position)) {
                holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
            } else {
                holder.parent.setBackgroundResource(R.drawable.shape_button_yellow);
            }
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (select.contains(position)) {
                        holder.parent.setBackgroundResource(R.drawable.shape_button_yellow);
                        selectMethodAdapter.removeMitem(mitems.get(position).getId());
                        select.remove(Integer.valueOf(position));
                    } else {
                        holder.parent.setBackgroundResource(R.drawable.shape_fillet_solid);
                        select.add(position);
                        selectMethodAdapter.addMitems(mitems.get(position));
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mitems != null) {
                return mitems.size();
            }
            return 0;
        }

        public class ItemHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.number)
            TextView number;
            @BindView(R.id.name)
            TextView name;
            @BindView(R.id.price)
            TextView price;
            @BindView(R.id.parent)
            LinearLayout parent;

            public ItemHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    private class DeshMethod {
        private PopUpViewUtil popUpViewUtil;
        private View view;
        private EditText name, price;
        private Button cancel, save, delete;
        private int position;

        public void init(final int position) {
            this.position = position;

            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(getContext()).inflate(R.layout.popview_desh_method, null, false);
            name = (EditText) view.findViewById(R.id.edit_add_cailei);
            save = (Button) view.findViewById(R.id.save);
            delete = (Button) view.findViewById(R.id.delete);
            cancel = (Button) view.findViewById(R.id.cancel);
            price = (EditText) view.findViewById(R.id.add_price);
            price.setHint("0");
            if (position > -1) {
                price.setText(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getPrice());
                name.setText(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getName());
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
                        ResponseMethod.DataBean dataBean = new ResponseMethod.DataBean();
                        dataBean.setName(name.getText().toString());
                        dataBean.setPrice(price.getText().toString());
                        selectMethodAdapter.addMitems(dataBean);
                        // presenter.addDeshMethod(new RequestAddMethod().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()).setName(name.getText().toString()).setPrice(price.getText().toString()));
                        name.setText("");
                        price.clearComposingText();
                        popUpViewUtil.dismiss();
                    } else {
                        // presenter.updateDeshMethod(new RequestUpdateMethod().setId(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getId()).setName(name.getText().toString()).setPrice(price.getText().toString()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position > -1) {
                        presenter.deleteDeshMethod(new RequestDeleteMethod().setId(((ResponseMethod.DataBean) adapter.getMitems().get(position)).getId()));
                        popUpViewUtil.dismiss();
                    }
                }
            });
        }

        public void show() {
            popUpViewUtil.popListWindow(writeMethod,
                    view,
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(getActivity()).getDefaultDisplay().getHeight() / 10 * 3, Gravity.CENTER, null);
        }

    }
}
