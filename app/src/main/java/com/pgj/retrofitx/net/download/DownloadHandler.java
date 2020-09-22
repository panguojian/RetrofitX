package com.pgj.retrofitx.net.download;

import android.os.AsyncTask;

import com.pgj.retrofitx.net.RestCreator;
import com.pgj.retrofitx.net.callback.IError;
import com.pgj.retrofitx.net.callback.IFailure;
import com.pgj.retrofitx.net.callback.IRequest;
import com.pgj.retrofitx.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pgj on 2020/9/22
 **/
public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS= RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url, ISuccess success, IFailure failure, IError error, IRequest request, String download_dir, String extension, String name) {
        URL = url;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        REQUEST = request;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
    }

    public final void handleDownload(){
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ResponseBody responseBody=response.body();
                            final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,NAME,responseBody);
                            if(task.isCancelled()){
                                if(REQUEST!=null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else{
                             if(ERROR!=null){
                                 ERROR.onError(response.code(),response.message());
                             }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE!=null){
                            FAILURE.onFailure(t);
                        }
                    }
                });
    }
}
