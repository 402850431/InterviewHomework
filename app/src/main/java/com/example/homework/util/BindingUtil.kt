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

package com.example.homework.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homework.R
import com.example.homework.network.user.GitUser
import com.example.homework.ui.GitUserListAdapter

//@BindingAdapter("listData")
//fun bindAdapterData(recyclerView: RecyclerView, data: List<GitUser>?) {
//    val adapter = recyclerView.adapter as GitUserListAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("imageUrl")
fun ImageView.glideLoadImg(imgUrl: String?) {
    imgUrl?.let {
        Glide.with(this.context)
                .load(imgUrl)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_baseline_broken_image_24))
                .into(this)
    }
}
