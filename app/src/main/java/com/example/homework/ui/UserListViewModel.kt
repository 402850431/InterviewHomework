package com.example.homework.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.network.GitUserApi
import com.example.homework.network.user.GitUser
import com.example.homework.network.user.UserDetail
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private var mGitUserList = listOf<GitUser>()
    private val _gitUserList = MutableLiveData<List<GitUser>>()
    val gitUserList: LiveData<List<GitUser>>
        get() = _gitUserList

    private val _gitUserDetail = MutableLiveData<UserDetail>()
    val gitUserDetail: LiveData<UserDetail>
        get() = _gitUserDetail

    fun getUserList() {
        viewModelScope.launch {
            try {
                mGitUserList = GitUserApi.retrofitService.getGitUserList()
                _gitUserList.value = mGitUserList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
