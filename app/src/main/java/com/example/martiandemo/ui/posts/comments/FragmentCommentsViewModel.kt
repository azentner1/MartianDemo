package com.example.martiandemo.ui.posts.comments

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.posts.comments.adapter.CommentsAdapter

class FragmentCommentsViewModel(val appRepository: AppRepository) : ViewModel() {

    private var commentsAdapter: CommentsAdapter = CommentsAdapter(listOf())

    private var postId: Int = 0

    val comments by lazyDeferred {
        appRepository.getComments(postId)
    }

    fun setPostId(postId: Int) {
        this.postId = postId
    }

    fun getAdapter(): CommentsAdapter {
        return commentsAdapter
    }

    fun getPostId(): Int {
        return postId
    }

}
