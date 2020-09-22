package com.pgj.retrofitx.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pgj.retrofitx.R
import com.pgj.retrofitx.component.RestLoader
import com.pgj.retrofitx.net.RestClient
import com.pgj.retrofitx.net.callback.IRequest
import java.io.File

class DownloadActivity : AppCompatActivity() {
    private var downloadBtn:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        initViews()
        downloadBtn?.setOnClickListener {
            RestClient.builder()
                    .url("/pic/bing/")
                    .success {
                        val file=File(it)
                        println("下载成功：$it")
                    }
                    .failure{
                        println("下载失败：${it.message}")
                    }
                    .request(object :IRequest{
                        override fun onRequestStart() {
                            RestLoader.showLoading(this@DownloadActivity)
                        }

                        override fun onRequestEnd() {
                            RestLoader.stopLoading()
                        }
                    })
                    .error { code, msg ->
                        println("下载错误：$code  $msg")
                    }
                    .extension("jpg")
                    .build()
                    .download()

        }
    }

    private fun initViews(){
        downloadBtn=findViewById(R.id.btn_download)
    }
}