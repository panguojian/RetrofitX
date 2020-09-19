package com.pgj.retrofitx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pgj.retrofitx.net.RestClient;
import com.pgj.retrofitx.net.callback.IError;
import com.pgj.retrofitx.net.callback.IFailure;
import com.pgj.retrofitx.net.callback.IRequest;
import com.pgj.retrofitx.net.callback.ISuccess;
import com.pgj.retrofitx.ui.RestLoader;


/**
 * Created by pgj on 2020/9/18
 **/

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.builder()
                        .url("")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                System.out.println(response);
                            }
                        })
                        .request(new IRequest() {
                            @Override
                            public void onRequestStart() {
                                RestLoader.showLoading(MainActivity.this);
                            }

                            @Override
                            public void onRequestEnd() {
                                RestLoader.stopLoading();
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(MainActivity.this,"请求失败："+t.getMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                Toast.makeText(MainActivity.this,"请求错误:"+code+" "+msg,Toast.LENGTH_LONG).show();
                            }
                        })
                        .build()
                        .get();
            }
        });


    }


}