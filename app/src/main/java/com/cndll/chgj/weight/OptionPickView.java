package com.cndll.chgj.weight;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.List;

/**
 * Created by kongqing on 2017/4/18.
 */

public class OptionPickView {
    private Activity context;
    private int layout;

    public OptionPickView(Activity context, @LayoutRes int layout) {
        this.context = context;
        this.layout = layout;
        init();
    }

    private LoopView loopView0;
    private LoopView loopView1;
    private View rootview;
    private List<String> item0;
    private List<List<String>> item1;
    List<List<List<String>>> s;
    private Button cancel;
    private Button sure;

    private void init() {
        rootview = LayoutInflater.from(context).inflate(layout, null, false);
        loopView0 = (LoopView) rootview.findViewById(R.id.loopview0);
        loopView1 = (LoopView) rootview.findViewById(R.id.loopview1);
        cancel = (Button) rootview.findViewById(R.id.cancel);
        sure = (Button) rootview.findViewById(R.id.sure);
    }

    public void setOptionItem(List<String> item0, List<List<String>> item1) {
        this.item0 = item0;
        this.item1 = item1;
        loopView0.setItems(item0);
        loopView0.setInitPosition(0);
        loopView1.setItems(item1.get(0));
        loopView0.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                loopView1.setItems(OptionPickView.this.item1.get(index));
                loopView1.setInitPosition(0);
            }
        });
    }

    public void show() {
        if (rootview.getParent() != null) {
            ((ViewGroup) (rootview.getParent())).removeView(rootview);
        }
        PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        popUpViewUtil.showDialog(context, rootview,
                0,
                popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getHeight() / 5 * 2,
                popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getWidth(), popUpViewUtil.getWindowManager(context).
                        getDefaultDisplay().getHeight() / 5 * 3, 0);
    }

    public void setLooper(boolean item0, boolean item1) {
        if (!item0) {
            loopView0.setNotLoop();
        }
        if (!item1) {
            loopView1.setNotLoop();
        }
    }
}
