package com.lt.qjoke.adapter;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lt.qjoke.command.ReplyCommand;

/**
 * Created by admin on 2018/4/4.
 */

public class ViewBindingAdapter {

    @android.databinding.BindingAdapter({"onClickCommand"})
    public static void onClickCommand(View view, ReplyCommand replyCommand){
        view.setOnClickListener( v -> {
            if(replyCommand != null){
                replyCommand.execute(v);
            }
        });
    }

    @android.databinding.BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @android.databinding.BindingAdapter({"background"})
    public static void setBackground(View view, Drawable drawable) {
        if (drawable != null) {
            view.setBackground(drawable);
        }
    }
}
