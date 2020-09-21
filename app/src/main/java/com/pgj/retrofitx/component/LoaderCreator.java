package com.pgj.retrofitx.component;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by pgj on 2020/9/19
 **/
public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP=new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context,String style){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(style) == null){
            final Indicator indicator=getIndicator(style);
            LOADING_MAP.put(style,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(style));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String style){
        if(style == null || style.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName=new StringBuilder();
        if(!style.contains(".")){
            final String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(style);
        try{
            final Class<?> drawableClass=Class.forName(drawableClassName.toString());
            return (Indicator)drawableClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
