package com.cndll.chgj.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by kongqing on 2017/6/16.
 */

public class AllLayout extends LinearLayout {
    public AllLayout(Context context) {
        super(context);
    }

    public AllLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AllLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
