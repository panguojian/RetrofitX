package com.pgj.retrofitx.app.contract

import com.pgj.retrofitx.base.IPresenter
import com.pgj.retrofitx.base.IView

/**
 * Created by pgj on 2020/9/21
 **/
class ExampleContract {
    interface Presenter:IPresenter{
        fun getData()
    }

    interface View:IView{
        fun showResult(msg:String)
        fun stopLoadingDialog()
    }
}