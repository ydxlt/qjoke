package com.lt.qjoke;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by admin on 2018/4/4.
 */

public class App extends Application {

    public static String sPackageName;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        initPackName();
    }

    private void initPackName() {
        PackageInfo info;
        try {
            info = getApplicationContext().getPackageManager().getPackageInfo(this.getPackageName(), 0);
            sPackageName = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
