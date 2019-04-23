package com.example.martiandemo.ui.posts.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.api.ApiService
import com.example.martiandemo.data.repository.AppRepository


class CreatePostViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentCreatePostViewModel(appRepository) as T
    }
}