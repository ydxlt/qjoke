package com.lt.qjoke.utils;

import android.util.Log;

/**
 * Created by luotong on 2017/9/20.
 */

public class LogUtils {

    private static final String TAG = "LogUtils";
    private static final int LEVEL = 1;
    private static final boolean sIsDebug = true;
    private static final int LEVEL_VERBOSE = 0;
    private static final int LEVEL_DEBUG = 1;
    private static final int LEVEL_INFO = 2;
    private static final int LEVEL_WRAN = 3;
    private static final int LEVEL_ERROR = 4;

    private LogUtils(){
        throw new RuntimeException("LogUtils is not be new");
    }

    public static void v(String tag,String msg){
        if(LEVEL >=LEVEL_VERBOSE && sIsDebug) {
            Log.v(tag, msg);
        }
    }

    public static void v(String msg){
        v(TAG,msg);
    }

    public static void d(String tag,String msg){
        if(LEVEL >=LEVEL_DEBUG && sIsDebug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg){
        d(TAG,msg);
    }

    public static void i(String tag,String msg){
        if(LEVEL >=LEVEL_INFO && sIsDebug) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg){
        i(TAG,msg);
    }

    public static void w(String tag,String msg){
        if(LEVEL >=LEVEL_WRAN && sIsDebug) {
            Log.w(tag, msg);
        }
    }

    public static void w(String msg){
        w(TAG,msg);
    }

    public static void e(String tag,String msg){
        if(LEVEL >=LEVEL_ERROR && sIsDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg){
        e(TAG,msg);
    }
}
