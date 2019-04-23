package com.example.martiandemo.ui.posts.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.repository.AppRepository


class CommentsViewHolderFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentCommentsViewModel(appRepository) as T
    }
}