package com.pgj.retrofitx;

import android.app.Application;
import android.content.Context;

/**
 * Created by pgj on 2020/9/19
 **/
public class MyApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
