package com.busealkan.covid19.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.busealkan.covid19.Covid19App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getContentViewLayoutResId(): Int

    @Inject
    @ApplicationContext
    lateinit var appContext: Context

    lateinit var covid19App: Covid19App

    abstract fun populateUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        covid19App = appContext as Covid19App

        if (this !is BaseBindingActivity<*>) {
            setContentView(getContentViewLayoutResId())
            populateUI(savedInstanceState)
        }
    }

}