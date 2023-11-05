package com.example.gorenganindonesia.Util;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemSpacing extends RecyclerView.ItemDecoration {
    private int space; // Define the spacing in pixels

    public RecyclerViewItemSpacing(Context context, int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.top = space;
        outRect.bottom = space;
    }
}
