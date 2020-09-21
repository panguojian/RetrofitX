package com.pgj.retrofitx.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.pgj.retrofitx.component.RestLoader

/**
 * Created by pgj on 2020/9/21
 **/
abstract class BaseActivity<T:IPresenter>:AppCompatActivity() {
    protected var mPresenter:T?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mPresenter=getPresenter()
        initialize()
    }

    protected abstract fun getLayout():Int
    protected abstract fun getPresenter():T
    protected abstract fun initialize()


}