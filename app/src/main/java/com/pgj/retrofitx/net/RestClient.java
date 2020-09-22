package com.pgj.retrofitx.net;

import com.pgj.retrofitx.net.callback.IError;
import com.pgj.retrofitx.net.callback.IFailure;
import com.pgj.retrofitx.net.callback.IRequest;
import com.pgj.retrofitx.net.callback.ISuccess;
import com.pgj.retrofitx.net.callback.RequestCallbacks;
import com.pgj.retrofitx.net.download.DownloadHandler;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by pgj on 2020/9/18
 **/
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url, Map<String, Object> params, ISuccess success, IFailure failure, IError error, IRequest rquest, RequestBody body,String downloadDir,String extension,String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = rquest;
        this.BODY = body;
        this.DOWNLOAD_DIR=downloadDir;
        this.EXTENSION=extension;
        this.NAME=name;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call = null;

        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }

        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(SUCCESS,FAILURE,ERROR,REQUEST);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownloadHandler(URL,SUCCESS,FAILURE,ERROR,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME).handleDownload();
    }
}
