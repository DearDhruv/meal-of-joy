/*
 *  TwitterSearchActivity.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 25-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.twitter.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mealofjoy.android.extensions.toRelativeDate
import com.mealofjoy.android.model.TwitterSearch.Search
import com.mealofjoy.android.twitter.R
import kotlinx.android.synthetic.main.twitter_search_entity.view.*


class TwitterSearchAdapter : ListAdapter<Search, SearchVH>(
    SEARCH_DIFF_CALLBACK
) {
    var onSearchClicked: ((Search) -> (Unit))? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH =
        SearchVH.create(
            parent,
            viewType
        ).apply { onClick = onSearchClicked }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        holder.entity = getItem(position)
    }

}


class SearchVH private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var onClick: ((Search) -> Unit)? = null

    var entity: Search? = null
        @SuppressLint("SetTextI18n")
        set(value) {
            field = value
            value?.let { search ->

                val profileImageUrl = search.user?.profile_image_url?.replace("_normal", "")
                itemView.avatar.load(profileImageUrl) {
                    crossfade(true)
                    crossfade(300)
                    placeholder(R.drawable.ic_twitter_logo_whiteonblue)
                    transformations(CircleCropTransformation())
                }
                itemView.fullname.text = search.user?.name
                itemView.username.text = "@" + search.user?.screen_name
                itemView.time_stamp.text = search.created_at?.toRelativeDate()
                itemView.tweet.text = search.text

                itemView.setOnClickListener { onClick?.invoke(search) }
            }
        }

    companion object Factory {
        fun create(parent: ViewGroup, viewType: Int): SearchVH {
            return SearchVH(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.twitter_search_entity,
                    parent,
                    false
                )
            )
        }
    }
}


val SEARCH_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }
}


