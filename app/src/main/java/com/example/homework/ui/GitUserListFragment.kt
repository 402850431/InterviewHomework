package com.example.homework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework.databinding.FragmentUserListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitUserListFragment : Fragment() {
    private val viewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUserListBinding.inflate(inflater)

        return binding.root
    }
}