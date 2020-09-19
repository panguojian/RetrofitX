package com.pgj.retrofitx.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.pgj.retrofitx.MyApplication;


/**
 * Created by pgj on 2020/9/19
 **/

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = MyApplication.getContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = MyApplication.getContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
