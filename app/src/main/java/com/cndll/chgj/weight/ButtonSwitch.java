package com.cndll.chgj.weight;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.cndll.chgj.R;

/**
 * Created by kongqing on 2017/5/2.
 */

public class ButtonSwitch {
    private View view;
    private TextView left;
    private TextView right;

    public int isLeftInt() {
        if (isLeft) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
        if (left) {
            this.left.setBackgroundResource(leftBackground);
            this.left.setTextColor(leftTextColor);
            right.setBackgroundResource(rightBackground);
            right.setTextColor(rightTextColor);
        } else {
            this.left.setBackgroundResource(rightBackground);
            this.left.setTextColor(rightTextColor);
            right.setBackgroundResource(leftBackground);
            right.setTextColor(leftTextColor);
        }
    }

    private boolean isLeft = true;
    int leftBackground;
    int rightBackground;

    public void setLeftBackground(@DrawableRes int leftBackground) {
        this.leftBackground = leftBackground;
    }

    public void setRightBackground(@DrawableRes int rightBackground) {
        this.rightBackground = rightBackground;
    }

    private int leftTextColor, rightTextColor;
    private String leftText, rightText;

    public void setText(String left, String right) {
        leftText = left;
        rightText = right;
    }

    public void setTextColor(@ColorInt int left, @ColorInt int right) {
        leftTextColor = left;
        rightTextColor = right;
    }

    public void init(View view) {
        this.view = view;
        left = (TextView) view.findViewById(R.id.left);
        right = (TextView) view.findViewById(R.id.right);
        left.setTextColor(leftTextColor);
        right.setTextColor(rightTextColor);
        left.setText(leftText);
        right.setText(rightText);
        left.setBackgroundResource(leftBackground);
        right.setBackgroundResource(rightBackground);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLeft = true;
                left.setBackgroundResource(leftBackground);
                left.setTextColor(leftTextColor);
                right.setTextColor(rightTextColor);
                right.setBackgroundResource(rightBackground);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLeft = false;
                left.setBackgroundResource(rightBackground);
                right.setBackgroundResource(leftBackground);
                right.setTextColor(leftTextColor);
                left.setTextColor(rightTextColor);
            }
        });
    }
}
