package com.example.martiandemo.ui.users

import androidx.lifecycle.ViewModel
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.users.adapter.UsersAdapter


class FragmentUsersViewModel(val appRepository: AppRepository) : ViewModel() {

    private var usersAdapter: UsersAdapter = UsersAdapter(listOf())


    val users by lazyDeferred {
        appRepository.getUsers()
    }

    fun getUsersAdapter(): UsersAdapter {
        return usersAdapter
    }
}