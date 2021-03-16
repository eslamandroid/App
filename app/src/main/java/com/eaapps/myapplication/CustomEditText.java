package com.eaapps.myapplication;


import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText
        extends AppCompatEditText
{

    public CustomEditText(Context paramContext)
    {
        super(paramContext);
    }

    public CustomEditText(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public CustomEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public void clearError()
    {
        setEndDrawable(0);
    }

//    public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
//    {
//        return new CustomEditText.CustomInputConnection(this, super.onCreateInputConnection(paramEditorInfo), true);
//    }

    protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
    {
        super.onFocusChanged(paramBoolean, paramInt, paramRect);
        if (paramBoolean) {
            clearError();
        }
    }

    protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
        super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
        clearError();
    }

    public void setEndDrawable(int paramInt)
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, paramInt, 0);
    }



}
