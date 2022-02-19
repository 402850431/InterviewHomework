package com.example.homework.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.network.GitUserApi
import com.example.homework.network.user.GitUser
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val _gitUserList = MutableLiveData<List<GitUser>>()
    val gitUserList: LiveData<List<GitUser>>
        get() = _gitUserList

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            try {
                _gitUserList.value = GitUserApi.retrofitService.getGitUserList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
