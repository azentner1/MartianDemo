package com.example.martiandemo.ui.posts.create

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.db.models.Post
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentCreatePostViewModel(val appRepository: AppRepository) : ViewModel() {

    private var userId: Int = 1
    private var title = ""
    private var body = ""

    val createdPost by lazyDeferred {
        appRepository.getSavedPost()
    }

    fun createComment() {
        val post = Post(userId, title, body)
        GlobalScope.launch(Dispatchers.IO) {
            appRepository.savePost(post)
        }
    }

    fun setPostData(title: String, body: String) {
        this.title = title
        this.body = body
    }
}
