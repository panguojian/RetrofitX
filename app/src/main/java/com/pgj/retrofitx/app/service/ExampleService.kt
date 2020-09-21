package com.pgj.retrofitx.app.service

import com.pgj.retrofitx.net.RestClient
import com.pgj.retrofitx.net.callback.IError
import com.pgj.retrofitx.net.callback.IFailure
import com.pgj.retrofitx.net.callback.IRequest
import com.pgj.retrofitx.net.callback.ISuccess

/**
 * Created by pgj on 2020/9/21
 **/
object ExampleService {
    fun getData(request:IRequest,success:ISuccess,failure:IFailure,error:IError){
        RestClient.builder()
                .url("")
                .request(request)
                .success(success)
                .failure(failure)
                .error (error)
                .build()
                .get()
    }
}