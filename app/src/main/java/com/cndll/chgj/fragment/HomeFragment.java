package com.cndll.chgj.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cndll.chgj.R;
import com.cndll.chgj.mvp.presenter.HomePresenter;
import com.cndll.chgj.mvp.presenter.impl.LoginImpl;
import com.cndll.chgj.mvp.view.HomeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


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
    @BindView(R.id.imageButton)
    ImageButton imageButton;
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

    @OnClick(R.id.order)
    void onclick_order() {
        replaceFragmentAddToBackStack(OrderDishFragment.newInstance(null, null), null);
    }

    @OnClick(R.id.register)
    void onclick_register() {
        replaceFragmentAddToBackStack(RegisterFragment.newInstance(null, null), null);
    }


    @OnClick(R.id.caipin_do)
    void onclick_do() {
        replaceFragmentAddToBackStack(CaiPinFunctionFragment.newInstance(null, null), null);
    }

    @OnClick(R.id.desk_edit)
    void oclick_desk() {
        replaceFragmentAddToBackStack(DeskEditorFragment.newInstance(null, null), null);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentAddToBackStack(MenuEditorFragment.newInstance(null, null), null);
            }
        });
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentAddToBackStack(LoginFragment.newInstance(null, null), new LoginImpl());

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

    }

    @Override
    public void showProg(String mesg) {

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
