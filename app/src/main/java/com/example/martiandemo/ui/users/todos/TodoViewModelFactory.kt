package com.example.martiandemo.ui.users.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.repository.AppRepository

class TodoViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentTodoViewModel(appRepository) as T
    }
}
