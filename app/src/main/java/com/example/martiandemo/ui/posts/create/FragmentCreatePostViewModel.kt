package com.example.martiandemo.ui.posts.create

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.db.models.Post
import com.example.martiandemo.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentCreatePostViewModel(val appRepository: AppRepository) : ViewModel() {

    fun createPost(title: String, body: String)   {
        val post = Post(1, title, body)
        GlobalScope.launch(Dispatchers.IO) {
            appRepository.createPost(post)
        }
    }
}
