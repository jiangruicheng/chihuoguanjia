package com.cndll.chgj.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by kongqing on 2017/3/29.
 */

public class PopUpViewUtil {


    private PopupWindow popupWindow;
    private AlertDialog alertDialog;

    public void setPopupWindowHeight(int height) {
        popupWindow.setHeight(height);
    }

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

    private void popListWindow(View location, View view, int width, int height, int gravity, int[] locations, boolean isoutside) {
        if (view == null) {
            return;
        }
        popupWindow = new PopupWindow(view, width, height);
        if (isoutside) {
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(isoutside);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onDismissAction != null) {
                    onDismissAction.onDismiss();
                }
                dismiss();
            }
        });
        if (null != locations && locations.length == 2) {
            if (null == location.getWindowToken())
                return;
            popupWindow.showAtLocation(location, gravity, locations[0], locations[1]);
        } else {
            if (null == location.getWindowToken())
                return;
            popupWindow.showAtLocation(location, gravity, 0, 0);
        }
    }

    public void popListWindowNotOut(View location, View view, int width, int height, int gravity, int[] locations) {
        popListWindow(location, view, width, height, gravity, locations, false);
    }

    public void popListWindow(View location, View view, int width, int height, int gravity, int[] locations) {
        popListWindow(location, view, width, height, gravity, locations, true);
    }

    public void showDialog(@NonNull Activity context, @LayoutRes int layout, int locationX, int locationY, int width, int heigth) {
        showDialog(context, layout, locationX, locationY, width, heigth, 0);
    }

    public void showDialog(@NonNull Context context, View layout, int locationX, int locationY, int width, int heigth, @StyleRes int style) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context, style);
        alertDialog = dialog.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(layout);
        android.view.WindowManager.LayoutParams p = window.getAttributes();  //获取对话框当前的参数值
        p.height = heigth;   //高度设置为屏幕的0.3
        p.width = width;    //宽度设置为屏幕的0.5
        p.x = locationX;
        p.y = locationY;
        window.setAttributes(p);     //设置生效
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onDismissAction != null) {
                    onDismissAction.onDismiss();
                }
                dismiss();
            }
        });
    }

    public void showDialog(@NonNull Activity context, @LayoutRes int layout, int locationX, int locationY, int width, int heigth, @StyleRes int style) {
        View view = LayoutInflater.from(context).inflate(layout, null, false);
        showDialog(context, view, locationX, locationY, width, heigth, style);
       /* AlertDialog.Builder dialog = new AlertDialog.Builder(context, style);
        alertDialog = dialog.create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(layout);
        android.view.WindowManager.LayoutParams p = window.getAttributes();  //获取对话框当前的参数值
        p.height = heigth;   //高度设置为屏幕的0.3
        p.width = width;    //宽度设置为屏幕的0.5
        p.x = locationX;
        p.y = locationY;
        window.setAttributes(p);     //设置生效
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onDismissAction != null) {
                    onDismissAction.onDismiss();
                }
                dismiss();
            }
        });*/
    }

    public interface OnDismissAction {
        void onDismiss();
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
        onDismissAction = null;
    }
}
