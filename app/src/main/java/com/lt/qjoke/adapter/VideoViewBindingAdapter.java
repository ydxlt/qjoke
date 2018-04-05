package com.lt.qjoke.adapter;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.VideoView;

import com.lt.qjoke.command.ReplyCommand;

/**
 * Created by admin on 2018/4/4.
 */

public class VideoViewBindingAdapter {

    @android.databinding.BindingAdapter({"onCompletionCommand"})
    public static void onCompletionCommand(VideoView videoView, ReplyCommand replyCommand){
        videoView.setOnCompletionListener(mediaPlayer -> {
            if (replyCommand != null){
                replyCommand.execute();
            }
        });
    }

    @android.databinding.BindingAdapter({"onErrorCommand"})
    public static void onErrorCommand(VideoView videoView, ReplyCommand replyCommand){
        videoView.setOnErrorListener((m,w,e) -> {
            if (replyCommand != null){
                replyCommand.execute();
            }
            return false;
        });
    }

    @android.databinding.BindingAdapter({"videoPath"})
    public static void setVideoPath(VideoView videoView,String videoPath){
        if (!TextUtils.isEmpty(videoPath)) {
            videoView.setVideoPath(videoPath);
        }
    }
    @android.databinding.BindingAdapter({"onStartCommand"})
    public static void onStartCommand(VideoView videoView,ReplyCommand<VideoView> replyCommand){
        videoView.start();
        if(replyCommand != null){
            replyCommand.execute(videoView);
        }
    }
}
