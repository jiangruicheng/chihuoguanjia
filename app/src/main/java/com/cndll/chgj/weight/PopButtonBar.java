package com.cndll.chgj.weight;

import android.view.View;
import android.widget.Button;

import com.cndll.chgj.R;

/**
 * Created by kongqing on 2017/5/3.
 */

public class PopButtonBar {
    Button save, delete, cancel;

    public interface OnPopButtonClick {
        void onSave();

        void onDelete();

        void onCancel();

    }

    public OnPopButtonClick getOnPopButtonClick() {
        return onPopButtonClick;
    }

    public void setOnPopButtonClick(OnPopButtonClick onPopButtonClick) {
        this.onPopButtonClick = onPopButtonClick;
    }

    private OnPopButtonClick onPopButtonClick;

    public void setSaveGoneIs(int isGone) {
        save.setVisibility(isGone);
    }

    public void setCacelGoneIs(int isGone) {
        cancel.setVisibility(isGone);
    }

    public void setDeleteGoneIs(int isGone) {
        delete.setVisibility(isGone);
    }

    public void init(View view) {
        save = (Button) view.findViewById(R.id.save);
        delete = (Button) view.findViewById(R.id.delete);
        cancel = (Button) view.findViewById(R.id.cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopButtonClick != null) {
                    onPopButtonClick.onSave();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopButtonClick != null) {
                    onPopButtonClick.onCancel();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopButtonClick != null) {
                    onPopButtonClick.onDelete();
                }
            }
        });
    }
}
