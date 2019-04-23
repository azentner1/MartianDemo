package com.example.martiandemo.ui.posts.comments.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.repository.AppRepository


class CreateCommentViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentCreateCommentViewModel(appRepository) as T
    }
}