package com.example.martiandemo.ui.posts.comments.create

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.db.models.Comment
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentCreateCommentViewModel(val appRepository: AppRepository) : ViewModel() {

    private var postId: Int = 0
    private var name = ""
    private var email = ""
    private var body = ""


    val createdComment by lazyDeferred {
        appRepository.getSavedComment()
    }

    fun createComment() {
        val comment = Comment(postId, name, email, body)
        GlobalScope.launch(Dispatchers.IO) {
            appRepository.saveComment(comment)
        }
    }

    fun setPostId(postId: Int) {
        this.postId = postId
    }

    fun setComment(name: String, email: String, body: String) {
        this.name = name
        this.email = email
        this.body = body
    }

}
