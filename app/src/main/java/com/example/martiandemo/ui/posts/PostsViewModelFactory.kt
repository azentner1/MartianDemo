package com.example.martiandemo.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.repository.AppRepository


class PostsViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentPostsViewModel(appRepository) as T
    }
}