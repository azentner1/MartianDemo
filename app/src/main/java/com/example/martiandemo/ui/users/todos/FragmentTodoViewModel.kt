package com.example.martiandemo.ui.users.todos

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.users.todos.adapter.TodoAdapter

class FragmentTodoViewModel(val appRepository: AppRepository) : ViewModel() {

    private var todoAdapter: TodoAdapter = TodoAdapter(listOf())

    private var userId: Int = 0

    val todos by lazyDeferred {
        appRepository.getTodos(userId)
    }

    fun getAdapter() : TodoAdapter {
        return todoAdapter
    }

    fun setUserId(userId: Int) {
        this.userId = userId
    }
}
