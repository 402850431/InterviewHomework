package com.example.homework.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.databinding.ItemGitUserBinding
import com.example.homework.databinding.ItemGitUserFooterBinding
import com.example.homework.databinding.ItemGitUserHeaderBinding
import com.example.homework.databinding.ItemNoDataBinding
import com.example.homework.network.user.GitUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GitUserListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback) {

    enum class ItemType {
        HEADER, ITEM, FOOTER, NO_DATA
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addFooterAndSubmitList(list: List<GitUser>, isLastPage: Boolean = true) {
        adapterScope.launch {
            val items = listOf(DataItem.Header) + when {
                list.isNullOrEmpty() -> listOf(DataItem.NoData)
                isLastPage -> list.map { DataItem.Item(it) } + listOf(DataItem.Footer)
                else -> list.map { DataItem.Item(it) }
            }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER.ordinal -> HeaderViewHolder.from(parent)
            ItemType.ITEM.ordinal -> ItemViewHolder.from(parent)
            ItemType.FOOTER.ordinal -> FooterViewHolder.from(parent)
            ItemType.NO_DATA.ordinal -> NoDataViewHolder.from(parent)
            else -> NoDataViewHolder.from(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ItemType.HEADER.ordinal
            is DataItem.Item -> ItemType.ITEM.ordinal
            is DataItem.Footer -> ItemType.FOOTER.ordinal
            is DataItem.NoData -> ItemType.NO_DATA.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val gitUser = (getItem(position) as DataItem.Item).gitUser
                holder.itemView.setOnClickListener {
                    onClickListener.onClick(gitUser)
                }
                holder.bind(gitUser)
            }

            is HeaderViewHolder -> {
            }

            is FooterViewHolder -> {
            }

            is NoDataViewHolder -> {
            }
        }
    }

    class ItemViewHolder(private var binding: ItemGitUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gitUser: GitUser) {
            binding.gitUser = gitUser
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemGitUserBinding.inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    class HeaderViewHolder(binding: ItemGitUserHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemGitUserHeaderBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    class FooterViewHolder(binding: ItemGitUserFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemGitUserFooterBinding.inflate(layoutInflater, parent, false)
                return FooterViewHolder(binding)
            }
        }
    }

    class NoDataViewHolder(binding: ItemNoDataBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemNoDataBinding.inflate(layoutInflater, parent, false)
                return NoDataViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (gitUser: GitUser?) -> Unit) {
        fun onClick(gitUser: GitUser?) = clickListener(gitUser)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

sealed class DataItem {

    abstract val id: Int?

    data class Item(val gitUser: GitUser) : DataItem() {
        override val id = gitUser.id
    }

    object Header : DataItem() {
        override val id: Int? = null
    }

    object Footer : DataItem() {
        override val id: Int? = null
    }

    object NoData : DataItem() {
        override val id: Int? = null
    }

}
