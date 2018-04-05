package com.lt.qjoke.adapter;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.SeekBar;

import com.lt.qjoke.command.ReplyCommand;

import java.lang.reflect.Method;

/**
 * Created by admin on 2018/4/4.
 */

public class SeekBarBindingAdapter {

    @BindingAdapter(value = {"onSeekBarChangeCommand"},requireAll = false)
    public static void onSeekBarChangeCommand(SeekBar seekBar, ReplyCommand<SeekBarChangeDataWrapper> replyCommand){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(replyCommand != null){
                    replyCommand.execute(new SeekBarChangeDataWrapper(seekBar,i,b,SeekBarChangeDataWrapper.onProgressChanged));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(replyCommand != null){
                    replyCommand.execute(new SeekBarChangeDataWrapper(seekBar,SeekBarChangeDataWrapper.onStartTrackingTouch));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(replyCommand != null){
                    replyCommand.execute(new SeekBarChangeDataWrapper(seekBar,SeekBarChangeDataWrapper.onStopTrackingTouch));
                }
            }
        });
    }

    public static class SeekBarChangeDataWrapper{
        public SeekBar seekbar;
        public int progress;
        public boolean fromUser;
        public int method;
        public static final int onProgressChanged = 0;
        public static final int onStartTrackingTouch = 1;
        public static final int onStopTrackingTouch = 2;

        public SeekBarChangeDataWrapper(SeekBar seekbar, int progress, boolean fromUser, int method) {
            this.seekbar = seekbar;
            this.progress = progress;
            this.fromUser = fromUser;
            this.method = method;
        }

        public SeekBarChangeDataWrapper(SeekBar seekbar, int method) {
            this.seekbar = seekbar;
            this.method = method;
        }
    }
}
