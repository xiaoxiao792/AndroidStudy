package com.shaobing.miho.Tools;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @className : MyDividerDecoration
 * @description : 仿QQ左滑
 * @date : 2020/6/3 15:06
 * @author : 邵文炳
 */
public class MyDividerDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, 2);
    }
}
