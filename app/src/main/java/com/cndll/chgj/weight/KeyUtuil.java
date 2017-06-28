package com.cndll.chgj.weight;

import android.content.Context;
import android.view.View;

/**
 * Created by kongqing on 2017-06-28.
 */

public class KeyUtuil {
    public static void popUpkey(Builder builder) {
        KeyWeight keyWeight = new KeyWeight();
        keyWeight.setCancelText(builder.getHint());
        keyWeight.setSureText(builder.getSureHint());
        keyWeight.setShowHintText(builder.getShowhint());
        keyWeight.setCancelcolor(builder.getCancelcolor());
        keyWeight.setSurecolor(builder.getSurecolor());
        keyWeight.init(builder.getContext(), builder.getLocation(), builder.getMode());
        keyWeight.setOnKeyClick(builder.getOnKeyClick());
    }

    public static class Builder {
        public Context getContext() {
            return context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public View getLocation() {
            return location;
        }

        public Builder setLocation(View location) {
            this.location = location;
            return this;
        }

        Context context;
        View location;
        int mode;
        int cancelcolor;
        int surecolor;
        String showhint;
        String hint;
        String sureHint;

        public int getMode() {
            return mode;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public int getCancelcolor() {
            return cancelcolor;
        }

        public Builder setCancelcolor(int cancelcolor) {
            this.cancelcolor = cancelcolor;
            return this;
        }

        public int getSurecolor() {
            return surecolor;
        }

        public Builder setSurecolor(int surecolor) {
            this.surecolor = surecolor;
            return this;
        }

        public String getShowhint() {
            return showhint;
        }

        public Builder setShowhint(String showhint) {
            this.showhint = showhint;
            return this;
        }

        public String getHint() {
            return hint;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public String getSureHint() {
            return sureHint;
        }

        public Builder setSureHint(String sureHint) {
            this.sureHint = sureHint;
            return this;
        }

        public KeyWeight.OnKeyClick getOnKeyClick() {
            return onKeyClick;
        }

        public Builder setOnKeyClick(KeyWeight.OnKeyClick onKeyClick) {
            this.onKeyClick = onKeyClick;
            return this;
        }

        KeyWeight.OnKeyClick onKeyClick;
    }
}
