package com.example.homework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.homework.databinding.FragmentUserListBinding
import kotlinx.android.synthetic.main.fragment_user_list.*

class GitUserListFragment : BaseFragment() {

    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

    private val userListAdapter by lazy {
        GitUserListAdapter(GitUserListAdapter.OnClickListener {
            //call another api
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserListBinding.inflate(inflater).root.apply {
        loading()
        viewModel.getUserList()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        initObserver()
    }

    private fun initObserver() {
        viewModel.gitUserList.observe(viewLifecycleOwner) {
            hideLoading()
            userListAdapter.addFooterAndSubmitList(it)
        }
    }

    private fun initRv() {
        rv.adapter = userListAdapter
    }
}