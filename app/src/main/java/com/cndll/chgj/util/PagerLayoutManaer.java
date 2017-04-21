package com.cndll.chgj.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

/**
 * Created by kongqing on 2017/4/21.
 */

public class PagerLayoutManaer extends RecyclerView.LayoutManager {
    private Context context;
    private int row;
    private int count;
    private int page;
    private int totalHeight;

    public PagerLayoutManaer(Context context, int row, int count) {
        this.context = context;
        this.row = row;
        this.count = count;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    private int margin = 12;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            return;
        }
        if (state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        int width = (getWidth() - margin * count * 2) / count;
        int height = (getHeight() - margin * row * 2) / row;
        page = getItemCount() / (count * row);
        totalHeight = (page - 1) * getHeight() + (getItemCount() % (count * row) == 0 ? 0 : 1) * getHeight();
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
            if ((i + 1) % (count * row) == 0) {
                removeAndRecycleAllViews(recycler);
            }
        }
        recyclerFillAndAttach(recycler, state);
    }

    private int offsetY;
    protected SparseArray<Rect> allItemFrames = new SparseArray<>();

    private void recyclerFillAndAttach(RecyclerView.Recycler recycler, RecyclerView.State state) {
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
        Toast.makeText(context, "" + getChildCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);
        int newY = offsetY + dy;
        int result = dy;
        if (newY > totalHeight) {
            result = totalHeight - offsetY;
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
