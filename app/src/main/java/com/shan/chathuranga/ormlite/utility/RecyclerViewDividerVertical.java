package com.shan.chathuranga.ormlite.utility;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Chathuranga on 6/4/2017.
 */

public class RecyclerViewDividerVertical extends RecyclerView.ItemDecoration{

    int space;

    public RecyclerViewDividerVertical(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //outRect.left = space;
        //outRect.right = space;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        }
    }
}
