package com.pgj.retrofitx.net.callback;

/**
 * Created by pgj on 2020/9/18
 **/
public interface IError {
    void onError(int code,String msg);
}
