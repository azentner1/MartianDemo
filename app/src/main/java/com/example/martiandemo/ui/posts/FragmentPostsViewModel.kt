package com.example.martiandemo.ui.posts

import androidx.lifecycle.ViewModel
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.posts.adapter.PostsAdapter


class FragmentPostsViewModel(val appRepository: AppRepository) : ViewModel() {

    private var postsAdapter: PostsAdapter = PostsAdapter(listOf())

    val posts by lazyDeferred {
        appRepository.getPosts()
    }

    fun getPostsAdapter(): PostsAdapter {
        return postsAdapter
    }

}