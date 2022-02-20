package com.example.homework.ui

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import com.example.homework.R
import kotlinx.android.synthetic.main.layout_loading.view.*
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {
    private var loadingView: View? = null

    open fun loading(message: String?= null) {
        if (loadingView == null) {
            loadingView = layoutInflater.inflate(R.layout.layout_loading, null)
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            addContentView(loadingView, params)
        } else {
            loadingView?.ll_loading?.visibility = View.VISIBLE
            loadingView?.ll_loading?.isClickable = true
        }
    }

    /*关闭加载界面*/
    open fun hideLoading() {
        if (loadingView == null) {
            Timber.d("loadingView不存在")
        } else {
            loadingView?.ll_loading?.visibility = View.GONE
        }
    }

}