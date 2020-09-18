package com.pgj.retrofitx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pgj.retrofitx.net.RestClient;
import com.pgj.retrofitx.net.RestCreator;
import com.pgj.retrofitx.net.callback.IError;
import com.pgj.retrofitx.net.callback.IFailure;
import com.pgj.retrofitx.net.callback.IRequest;
import com.pgj.retrofitx.net.callback.ISuccess;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoadingDialog();
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.builder()
                        .url("/")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                System.out.println(response);
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .request(new IRequest() {
                            @Override
                            public void onRequestStart() {
                                System.out.println("请求开始");
                                progressDialog.show();
                            }

                            @Override
                            public void onRequestEnd() {
                                System.out.println("请求结束");
                                progressDialog.dismiss();
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(Throwable t) {
                                System.out.println("失败："+t.getMessage());
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                System.out.println("错误");
                            }
                        })
                        .build()
                        .get();
            }
        });


    }

    private void initLoadingDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("加载中......");
    }

}