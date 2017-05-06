package com.cndll.chgj.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by kongqing on 2017/4/21.
 */

public class PagerLayoutManager extends RecyclerView.LayoutManager {
    private Context context;
    private int row;
    private int count;
    private int page;
    private int offsetHeight;

    public PagerLayoutManager(Context context, int row, int count) {
        this.context = context;
        this.row = row;
        this.count = count;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    private int margin = 12;
    protected int allHeight;
    protected int width;
    protected int height;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAllViews();
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (state.isPreLayout()) {

            return;
        }
        detachAndScrapAttachedViews(recycler);
        width = (getWidth() - margin * count * 2) / count;
        height = (getHeight() - margin * row * 2) / row;
        page = getItemCount() / (count * row);
        allHeight = (getHeight() / row) * getItemCount() / count + (getItemCount() % count == 0 ? 0 : 1) * (getHeight() / row) - getHeight();
        offsetHeight = (page - 1) * getHeight() + (getItemCount() % (count * row) == 0 ? 0 : 1) * getHeight();
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            //measureChild(view, 0, 0);
            addView(view);

            Rect rect = allItemFrames.get(i);
            if (rect == null) {
                rect = new Rect();
            }
            int mrow = i / count;
            int mcount = i % count;
            /*layoutDecorated(view, margin + 2 * mcount * margin + (mcount) * width,
                    height * mrow + 2 * mrow * margin + margin,
                    margin + 2 * (mcount) * margin + (mcount + 1) * width,
                    height * (mrow + 1) + margin + 2 * mrow * margin);*/
            rect.set(margin + 2 * mcount * margin + (mcount) * width,
                    height * mrow + 2 * mrow * margin + margin,
                    margin + 2 * (mcount) * margin + (mcount + 1) * width,
                    height * (mrow + 1) + margin + 2 * mrow * margin);

            allItemFrames.put(i, rect);
            if ((i + 1) % (count * (row)) == 0) {
                removeAndRecycleAllViews(recycler);
            }
        }
        recyclerFillAndAttach(recycler, state);
    }

    protected int offsetY;
    protected SparseArray<Rect> allItemFrames = new SparseArray<>();

    protected void recyclerFillAndAttach(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            return;
        }

        Rect displayRect = new Rect(0, offsetY, getWidth(), getHeight() + offsetY);
        Rect childRect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childRect.left = getDecoratedLeft(child);
            childRect.top = getDecoratedTop(child);
            childRect.right = getDecoratedRight(child);
            childRect.bottom = getDecoratedBottom(child);
            if (!Rect.intersects(displayRect, childRect)) {
                removeAndRecycleView(child, recycler);
            }
        }

        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayRect, allItemFrames.get(i))) {
                View view = recycler.getViewForPosition(i);
                addView(view);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
                params.width = width;
                params.height = height;
                view.setLayoutParams(params);
                measureChildWithMargins(view, 0, 0);
                Rect rect = allItemFrames.get(i);
                layoutDecorated(view, rect.left, rect.top - offsetY, rect.right, rect.bottom - offsetY);
                Log.d("Loaction " + i + "  ", "recyclerFillAndAttach: " + "" + "\n" +
                        "left:" + (rect.left) + "\n" +
                        "top:" + (rect.top - offsetY) + "\n" +
                        "right:" + (rect.right) + "\n" +
                        "bottom:" + (rect.bottom - offsetY) + "\n");
            }
        }

    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int newY = offsetY + dy;
        int result = dy;
        if (newY > offsetHeight) {
            result = offsetHeight - offsetY;
        } else if (newY < 0) {
            result = 0 - offsetY;
        }
        offsetY += result;
        offsetChildrenVertical(-result);
        recyclerFillAndAttach(recycler, state);
        return result;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
    }
}
