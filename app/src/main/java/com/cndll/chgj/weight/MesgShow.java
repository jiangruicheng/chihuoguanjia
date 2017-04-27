package com.cndll.chgj.weight;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;

/**
 * Created by kongqing on 2017/4/27.
 */

public class MesgShow {
    public interface OnButtonListener {
        void onListerner();
    }

    public static void showMesg(String title, String mesg, View location, final OnButtonListener sure, final OnButtonListener cancel, boolean isCancelShow) {
        final PopUpViewUtil popUpViewUtil = PopUpViewUtil.getInstance();
        View view = LayoutInflater.from(location.getContext()).inflate(R.layout.popview_showmesg, null, false);
        Button btn_sure = (Button) view.findViewById(R.id.sure);
        Button btn_cancel = (Button) view.findViewById(R.id.cancel);
        TextView txt_title = (TextView) view.findViewById(R.id.mesg_title);
        TextView txt_mesg = (TextView) view.findViewById(R.id.mesg_info);
        txt_mesg.setText(mesg);
        if (title == null || title.equals("")) {
        } else {
            txt_title.setText(title);
        }
        if (!isCancelShow) {
            btn_cancel.setVisibility(View.GONE);
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancel != null) {
                    cancel.onListerner();
                }
                popUpViewUtil.dismiss();
            }
        });
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sure != null) {
                    sure.onListerner();
                }
                popUpViewUtil.dismiss();
            }
        });
        int screenWidth = popUpViewUtil.getWindowManager(location.getContext()).getDefaultDisplay().getWidth();
        int screenHeight = popUpViewUtil.getWindowManager(location.getContext()).getDefaultDisplay().getHeight();
        int width = screenWidth / 7 * 6;
        int height = screenHeight / 5 * 2;
        popUpViewUtil.showDialog((Activity) location.getContext(), view, 0, 0, width, height, R.style.Translucent_Dialog);
    }
}
