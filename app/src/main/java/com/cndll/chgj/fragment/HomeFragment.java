package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cndll.chgj.R;
import com.cndll.chgj.adapter.MendianListAdpater;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.HomePresenter;
import com.cndll.chgj.mvp.presenter.impl.AddDeskImpl;
import com.cndll.chgj.mvp.presenter.impl.BillImpl;
import com.cndll.chgj.mvp.presenter.impl.DeshMethodImpl;
import com.cndll.chgj.mvp.presenter.impl.LoginImpl;
import com.cndll.chgj.mvp.presenter.impl.MenuImpl;
import com.cndll.chgj.mvp.presenter.impl.OrderImpl;
import com.cndll.chgj.mvp.presenter.impl.PrintListImpl;
import com.cndll.chgj.mvp.presenter.impl.RegisterImpl;
import com.cndll.chgj.mvp.presenter.impl.StaffImpl;
import com.cndll.chgj.mvp.view.HomeView;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.MesgShow;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;

    Unbinder unbinder;
    @BindView(R.id.logoff)
    Button logoff;
    @BindView(R.id.desk_edit)
    CircleImageView deskEdit;
    @BindView(R.id.caipin_do)
    CircleImageView caipinDo;
    @BindView(R.id.register)
    CircleImageView register;
    @BindView(R.id.order)
    CircleImageView order;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.resetpassword)
    Button resetpassword;
    @BindView(R.id.mode_image)
    ImageView modeImage;
    @BindView(R.id.unloding_top)
    TextView unlodingTop;
    @BindView(R.id.loding_top)
    LinearLayout lodingTop;
    @BindView(R.id.username)
    TextView username;

    @OnClick(R.id.resetpassword)
    void onclick_resetpassword() {
        replaceFragmentAddToBackStack(FindPasswordFragment.newInstance(null, null), null);
    }

    @BindView(R.id.switch_mendian)
    Button switchMendian;
    @BindView(R.id.parent)
    LinearLayout parent;
    MendianListAdpater mendianListAdpater;

    @OnClick(R.id.switch_mendian)
    void onclick_switchmendian() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_mendian, parent, false);
        ListView listView = (ListView) view.findViewById(R.id.list_mendian);
        if (adapter == null) {
            mendianListAdpater = new MendianListAdpater();
        }
        listView.setAdapter(mendianListAdpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppMode.getInstance().setMid(mendianListAdpater.getList().get(position).getId());
                init();
            }
        });

        PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                mendianListAdpater = null;
            }
        });
        int[] locations = new int[2];
        switchMendian.getLocationOnScreen(locations);
        locations[1] = locations[1] + switchMendian.getHeight();
        popUpViewUtil.popListWindow(switchMendian, view,
                popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getWidth() / 3 * 2,
                popUpViewUtil.getWindowManager(getContext()).getDefaultDisplay().getHeight() / 10 * 4, Gravity.NO_GRAVITY, locations);
        /*setMendianList(new ArrayList<ResponseMendianHomeList.DataBean>());*/
        presenter.getMendianList();
    }

    @BindView(R.id.today_comin)
    TextView todayComin;
    @BindView(R.id.today_numb)
    TextView todayNumb;
    @BindView(R.id.month_comin)
    TextView monthComin;
    @BindView(R.id.search_baobiao)
    Button searchBaobiao;

    @OnClick(R.id.search_baobiao)
    void onclick_baobiao() {
        replaceFragmentAddToBackStack(ReportFragment.newInstance(null, null), null);
    }

    @BindView(R.id.set_print)
    CircleImageView setPrint;

    @OnClick(R.id.set_print)
    void onclick_setpirng() {
        replaceFragmentAddToBackStack(PrintSetingFragment.newInstance(null, null), new PrintListImpl());
    }

    @BindView(R.id.staff)
    CircleImageView staff;

    @OnClick(R.id.staff)
    void onclick_staff() {
        replaceFragmentAddToBackStack(StaffFragment.newInstance(null, null), new StaffImpl());
    }

    @BindView(R.id.search_bear)
    CircleImageView searchBear;

    @OnClick(R.id.search_bear)
    void onclick_bill() {
        replaceFragmentAddToBackStack(BillQueryFragment.newInstance(null, null), new BillImpl());
    }

    @BindView(R.id.setting)
    CircleImageView setting;

    @OnClick(R.id.setting)
    void onclick_setting() {
        replaceFragmentAddToBackStack(SetingFragment.newInstance(null, null), null);
    }

    @BindView(R.id.kefu)
    ImageButton kefu;

    @OnClick(R.id.kefu)
    void onclick_kefu() {

    }

    @BindView(R.id.mode_switch)
    ImageButton modeSwitch;

    @OnClick(R.id.mode_switch)
    void onclick_modeswitch() {
        final PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mode_switch, null, false);
        TextView nodesk, havedesk, cancel;
        nodesk = (TextView) view.findViewById(R.id.mode_not_desk);
        havedesk = (TextView) view.findViewById(R.id.mode_yes_desk);
        cancel = (TextView) view.findViewById(R.id.cancel);
        nodesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMode.getInstance().setDeskMode(false);
                modeImage.setImageResource(R.mipmap.mode_not_desk);
                popUpViewUtil.dismiss();
            }
        });
        havedesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppMode.getInstance().setDeskMode(true);
                modeImage.setImageResource(R.mipmap.mode_yes_desk);
                popUpViewUtil.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpViewUtil.dismiss();
            }
        });
        popUpViewUtil.
                showDialog(getActivity(),
                        view,
                        0,
                        (int) (d.getHeight() * 0.5),
                        (int) (d.getWidth() * 0.9),
                        (int) (d.getHeight() * 0.4),
                        R.style.Translucent_Dialog);
    }

    @BindView(R.id.usernumber)
    TextView usernumber;
    @BindView(R.id.mendian_numb)
    TextView mendianNumb;

    @OnClick(R.id.order)
    void onclick_order() {
        if (AppMode.getInstance().isDeskMode()) {
            replaceFragmentAddToBackStack(DeskFragment.newInstance(null, null), new AddDeskImpl());
        } else {
            replaceFragmentAddToBackStack(OrderDishFragment.newInstance(null, null), new OrderImpl());
        }
    }

    @OnClick(R.id.register)
    void onclick_register() {
        replaceFragmentAddToBackStack(RegisterFragment.newInstance(null, null), new RegisterImpl());
    }


    @OnClick(R.id.caipin_do)
    void onclick_do() {
        replaceFragmentAddToBackStack(CaiPinFunctionFragment.newInstance(null, null), new DeshMethodImpl());
    }

    @OnClick(R.id.desk_edit)
    void oclick_desk() {
        replaceFragmentAddToBackStack(DeskEditorFragment.newInstance(null, null), new AddDeskImpl());
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private void getPrintList() {
        AppRequest.getAPI().getPrintList(new RequestPrintList().setUid(AppMode.getInstance().getUid()).setUid(AppMode.getInstance().getUid())).
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).subscribe(new MObeserver(null) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 1) {
                    List<ResponsePrintList.DataBean> list = ((ResponsePrintList) baseResponse).getData();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getIs_default().equals("1")) {
                            AppMode.getInstance().setPrint_code(list.get(i).getId());
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentAddToBackStack(MenuEditorFragment.newInstance(null, null), new MenuImpl());
            }
        });
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppMode.getInstance().isLoading()) {
                    MesgShow.showMesg("", "确定退出登录?", logoff, new MesgShow.OnButtonListener() {
                        @Override
                        public void onListerner() {
                            AppRequest.getAPI().logoff(AppMode.getInstance().getUid()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
                                @Override
                                public void onCompleted() {
                                    super.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                }

                                @Override
                                public void onNext(BaseResponse baseResponse) {
                                    super.onNext(baseResponse);
                                    if (baseResponse.getCode() == 1) {
                                        showMesg("退出成功");
                                        AppMode.getInstance().setMid("3").setUid("3");
                                        AppMode.getInstance().setLoading(false);
                                    }
                                }
                            });
                        }
                    }, new MesgShow.OnButtonListener() {
                        @Override
                        public void onListerner() {

                        }
                    }, true);
                } else {
                    replaceFragmentAddToBackStack(LoginFragment.newInstance(null, null), new LoginImpl());
                }
            }
        });
        return view;
    }


    private void setBannerUrl(List<String> urls) {

        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, urls).setCanLoop(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getHomeInfo();
        banner.startTurning(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();

    }

    @Override
    public void onStop() {
        super.onStop();
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
        MesgShow.showMesg("", mesg, kefu, new MesgShow.OnButtonListener() {
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

    private void init() {
        getPrintList();
        presenter.getHomeInfo();
        if (AppMode.getInstance().isLoading()) {
            lodingTop.setVisibility(View.VISIBLE);
            unlodingTop.setVisibility(View.GONE);
            username.setText(AppMode.getInstance().getUsername());
            logoff.setText("退出登录");
        } else {
            lodingTop.setVisibility(View.GONE);
            unlodingTop.setVisibility(View.VISIBLE);
            logoff.setText("登录");
        }
    }

    @Override
    public void reload() {
        super.reload();
        init();
    }

    private HomePresenter presenter;

    @Override
    public void setPresenter(HomePresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public void setBanner(List<String> urls) {
        setBannerUrl(urls);
    }

    @Override
    public void setUserNumb(String s) {
        usernumber.setText(s);
    }

    @Override
    public void setMendianNumb(String s) {
        mendianNumb.setText(s);
    }

    @Override
    public void setTodayComin(String s) {
        todayComin.setText(s);
    }

    @Override
    public void setTodaynumb(String s) {
        todayNumb.setText(s);
    }

    @Override
    public void setMonthComin(String s) {
        monthComin.setText(s);
    }

    @Override
    public void setMendianName(String s) {
        textView.setText(s);
    }

    @Override
    public void setMendianList(List<ResponseMendianHomeList.DataBean> list) {
        /*list = new ArrayList<>();
        list.add(new ResponseMendianHomeList.DataBean().setCode("10000").setName("宝安店"));
        list.add(new ResponseMendianHomeList.DataBean().setCode("10000").setName("宝安店"));
        list.add(new ResponseMendianHomeList.DataBean().setCode("10000").setName("宝安店"));
        list.add(new ResponseMendianHomeList.DataBean().setCode("10000").setName("宝安店"));*/
        if (mendianListAdpater != null) {
            mendianListAdpater.setList(list);
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

    public class LocalImageHolderView implements Holder<String> {
        // private CubeImageView imageView;
        private SimpleDraweeView imageView;

        @Override
        public View createView(Context context) {
            // imageView = new CubeImageView(context);
            imageView = new SimpleDraweeView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            imageView.setImageURI(data);
           /* ImageLoader imageLoader = ImageLoaderFactory.create(context);
            imageLoader.setImageLoadHandler(new DefaultImageLoadHandler(context));*/
            //imageView.loadImage(imageLoader, data);
        }
    }
}
