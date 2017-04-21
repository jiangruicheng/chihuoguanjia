package com.cndll.chgj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;

public class KeyActivity extends AppCompatActivity {
    LinearLayout key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup group = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_key, null, false);
        setContentView(group);
        key = (LinearLayout) group.findViewById(R.id.key);
        setOnclick(group);
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
            ((TextView) view).setAllCaps(iscap);
        } else if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setCap(((ViewGroup) view).getChildAt(i), iscap);
            }
        }
    }

    boolean cap = false;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {
                if (v.getId() == R.id.tran) {
                    setCap(key, cap);
                    cap = !cap;
                }
                Toast.makeText(KeyActivity.this, "" + ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
