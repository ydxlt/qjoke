package com.lt.qjoke.viewmodel;

import android.content.Context;
import android.database.Observable;
import android.databinding.ObservableField;

import com.lt.qjoke.base.ViewModel;
import com.lt.qjoke.command.ReplyCommand;
import com.lt.qjoke.retrofit.AmuseService;

/**
 * Created by admin on 2018/4/3.
 */

public class AmuseItemViewModel implements ViewModel {

    private Context mContext;
    private AmuseService.Amuse.ResBody.PageBean.Content mContent;

    // ViewStyle

    // 业务逻辑数据
    public final ObservableField<String> username = new ObservableField<String>();
    public final ObservableField<String> postime = new ObservableField<String>();
    public final ObservableField<String> content = new ObservableField<String>();
    public final ObservableField<String> imageUrl = new ObservableField<String>(); // 正文url
    public final ObservableField<String> profileImageUrl = new ObservableField<String>(); // 头像url

    // 事件绑定
    public final ReplyCommand onCompletionCommand = new ReplyCommand(()->{
        // VideoView Completion do something

    });
    public final ReplyCommand onErrorCommand = new ReplyCommand(()->{
        // VideoView onError something

    });

    public AmuseItemViewModel(AmuseService.Amuse.ResBody.PageBean.Content content, Context mContext) {
        this.mContent = content;
        this.mContext = mContext;
        this.username.set(content.getName());
        this.content.set(content.getText());
        this.postime.set(content.getCreate_time());
        this.imageUrl.set(content.getCdn_img());
        this.profileImageUrl.set(content.getProfile_image());
    }
}
