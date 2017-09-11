package com.cndll.chgj.weight;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cndll.chgj.R;
import com.cndll.chgj.fragment.SetingFragment;
import com.cndll.chgj.util.PopUpViewUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by jiangruicheng on 2017/9/11.
 */

public class KeyBoardUtil {
    public static void setEdit(final EditText editText, Activity act) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {//4.0以下 danielinbiti
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            editText.setShowSoftInputOnFocus(false);
        }

    }

    private Context ctx;
    private Activity act;
    private View keyboardView;
    private Keyboard k1;// 字母键盘
    private Keyboard k2;// 数字键盘
    public boolean isnun = false;// 是否数据键盘
    public boolean isupper = false;// 是否大写

    private EditText ed;

    public KeyBoardUtil(View view, Context ctx, EditText edit) {
        this.ctx = ctx;
        this.ed = edit;
        //keyboardView = LayoutInflater.from(ctx).inflate(R.layout.activity_key,null,false);
        keyboardView = view.findViewById(R.id.key);
        keyboardView.setEnabled(true);
        WindowManager wg = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(wg.getDefaultDisplay().getWidth(), wg.getDefaultDisplay().getHeight() / 7 * 2);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        keyboardView.setLayoutParams(lp);
        keyboardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView v = (TextView) keyboardView.findViewById(R.id.tran);
        v.setText("完成");
        setOnclick(keyboardView);
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

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof View) {
                Editable editable = ed.getText();
                int start = ed.getSelectionStart();
                if (v.getId() == R.id.cancel) {

                    return;
                }
                if (v.getId() == R.id.sure) {

                    return;
                }
                if (v.getId() == R.id.acp) {

                    return;
                }
                if (v.getId() == R.id.key_exit) {

                    return;
                }
                if (v.getId() == R.id.show) {
                    return;
                }
                if (v.getId() == R.id.delete_abc || v.getId() == R.id.delete_number) {
                    editable.delete(start - 1, start);
                    return;
                }
                if (v.getId() == R.id.tran_number) {

                    return;
                }
                if (v.getId() == R.id.tran) {
                    hideKeyboard();
                    return;
                }
                editable.delete(ed.getSelectionStart(), ed.getSelectionEnd());
                editable.insert(start, ((TextView) v).getText());

            }
        }
    };
    private KeyboardView.OnKeyboardActionListener l = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            {
                editable.delete(ed.getSelectionStart(), ed.getSelectionEnd());
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };

    /**
     *          * 键盘大小写切换
     *          
     */
    private void changeKey() {
        List<Keyboard.Key> keylist = k1.getKeys();
        if (isupper) {//大写切换小写
            isupper = false;
            for (Keyboard.Key key : keylist) {
                if (key.label != null && isword(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                }
            }
        } else {//小写切换大写
            isupper = true;
            for (Keyboard.Key key : keylist) {
                if (key.label != null && isword(key.label.toString())) {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
    }

    private PopUpViewUtil pupViewUtil;

    public void showKeyboard() {
        keyboardView.setVisibility(View.VISIBLE);
        /*if (pupViewUtil == null) {
            if (keyboardView.getParent() != null) {
                ((ViewGroup) keyboardView.getParent()).removeView(keyboardView);
            }
            pupViewUtil = PopUpViewUtil.getInstance();
            int[] locations = new int[2];
            locations[0] = 0;
            pupViewUtil.popListWindow(ed, keyboardView, pupViewUtil.getWindowManager(ctx).getDefaultDisplay().getWidth(), pupViewUtil.getWindowManager(ctx).getDefaultDisplay().getHeight() / 7 * 2, Gravity.BOTTOM, null);
        }*/
    }

    public void hideKeyboard() {
        keyboardView.setVisibility(View.GONE);
        /*if (pupViewUtil != null) {
            pupViewUtil.dismiss();
            pupViewUtil = null;
        }*/
    }

    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }
}
