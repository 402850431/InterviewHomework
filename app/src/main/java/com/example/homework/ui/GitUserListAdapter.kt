/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.homework.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.databinding.ItemGitUserBinding
import com.example.homework.network.user.GitUser

class GitUserListAdapter(private val onClickListener: OnClickListener ) :
        ListAdapter<GitUser, GitUserListAdapter.MarsPropertyViewHolder>(DiffCallback) {
    
    class MarsPropertyViewHolder(private var binding: ItemGitUserBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(gitUser: GitUser) {
            binding.gitUser = gitUser
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(ItemGitUserBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val gitUser = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(gitUser)
        }
        holder.bind(gitUser)
    }

    class OnClickListener(val clickListener: (gitUser:GitUser) -> Unit) {
        fun onClick(gitUser:GitUser) = clickListener(gitUser)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GitUser>() {
        override fun areItemsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
