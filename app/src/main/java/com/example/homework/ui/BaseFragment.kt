package com.example.homework.ui

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    open fun loading(message: String? = null) {
        if (activity is BaseActivity)
            (activity as BaseActivity).loading(message)
    }

    /*关闭加载界面*/
    open fun hideLoading() {
        if (activity is BaseActivity)
            (activity as BaseActivity).hideLoading()
    }

}