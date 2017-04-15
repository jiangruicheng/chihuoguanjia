package com.cndll.chgj.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by kongqing on 2017/3/29.
 */

public class PopUpViewUtil {


    private PopupWindow popupWindow;

    public void setOnDismissAction(OnDismissAction onDismissAction) {
        this.onDismissAction = onDismissAction;
    }

    private OnDismissAction onDismissAction;

    private PopUpViewUtil() {

    }

    public static PopUpViewUtil getInstance() {
        return new PopUpViewUtil();
    }

    public WindowManager getWindowManager(Context context) {
        WindowManager wg = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wg;
    }

    public void popListWindow(View location, View view, int width, int height, int gravity, int[] locations) {
        if (view == null) {
            return;
        }
        popupWindow = new PopupWindow(view, width, height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onDismissAction != null) {
                    onDismissAction.onDismiss();
                }
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow = null;
            }
        });
        if (null != locations && locations.length == 2) {
            popupWindow.showAtLocation(location, gravity, locations[0], locations[1]);
        } else {
            popupWindow.showAtLocation(location, gravity, 0, 0);
        }
    }

   public interface OnDismissAction {
        void onDismiss();
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
