package com.lt.qjoke.adapter;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by admin on 2018/4/4.
 */

public final class ImageViewBindingAdapter {

    @android.databinding.BindingAdapter({"uri"})
    public static void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {
        if (!TextUtils.isEmpty(uri)) {
            simpleDraweeView.setImageURI(Uri.parse(uri));
        }
    }
}
