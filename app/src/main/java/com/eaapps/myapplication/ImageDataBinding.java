package com.eaapps.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

public class ImageDataBinding {

    public static int getImageFromDrawable(Context context, String id) {
        return context.getResources().getIdentifier(id, "drawable", context.getPackageName());
    }

    @BindingAdapter(value = {"android:src", "fromDrawable", "placeholderImage", "errorImage"}, requireAll = false)
    public static void loadImageWithGlide(ImageView imageView, Object obj, boolean fromDrawable, Object placeHolder, Object errorImage) {
        RequestOptions options = new RequestOptions();

        if (placeHolder instanceof Drawable) options.placeholder((Drawable) placeHolder);
        if (placeHolder instanceof Integer) options.placeholder((Integer) placeHolder);

        if (errorImage instanceof Drawable) options.error((Drawable) errorImage);
        if (errorImage instanceof Integer) options.error((Integer) errorImage);

        RequestManager manager = Glide.with(imageView.getContext()).applyDefaultRequestOptions(options);

        if (obj != null) {
            if (obj instanceof String) {
                if (fromDrawable) {
                    int resId = getImageFromDrawable(imageView.getContext(), (String) obj);
                    if (resId > 0)
                        manager.load(resId).into(imageView);
                } else
                    manager.load((String) obj).into(imageView);
            } else if (obj instanceof Uri) {
                manager.load((Uri) obj).into(imageView);
            } else if (obj instanceof Drawable) {
                manager.load((Drawable) obj).into(imageView);
            } else if (obj instanceof Bitmap) {
                manager.load((Bitmap) obj).into(imageView);
            } else if (obj instanceof Integer) {
                manager.load((Integer) obj).into(imageView);
            } else if (obj instanceof File) {
                manager.load((File) obj).into(imageView);
            } else {
                manager.load(obj).into(imageView);
            }
        }


    }


}
