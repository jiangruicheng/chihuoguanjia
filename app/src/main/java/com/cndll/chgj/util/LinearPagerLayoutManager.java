package com.cndll.chgj.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by kongqing on 2017/4/24.
 */

public class LinearPagerLayoutManager extends PagerLayoutManager {
    public LinearPagerLayoutManager(Context context, int row, int count) {
        super(context, row, count);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);

        //实际要滑动的距离
        int travel = dy;

        //如果滑动到最顶部
        if (offsetY + dy < 0) {
            travel = -offsetY;
        } else if (offsetY + dy > allHeight) {//如果滑动到最底部
            travel = allHeight - offsetY;
        }

        //将竖直方向的偏移量+travel
        offsetY += travel;

        // 平移容器内的item
        offsetChildrenVertical(-travel);
        recyclerFillAndAttach(recycler, state);
        Log.d("--->", " childView count:" + getChildCount());
        return travel;
    }
}
