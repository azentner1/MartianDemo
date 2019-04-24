package com.example.martiandemo.ui.posts.comments.create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_create_comment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentCreateComment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val createCommentViewModelFactory: CreateCommentViewModelFactory by instance()

    private lateinit var createCommentViewModel: FragmentCreateCommentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createCommentViewModel = ViewModelProviders.of(this, createCommentViewModelFactory).get(FragmentCreateCommentViewModel::class.java)

        arguments?.let {
            createCommentViewModel.setPostId(FragmentCreateCommentArgs.fromBundle(it).postId)
        }

        initUi()
    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.create_comment)
        fabCreateComment.setOnClickListener {
            saveComment()
        }
    }

    fun saveComment() = launch(Dispatchers.Main) {
        groupCreatingComment.visibility = View.VISIBLE
        createCommentViewModel.setComment(etCreateCommentName.text.toString(), etCreateCommentEmail.text.toString(), etCreateCommentBody.text.toString())
        createCommentViewModel.createComment()
        val comment = createCommentViewModel.createdComment.await()
        comment.observe(this@FragmentCreateComment, Observer {
            groupCreatingComment.visibility = View.GONE
            activity?.supportFragmentManager?.popBackStack()
        })

    }

}
