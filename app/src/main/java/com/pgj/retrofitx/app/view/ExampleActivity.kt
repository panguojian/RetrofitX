package com.pgj.retrofitx.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pgj.retrofitx.R
import com.pgj.retrofitx.app.contract.ExampleContract
import com.pgj.retrofitx.app.presenter.ExamplePresenter
import com.pgj.retrofitx.base.BaseActivity
import com.pgj.retrofitx.component.RestLoader

class ExampleActivity : BaseActivity<ExamplePresenter>(),ExampleContract.View {

    private var refresh:SwipeRefreshLayout?=null
    private var result:TextView?=null

    override fun getLayout(): Int {
        return R.layout.activity_example
    }

    override fun getPresenter(): ExamplePresenter {
        return ExamplePresenter(this)
    }

    override fun initialize() {
        initViews()
        setupEvents()
    }

    private fun initViews(){
        refresh=findViewById(R.id.srl_refresh)
        result=findViewById(R.id.tv_result)
    }

    private fun setupEvents(){
        refresh?.setOnRefreshListener {
            mPresenter?.getData()
        }
    }

    override fun showResult(msg:String) {
        result?.text=msg
    }

    override fun stopLoadingDialog() {
        RestLoader.stopLoading()
        refresh?.isRefreshing=false
    }

}