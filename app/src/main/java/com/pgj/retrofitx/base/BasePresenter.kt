package com.pgj.retrofitx.base

/**
 * Created by pgj on 2020/9/21
 **/
abstract class BasePresenter<T:IView>(view:T){
    protected var mView:T=view
}