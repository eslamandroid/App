package com.eaapps.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MaxHeightRecyclerView extends RecyclerView {

    public MaxHeightRecyclerView(Context paramContext)
    {
        super(paramContext);
    }

    public MaxHeightRecyclerView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public MaxHeightRecyclerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec((int)getResources().getDimension(R.dimen.recycler_view_max_height), Integer.MIN_VALUE));
    }

}
