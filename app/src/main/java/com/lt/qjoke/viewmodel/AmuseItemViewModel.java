package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.lt.qjoke.App;
import com.lt.qjoke.R;
import com.lt.qjoke.adapter.SeekBarBindingAdapter.SeekBarChangeDataWrapper;
import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.command.ReplyCommand;
import com.lt.qjoke.retrofit.AmuseService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2018/4/3.
 */

public class AmuseItemViewModel implements ViewModel {

    private Context mContext;
    public AmuseService.Amuse.ResBody.PageBean.Content mData;

    public ViewStyle viewStyle = new ViewStyle() ;
    private MediaPlayer mMediaPlayer;

    // ViewStyle
    public static class ViewStyle{
        public final ObservableBoolean icPlayVisabled = new ObservableBoolean(true);
        public final ObservableBoolean videoViewVisabled = new ObservableBoolean(false);
        public final ObservableField<Drawable> rlBackground = new ObservableField<>();
        public final ObservableBoolean isVideoViewRequestFocus = new ObservableBoolean(false);
        public final ObservableInt seekBarMax = new ObservableInt();
        public final ObservableInt seekBarProgress = new ObservableInt();
    }

    // 业务逻辑数据
    public final ObservableField<String> username = new ObservableField<String>();
    public final ObservableField<String> postime = new ObservableField<String>();
    public final ObservableField<String> content = new ObservableField<String>();
    public final ObservableField<String> imageUrl = new ObservableField<String>(); // 正文url
    public final ObservableField<String> profileImageUrl = new ObservableField<String>(); // 头像url
    public final ObservableField<String> videoPath = new ObservableField<String>(); // videoPath

    // 事件绑定
    public final ReplyCommand onCompletionCommand = new ReplyCommand(()->{
        // VideoView Completion do something
        viewStyle.icPlayVisabled.set(true);
    });
    public final ReplyCommand onErrorCommand = new ReplyCommand(()->{
        // VideoView onError something
        viewStyle.icPlayVisabled.set(true);
        viewStyle.icPlayVisabled.set(false);
        Toast.makeText(mContext,"播放失败~",Toast.LENGTH_SHORT).show();
    });
    public final ReplyCommand<VideoView> onStartCommand = new ReplyCommand<VideoView>(()->{});
    public final ReplyCommand<View> onClickCommand = new ReplyCommand<View>((v)->{
        // view onClick something
        switch (v.getId()){
            case R.id.iv_play:
                viewStyle.rlBackground.set(new ColorDrawable(Color.WHITE));
                viewStyle.icPlayVisabled.set(false);
                viewStyle.isVideoViewRequestFocus.set(true);
                viewStyle.videoViewVisabled.set(true);
                videoPath.set(mData.getVideo_uri());
                onStartCommand.execute();
                break;
            case R.id.ib_play:
                playOrStopVoice((ImageButton) v, mData.getVoiceuri());
                break;
        }});
    public final ReplyCommand<SeekBarChangeDataWrapper> onSeekBarChangeCommand = new ReplyCommand<>(w ->{
                switch (w.method){
                    case SeekBarChangeDataWrapper.onProgressChanged:
                        break;
                    case SeekBarChangeDataWrapper.onStartTrackingTouch:
                        break;
                    case SeekBarChangeDataWrapper.onStopTrackingTouch:
                        mMediaPlayer.seekTo(w.seekbar.getProgress());
                        break;
                }
            });

    public AmuseItemViewModel(AmuseService.Amuse.ResBody.PageBean.Content data, Context mContext) {
        this.mData = data;
        this.mContext = mContext;
        initViewStyle();
        this.username.set(mData.getName());
        this.content.set(mData.getText());
        this.postime.set(mData.getCreate_time());
        this.imageUrl.set(mData.getCdn_img());
        this.profileImageUrl.set(mData.getProfile_image());
    }

    private void initViewStyle() {
        viewStyle.rlBackground.set(new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray)));
    }

    private void playOrStopVoice(final ImageButton view, String uri) {
        view.setSelected(!view.isSelected());
        if(mMediaPlayer ==null){//代表第一次播放音乐
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    viewStyle.seekBarMax.set(mMediaPlayer.getDuration()); //设置进度条
                    //----------定时器记录播放进度---------//
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            viewStyle.seekBarProgress.set(mMediaPlayer.getCurrentPosition());
                        }
                    };
                    timer.schedule(timerTask, 0, 10);
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    view.setSelected(!view.isSelected());
                }
            });
        }else if(mMediaPlayer.isPlaying()){//正在播放音乐
            mMediaPlayer.pause();
            //把图标改成播放的图标（暂停状态）
        }else{
            mMediaPlayer.reset();
            try {
                mMediaPlayer.setDataSource(mContext, Uri.parse(uri));
            } catch (Exception e) {
                view.setImageResource(R.mipmap.ic_play_48);
                e.printStackTrace();
            }
            mMediaPlayer.prepareAsync();
        }
    }
}
