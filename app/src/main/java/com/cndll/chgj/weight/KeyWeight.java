package com.cndll.chgj.weight;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.util.PopUpViewUtil;

/**
 * Created by kongqing on 2017/5/4.
 */

public class KeyWeight {
    public int getCancelcolor() {
        return cancelcolor;
    }

    public void setCancelcolor(int cancelcolor) {
        this.cancelcolor = cancelcolor;
    }

    public int getSurecolor() {
        return surecolor;
    }

    public void setSurecolor(int surecolor) {
        this.surecolor = surecolor;
    }

    private int cancelcolor;
    private int surecolor;
    private PopUpViewUtil popUpViewUtil;

    public void setKey(View key) {
        this.key = key;
    }

    private View key;

    public void setChilder(boolean childer) {
        isChilder = childer;
    }

    private boolean isChilder;
    private TextView show;
    private TextView abcKey;
    private TextView numberKey;
    public final static int Mode_NoButton = 1;
    public final static int Mode_OnlyNumb = 2;
    boolean isButtonShow;

    public void buttonShowIs(boolean is) {
        isButtonShow = is;
    }

    String showHint;

    public void setShowHintText(String s) {
        showHint = s;
    }

    String cancelText;

    public void setCancelText(String s) {
        cancelText = s;
    }

    String sureText;

    public void setSureText(String s) {
        sureText = s;
    }

    public void init(Context context, View location, int Mode) {
        if (popUpViewUtil == null)
            popUpViewUtil = PopUpViewUtil.getInstance();
        if (key == null) {
            key = LayoutInflater.from(context).inflate(R.layout.popview_key, null, false);
        }
        if (!isChilder) {
            if (location != null) {
                int[] locations = new int[2];
                location.getLocationOnScreen(locations);
                locations[1] = locations[1] - popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 2;
                popUpViewUtil.popListWindow(location, key,
                        popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth(),
                        popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 2,
                        Gravity.NO_GRAVITY, locations);

            } else {
                popUpViewUtil.popListWindow(location, key,
                        popUpViewUtil.getWindowManager(context).getDefaultDisplay().getWidth(),
                        popUpViewUtil.getWindowManager(context).getDefaultDisplay().getHeight() / 2,
                        Gravity.NO_GRAVITY, null);
            }
        }
        setOnclick(key);
        Button cancel, sure;
        cancel = (Button) key.findViewById(R.id.cancel);
        sure = (Button) key.findViewById(R.id.sure);
        cancel.setText(cancelText);
        cancel.setBackgroundColor(cancelcolor);
        sure.setBackgroundColor(surecolor);
        sure.setText(sureText);
        TextView tran = (TextView) key.findViewById(R.id.tran);
        TextView show = (TextView) key.findViewById(R.id.show);
        show.setHint(showHint);
        View layout = key.findViewById(R.id.buttonlayout);
        View keyabc = key.findViewById(R.id.abc_key);
        View keynum = key.findViewById(R.id.number_key);
        if (Mode == Mode_NoButton) {
            layout.setVisibility(View.GONE);
        }
        if (Mode == Mode_OnlyNumb) {
            tran.setText(".");
            layout.setVisibility(View.VISIBLE);
            keyabc.setVisibility(View.GONE);
            keynum.setVisibility(View.VISIBLE);
        }

        popUpViewUtil.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
            @Override
            public void onDismiss() {
                if (showbuffer.length() > 0) {
                    showbuffer.delete(0, showbuffer.length());
                    popUpViewUtil = null;
                }
            }
        });
        // ((TextView)key.findViewById(R.id.tran)).setText(".");
        if (show == null) {
            show = (TextView) key.findViewById(R.id.show);
        }
        show.setText(showbuffer.toString());
    }

    private void setOnclick(View view) {
        if (!(view instanceof ViewGroup)) {
            (view).setOnClickListener(listener);
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setOnclick(((ViewGroup) view).getChildAt(i));
            }
        }
    }

    private void setCap(View view, boolean iscap) {
        if (view instanceof TextView) {
            if (view.getId() != R.id.show) {
                ((TextView) view).setAllCaps(iscap);
            }
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setCap(((ViewGroup) view).getChildAt(i), iscap);
            }
        }
    }

    private StringBuilder showbuffer = new StringBuilder();
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof View) {
                if (v.getId() == R.id.cancel) {
                    if (onKeyClick != null) {
                        onKeyClick.onKeyCancel(showbuffer.toString());
                    }
                    if (popUpViewUtil != null) {
                        popUpViewUtil.dismiss();
                    }
                    return;
                }
                if (v.getId() == R.id.sure) {
                    if (onKeyClick != null) {
                        onKeyClick.onKeySure(showbuffer.toString());
                    }
                    if (popUpViewUtil != null) {
                        popUpViewUtil.dismiss();
                    }
                    return;
                }
                if (v.getId() == R.id.acp) {
                    setCap(key, cap);
                    cap = !cap;
                    return;
                }
                if (v.getId() == R.id.key_exit) {
                    if (onKeyClick != null) {
                        onKeyClick.onKeyCancel(showbuffer.toString());
                    }
                    if (popUpViewUtil != null) {
                        popUpViewUtil.dismiss();
                    }
                    return;
                }
                if (v.getId() == R.id.show) {
                    return;
                }
                if (v.getId() == R.id.delete_abc || v.getId() == R.id.delete_number) {
                    if (showbuffer.length() > 0) {
                        showbuffer.deleteCharAt(showbuffer.length() - 1);
                        show.setText(showbuffer.toString());
                    }
                    return;
                }
                if (v.getId() == R.id.tran_number) {
                    key.findViewById(R.id.number_key).setVisibility(View.VISIBLE);
                    key.findViewById(R.id.abc_key).setVisibility(View.GONE);
                    return;
                }
                if (v.getId() == R.id.tran && ((TextView) v).getText().equals("ABC")) {
                    key.findViewById(R.id.number_key).setVisibility(View.GONE);
                    key.findViewById(R.id.abc_key).setVisibility(View.VISIBLE);
                    return;
                }
                if (show == null) {
                    show = (TextView) key.findViewById(R.id.show);
                }
                showbuffer.append(((TextView) v).getText());
                show.setText(showbuffer.toString());
                if (onKeyClick != null) {
                    onKeyClick.onKeyNub(showbuffer.toString());
                }
            }
        }
    };
    boolean cap = false;

    public interface OnKeyClick {
        void onKeyCancel(String s);

        void onKeySure(String s);

        void onKeyNub(String s);
    }

    public OnKeyClick getOnKeyClick() {
        return onKeyClick;
    }

    public void setOnKeyClick(OnKeyClick onKeyClick) {
        this.onKeyClick = onKeyClick;
    }

    private OnKeyClick onKeyClick;
}
