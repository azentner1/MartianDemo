package com.example.martiandemo.ui.posts.comments.create

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.db.models.Comment
import com.example.martiandemo.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentCreateCommentViewModel(val appRepository: AppRepository) : ViewModel() {

    private var postId: Int = 0

    fun createComment(name: String, email: String, body: String) {
        val comment = Comment(postId, name, email, body)
        GlobalScope.launch(Dispatchers.IO) {
            appRepository.createComment(comment)
        }
    }

    fun setPostId(postId: Int) {
        this.postId = postId
    }

}
