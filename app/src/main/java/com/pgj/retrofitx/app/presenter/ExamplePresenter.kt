package com.pgj.retrofitx.app.presenter

import com.pgj.retrofitx.app.contract.ExampleContract
import com.pgj.retrofitx.app.service.ExampleService
import com.pgj.retrofitx.app.view.ExampleActivity
import com.pgj.retrofitx.base.BasePresenter
import com.pgj.retrofitx.net.callback.IError
import com.pgj.retrofitx.net.callback.IFailure
import com.pgj.retrofitx.net.callback.IRequest
import com.pgj.retrofitx.net.callback.ISuccess


/**
 * Created by pgj on 2020/9/21
 **/
class ExamplePresenter(view: ExampleActivity):BasePresenter<ExampleActivity>(view),ExampleContract.Presenter{

    override fun getData(){
        ExampleService.getData(Request(),Success(),Failure(),Error())
    }

    inner class Request:IRequest{
        override fun onRequestStart() {

        }

        override fun onRequestEnd() {
            mView.stopLoadingDialog()
        }
    }

    private inner class Success:ISuccess{
        override fun onSuccess(response: String?) {
            mView.showResult(response!!)
        }
    }

    private inner class Failure:IFailure{
        override fun onFailure(t: Throwable?) {

        }
    }

    private inner class Error:IError{
        override fun onError(code: Int, msg: String?) {

        }
    }
}